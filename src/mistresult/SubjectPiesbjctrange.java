/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}. This chart has a lot of labels and
 * rotates, so it is useful for testing the label distribution algorithm.
 */
public class SubjectPiesbjctrange extends JFrame {

    /**
     * Default constructor.
     *
     * @param title the frame title.
     */
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mist_result";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    int totstudent=0;
    
    public SubjectPiesbjctrange(String title,String subjecttitle, String batchname ,String subjectid ,String semesterid ) {

        super(title);
        final PieDataset dataset = createDataset(subjectid,batchname,semesterid);

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart(
                subjecttitle+"\nTotal Student : "+totstudent, // chart title
                dataset, // dataset
                false, // include legend
                true,
                false
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(new Color(222, 222, 255));
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setCircular(true);
        plot.setLabelGenerator(new StandardPieItemLabelGenerator(
                "{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
        ));
        plot.setNoDataMessage("No data available");

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);
        add(chartPanel,BorderLayout.CENTER);
        JButton btn = new JButton("Save");
        btn.setBackground(new java.awt.Color(21, 21, 21));
        btn.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        btn.setForeground(new java.awt.Color(255, 255, 255));
        btn.setFocusable(false);
        add(btn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File imageFile = new File(subjecttitle+".png");
                int width = 1366;
                int height = 768;

                try {
                    ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                    
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //final Rotator rotator = new Rotator(plot);
        //rotator.start();
    }

    /*
     * Creates a sample dataset.
     */
    private PieDataset createDataset(String subjectid , String batchname ,String semesterid ) {
        //System.err.println(subjectid+" "+studentid);
        
        String sql = "SELECT distinct idnumber,s.gradetitle,sb.coursenumber,sb.subject,sb.creditunit,s.gradepoints from semestergrades"
                + " s join subjects sb using (subjectid) join classes c using(classid) join students using(studentid) where studentid "
                + "in (select studentid from students where batchnumber= '"+batchname+"' ) and s.subjectid="+subjectid+" and "
                + "c.semesterid="+semesterid+" ORDER BY `students`.`idNumber` ASC";
        
        //System.out.println(sql);
         Connection conn = null;
        Statement stmt = null;
        Map<String,Integer> map = new HashMap<String,Integer>();
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
            while (rs.next()) {
                totstudent++;
                if(!map.containsKey(rs.getString(2)))map.put(rs.getString(2), 1);
                else map.put(rs.getString(2), map.get(rs.getString(2))+1);
                
            }

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
        
        
        final DefaultPieDataset result = new DefaultPieDataset();
        for(Map.Entry<String,Integer> mp : map.entrySet())
        {
            //System.err.println(mp.getKey()+" "+mp.getValue());
            result.setValue(mp.getKey(),mp.getValue());
        }
        return result;
    }

//    public static void main(final String[] args) {
//
//        Log.getInstance().addTarget(new PrintStreamLogTarget());
//        //final SubjectPieChart demo = new SubjectPieChart(, JDBC_DRIVER, DB_URL, DB_URL);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//
//    }

    /**
     * The rotator.
     *
     */
    static class Rotator extends Timer implements ActionListener {

        /**
         * The plot.
         */
        private PiePlot plot;

        /**
         * The angle.
         */
        private int angle = 270;

        /**
         * Constructor.
         *
         * @param plot the plot.
         */
        Rotator(final PiePlot plot) {
            super(100, null);
            this.plot = plot;
            addActionListener(this);
        }

        /**
         * Modifies the starting angle.
         */
        public void actionPerformed(final ActionEvent event) {
            this.plot.setStartAngle(angle);
            this.angle = this.angle + 1;
            if (this.angle == 360) {
                this.angle = 0;
            }
        }

    }

}
