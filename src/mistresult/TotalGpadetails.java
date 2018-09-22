/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TotalGpadetails extends JFrame {

    static String batchname = null;
    public TotalGpadetails(String selectedbatch) {
        batchname=selectedbatch;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DatabaseHelper object = new DatabaseHelper();
        ArrayList<String> semester = object.getsemesterid(selectedbatch);
        DefaultTableModel model = new DefaultTableModel();
        int totalstudent=0;
        totalstudent=object.totalstudent(selectedbatch);
        model.addColumn("ID Number");
        model.addColumn("Name");
        ArrayList<String> selectedsemester = new ArrayList<String>();
        for (int i = 0, j = 0; i < semester.size(); i++) {
            String sql1 = "select count(distinct idnumber) from students join semestergrades using(studentid) join classes using(classid) where batchnumber='" + selectedbatch + "' and semesterid=" + semester.get(i);

            if (object.gettotstdntbysemester(sql1) > totalstudent / 2) {
                String nameString=object.gettermname(semester.get(i));
                model.addColumn(nameString);
                selectedsemester.add(object.gettermid(nameString));
                
                //System.out.println(rs.getString(1));
            }
        }
        Map<String, String> studentinfo = object.getallid(selectedbatch);
        Map<String, String> student = new TreeMap<String, String>(studentinfo);
        int row =0,column=0;
        
        for (Map.Entry<String, String> mp : student.entrySet()) {
                column=0;
                //ArrayList<Object> arr = new ArrayList<Object>();
                model.addRow(new Object[]{});
                //arr.add(mp.getKey());
                model.setValueAt(mp.getKey(), row, column++);
                model.setValueAt(object.getstudentname(mp.getKey()), row, column++);
                for(int i=0;i<selectedsemester.size();i++)
                {
                    //System.out.println(mp.getValue());
                    //System.err.println(selectedsemester.get(i));
                    
                    double val=object.getGPA(mp.getValue(), selectedsemester.get(i));
                    model.setValueAt(val, row, column++);
                    //model.setval
                    //arr.add(val);
                }
                //model.addRow(new Object[]{arr});
                row++;
                //System.out.println(mp.getKey() + " " + mp.getValue() + " " + obj.getGPA(mp.getValue(), semesterid.get(i)));
                //dataset.addValue(obj.getGPA(mp.getValue(), semesterid.get(i)), semestername, mp.getKey());
            }
       
        
        //Object columnNames[] = {"Column One", "Column Two", "Column Three"};
        JTable table = new JTable(model);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(20);

        table.getColumn("ID Number").setCellRenderer(new ButtonRenderer());
            table.getColumn("ID Number").setCellEditor(
                  new ButtonEditor(new JCheckBox()));
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setSize(1000, 700);
        //setLocationRelativeTo(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    public static void main(String args[]) {
        new TotalGpadetails(batchname);

    }
    
    
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   // System.out.println(label + ": Ouch!");
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
      // 
                JFrame frame=new JFrame();
                PlainResultView ob= new PlainResultView();
                DatabaseHelper obj = new DatabaseHelper();
                ob.showinfo(Integer.toString(obj.getid(Integer.parseInt(label))));
                frame.add(new JScrollPane(ob));
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame.setSize(1000, 700);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                frame.setVisible(true);
                
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
