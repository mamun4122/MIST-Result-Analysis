/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This program demonstrates how to draw line chart with CategoryDataset using
 * JFreechart library.
 *
 * @author www.codejava.net
 *
 */
public class TotalGpaLineChart extends JFrame {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mist_result";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    JPanel panel = new JPanel();
    int pos = 0, cnt = 0;

    String titlename = null;

    JFreeChart chart;
    boolean ok;

    public TotalGpaLineChart() {
        super("");

        //JPanel chartPanel = createChartPanel();
        //add(chartPanel, BorderLayout.CENTER);
        JPanel pan = new JPanel(new FlowLayout());
        panel.setLayout(new CardLayout());
        add(panel, BorderLayout.CENTER);

        JLabel department = new JLabel("Department : ");
        //department.setSize(300, 500);
        pan.add(department);
        final DefaultComboBoxModel deptname = new DefaultComboBoxModel();
        DatabaseHelper ob = new DatabaseHelper();
        Map<String, String> info = ob.getdeptname();
        for (Map.Entry<String, String> mp : info.entrySet()) {
            deptname.addElement(mp.getKey());
        }
        final JComboBox dept = new JComboBox(deptname);
        pan.add(dept);

        JLabel batchlabel = new JLabel("Batch : ");
        pan.add(batchlabel);

        DefaultComboBoxModel batchname = new DefaultComboBoxModel();
        JComboBox batch = new JComboBox(batchname);
        pan.add(batch);

        JButton button = new JButton("Save");
        pan.add(button);
        JButton table = new JButton("Show Table");
        pan.add(table);
        JButton btn = new JButton("Show");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                File imageFile = new File(titlename + ".png");
                int width = 1366;
                int height = 768;

                try {
                    ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        ok = false;
        dept.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String batchString = info.get(dept.getSelectedItem().toString());

                if (!batchString.isEmpty()) {
                    ArrayList<String> infoaArrayList = ob.getbatchinfo(batchString);
                    if (infoaArrayList.size() > 0) {
                        //batchname.removeAllElements();
                        batch.removeAllItems();

                        //batchinfo = ob.getbatchinfo(batchString);
                        for (int i = 0; i < infoaArrayList.size(); i++) {
                            batch.addItem(infoaArrayList.get(i));
                        }
                        ok = true;
                        btn.setEnabled(true);
                    } else {
                        //System.out.println("not ok"+batchString);
                        ok = false;
                        batch.addItem("No Batch Found");
                        btn.setEnabled(false);
                    }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        table.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String batchnamefortable="CSE-13";
                try {
                    batchnamefortable = batch.getSelectedItem().toString();
                } catch (Exception eX) {
                }
                
                TotalGpadetails obj = new TotalGpadetails(batchnamefortable);
            }
        });

        add(pan, BorderLayout.NORTH);
        JPanel checkPanel = new JPanel(new CardLayout());

        DatabaseHelper object = new DatabaseHelper();
        JCheckBox[] arr = new JCheckBox[12];

        ArrayList<String> semesterid = new ArrayList<String>();
        batch.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                //System.out.println(batch.gets);
                if (ok) {
                    try {

                        String selectedbatch = batch.getSelectedItem().toString();
                        if (!selectedbatch.isEmpty()) {
                            ArrayList<String> semester = object.getsemesterid(selectedbatch);
                            JPanel panel1 = new JPanel(new FlowLayout());
                            panel1.removeAll();
                            panel1.repaint();
                            int totalstudent = object.totalstudent(selectedbatch);
                            //System.out.println(totalstudent);
                            boolean flag = false;
                            for (int i = 0, j = 0; i < semester.size(); i++) {
                                String sql1 = "select count(distinct idnumber) from students join semestergrades using(studentid) join classes using(classid) where batchnumber='" + batch.getSelectedItem().toString() + "' and semesterid=" + semester.get(i);

                                if (object.gettotstdntbysemester(sql1) > totalstudent / 2) {
                                    String nmeString = object.gettermname(semester.get(i));
                                    arr[j++] = new JCheckBox(nmeString);
                                    arr[j - 1].setName(nmeString);
                                    panel1.add(arr[j - 1]);
                                    pos = j;
                                    flag = true;
                                    //System.out.println(rs.getString(1));
                                }
                            }
                            if (flag) {
                                btn.setEnabled(true);
                            } else {
                                btn.setEnabled(false);
                            }
                            panel1.add(btn);
                            checkPanel.removeAll();
                            checkPanel.repaint();
                            checkPanel.add(new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

                            //checkPanel.setSize(40, 50);
                            checkPanel.repaint();
                            checkPanel.revalidate();
                            add(checkPanel, BorderLayout.SOUTH);
                        }
                    } catch (Exception f) {
                    }
                }

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        cnt = 0;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cnt = 0;
                semesterid.clear();
                for (int i = 0; i < pos; i++) {
                    if (arr[i].isSelected()) {
                        cnt++;
                        //System.out.println(arr[i].getName());
                        semesterid.add(object.gettermid(arr[i].getName()));
                    }
                }
                if (cnt == 0) {
                    JOptionPane.showMessageDialog(null, "Please Select Some levels");
                } else {
                    panel.removeAll();
                    panel.repaint();
                    JPanel chartPanel = createChartPanel(batch.getSelectedItem().toString(), semesterid);
                    panel.add(chartPanel);
                    panel.repaint();
                    panel.revalidate();
                }

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(String deptname, ArrayList<String> semesterid) {
        
        String chartTitle = "GPA OF STUDENTS IN ALL TERM of " + deptname;
        titlename = chartTitle;
        String categoryAxisLabel = "Student ID";
        String valueAxisLabel = "Term Wise GPA";

        CategoryDataset dataset = createDataset(deptname, semesterid);

        chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset);
        customizeChart(chart);
        return new ChartPanel(chart);
    }

    private CategoryDataset createDataset(String deptname, ArrayList<String> semesterid) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DatabaseHelper obj = new DatabaseHelper();
        //Series[] sereies=new Series[12];
        Map<String, String> studentinfo = obj.getallid(deptname);
        Map<String, String> student = new TreeMap<String, String>(studentinfo);

        for (int i = 0; i < semesterid.size(); i++) {
            String semestername = obj.gettermname(semesterid.get(i));
            for (Map.Entry<String, String> mp : student.entrySet()) {
                //System.out.println(mp.getKey() + " " + mp.getValue() + " " + obj.getGPA(mp.getValue(), semesterid.get(i)));
                dataset.addValue(obj.getGPA(mp.getValue(), semesterid.get(i)), semestername, mp.getKey());
            }
            //System.out.println(semestername);
        }

        return dataset;
    }

    private void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesPaint(3, Color.YELLOW);

        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());

        // sets thickness for series (using strokes)
        /*for (int i = 0; i <= 12; i++) {
         renderer.setSeriesStroke(i, new BasicStroke(4.0f));

         }*/
//         renderer.setSeriesStroke(0, new BasicStroke(4.0f));
//         renderer.setSeriesStroke(1, new BasicStroke(3.0f));
//         renderer.setSeriesStroke(2, new BasicStroke(2.0f));
//         renderer.setSeriesStroke(3, new BasicStroke(1.5f));
        // sets paint color for plot outlines
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TotalGpaLineChart().setVisible(true);
            }
        });
    }
}
