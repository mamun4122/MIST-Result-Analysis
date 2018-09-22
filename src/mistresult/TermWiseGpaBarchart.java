/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import static mistresult.DatabaseHelper.DB_URL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TermWiseGpaBarchart extends JPanel {

    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mist_result";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    
    double cgpa=0.0;
    
    public TermWiseGpaBarchart(String studentid) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane pane = new JScrollPane(this);
        JScrollPane pane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane1.getViewport().add(this);
        BasicShortInfo ob = new BasicShortInfo();
        DatabaseHelper obj = new DatabaseHelper();
        String id = Integer.toString(obj.getid(Integer.parseInt(studentid)));
        ob.showinfo(id);
        add(ob);
        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Term Wise GPA",
                "Level and Term",
                "GPA",
                createDataset(id),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        add(chartPanel);
        JButton btn= new JButton("Save");
        add(btn);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File imageFile = new File("Term Wise GPA of "+studentid+".png");
                int width = 1366;
                int height = 768;

                try {
                    ChartUtilities.saveChartAsPNG(imageFile, barChart, width, height);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
            Double res=cgpa;
            res=new BigDecimal(res ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            ob.showcgpa(Double.toString(res));
            ob.repaint();ob.revalidate();
        
    }

    private CategoryDataset createDataset(String studentid) {
        
        
        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        String sql = "select distinct semesterid from classes where classid in (select classid from semestergrades where studentid="+studentid+") ";
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DatabaseHelper ob = new DatabaseHelper();
            int cnt=0;
            //STEP 5: Extract data from result set
            while (rs.next()) {
                cnt++;
                String nameString=ob.gettermname(rs.getString(1));
                System.out.println(nameString);
                double val=ob.getGPA(studentid, rs.getString(1));
                cgpa+=val;
                  dataset.addValue(val, nameString, nameString);
            }
            cgpa/=cnt;

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        
        /*final String fiat = "FIAT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        dataset.addValue(1.0, fiat, speed);
        dataset.addValue(3.0, fiat, userrating);
        dataset.addValue(5.0, fiat, millage);
        dataset.addValue(5.0, fiat, safety);

        dataset.addValue(5.0, audi, speed);
        dataset.addValue(6.0, audi, userrating);
        dataset.addValue(10.0, audi, millage);
        dataset.addValue(4.0, audi, safety);

        dataset.addValue(4.0, ford, speed);
        dataset.addValue(2.0, ford, userrating);
        dataset.addValue(3.0, ford, millage);
        dataset.addValue(6.0, ford, safety);*/

        return dataset;
    }

}
