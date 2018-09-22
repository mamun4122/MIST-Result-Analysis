/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//import static mistresult.MistResult.createandshow;


/**
 *
 * @author HP
 */
public class Mainpanel implements MouseListener {

    int posx, posy, defposx, defposy, chngedposx, chngedposy;
    double dimh, dimw, tempx, tempy;

    static JFrame mainFrame;
    JPanel mainsidePanel, mainPanel, basicpanel, resultsidepanel, graphsidepanel;
    JTextField roll;
    JButton setroll, basicinfo, result, scholarship, mistresult, backresult, plainview, graphview, tgpa, tdetails, deptcgpa, deptgparnge, sbjctgparnge,deptgpapecentage,extraactivity,subjectgpapecentage;
    JButton logout;
    JPanel resultside;
    
    int id = 0;

    public Mainpanel() {
        //createshow();
    }

    public void addcomponent(Container pane) {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dimh = (double) (dim.getHeight() / 100);
        dimw = (double) (dim.getWidth() / 100);

        //*****************SIDE PANEL START ***************//
        
        
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        mainsidePanel = new JPanel();
        mainsidePanel.setLayout(null);
        posx = (int) (dimw * 30);
        mainsidePanel.setSize(posx, dim.height - 75);
        mainsidePanel.setLocation(0, 0);
        mainsidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        System.out.println(mainsidePanel.getSize().getWidth());
        
        
        JLabel vua = new JLabel();
        vua.setLocation(mainsidePanel.getX(), mainsidePanel.getY());
        vua.setSize(mainsidePanel.getSize());
        mainsidePanel.add(vua);
        

        Dimension dimension = mainsidePanel.getSize();
        dimw = dimension.width / 100;
        dimh = dimension.height / 100;

        roll = createTextField(posx - posx / 3, 30, (int) dimh, (int) dimw);
        roll.setEditable(false);
        roll.setName("roll");
        mainsidePanel.add(roll);

        setroll = createButton("SET", posx - posx / 3 + 5, 4, posx / 3 - 10, 30);
        mainsidePanel.add(setroll);
        setroll.setName("setroll");
        setroll.addMouseListener(this);

        tempx = dimw + 5;
        tempy = dimh * 5;
        basicinfo = createButton("Basic Information", 5, 35, (int) dimension.width - 10, (int) dimh * 10);
        basicinfo.setName("basicinfo");
        basicinfo.addMouseListener(this);
        mainsidePanel.add(basicinfo);

        tempy = dimh * 10;
        tempx = dimension.width - 10;

        tempy = tempy + 35;
        mistresult = createButton("MIST Result", 5, (int) tempy, (int) tempx, (int) dimh * 10);
        mistresult.setName("mistresult");
        mistresult.addMouseListener(this);
        mainsidePanel.add(mistresult);

        tempy = (int) dimh * 10 + tempy;
        backresult = createButton("Background Result", 5, (int) tempy, (int) tempx, (int) dimh * 10);
        backresult.setName("backresult");
        backresult.addMouseListener(this);
        mainsidePanel.add(backresult);

        tempy = tempy + (int) dimh * 10;
        defposx = (int) tempx;
        defposy = (int) tempy;
        scholarship = createButton("Scholarship", 5, defposy, defposx, (int) dimh * 10);
        scholarship.setName("scholarship");
        scholarship.addMouseListener(this);
        mainsidePanel.add(scholarship);
        
        tempy = tempy + (int) dimh * 10;
        defposx = (int) tempx;
        defposy = (int) tempy;
        scholarship = createButton("Extra Activities", 5, defposy, defposx, (int) dimh * 10);
        scholarship.setName("extraactivity");
        scholarship.addMouseListener(this);
        mainsidePanel.add(scholarship);
        

        tempy = (int) dimh * 10 + tempy;
        chngedposy = (int) tempy;
        
        logout = createButton("Logout", 5, (int) tempy, (int)tempx,(int)dimh*10);
        logout.setName("logout");
        logout.addMouseListener(this);
        mainsidePanel.add(logout);
        
        tempy = (int) dimh * 10 + tempy;
        
        
        JLabel imagelabel = new JLabel("image");
        imagelabel.setBounds(5, (int) tempy, (int)tempx, (int)dimh*50);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/mistresult/MIST.gif")); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        //Image newimg = image.getScaledInstance(imagelabel.getWidth(), imagelabel.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imageIcon = new ImageIcon(image);  // transform it back
        imagelabel.setIcon(imageIcon);
        System.err.println(imagelabel.getWidth()+" "+imagelabel.getHeight());
        mainsidePanel.add(imagelabel);
        
        
        
        
        tempy = (int) dimh * 10 + tempy;

        pane.add(mainsidePanel);

        //***********MAIN SIDEPANEL FINISHED***********//
        
       // Dimension dimension1=new Dimension((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)*69),(int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight())-75));
                
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        dimh = (double) (dim.getHeight() / 100);
        dimw = (double) (dim.getWidth() / 100);

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setSize((int) (dimw * 69), dim.height - 75);
        mainPanel.setLocation(posx, 0);

        basicpanel = new JPanel();
        basicpanel.setLayout(null);
        basicpanel.setSize((int) (dimw * 69), dim.height - 75);
        basicpanel.setLocation(posx, 0);
        basicpanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));

        //************mist result side panel start***************//
        //tempy=dimh*10*2+35;
        resultsidepanel = new JPanel();
        resultsidepanel.setLayout(null);
        resultsidepanel.setSize((int) (dimw * 14), (int) (dimh * 15));
        resultsidepanel.setLocation(posx , (int) (dimh * 10 + 20));

        Dimension test = resultsidepanel.getSize();

        plainview = new JButton("Plain View");
        plainview.setSize(test.width, (int) (dimh * 15) / 2);
        plainview.setLocation(0, 0);
        plainview.setName("plainview");
        plainview.addMouseListener(this);
        plainview.setBackground(new java.awt.Color(21, 21, 21));
        plainview.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        plainview.setForeground(new java.awt.Color(255, 255, 255));
        resultsidepanel.add(plainview);

        graphview = new JButton("Graph View");
        graphview.setSize(test.width, (int) (dimh * 15) / 2);
        graphview.setLocation(0, (int) (dimh * 15) / 2);
        graphview.setName("graphview");
        graphview.addMouseListener(this);
        graphview.setBackground(new java.awt.Color(21, 21, 21));
        graphview.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        graphview.setForeground(new java.awt.Color(255, 255, 255));
        resultsidepanel.add(graphview);

        resultsidepanel.setVisible(false);

        pane.add(resultsidepanel);

        //****************mist result side panel finshed**********//
        //***************graph side panel start******************//
        
        
        
        graphsidepanel = new JPanel();
        graphsidepanel.setLayout(null);
        graphsidepanel.setSize((int) (dimw * 14), (int) ((dimh * 15) / 2) * 7);
        graphsidepanel.setLocation(posx-1  + (int) (dimh * 25), (int) (dimh * 20));
        
        graphsidepanel.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        Dimension test1 = graphsidepanel.getSize();

        tgpa = new JButton("Term Wise GPA");
        tgpa.setSize(test1.width, (int) (dimh * 15) / 2);
        tgpa.setLocation(0, 0);
        tgpa.setName("tgpa");
        tgpa.addMouseListener(this);
        tgpa.setBackground(new java.awt.Color(21, 21, 21));
        tgpa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tgpa.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(tgpa);

        tdetails = new JButton("Subject GPA % of Student");
        tdetails.setSize(test1.width, (int) (dimh * 15) / 2);
        tdetails.setLocation(0, (int) (dimh * 15) / 2);
        tdetails.setName("tdetails");
        tdetails.addMouseListener(this);
        tdetails.setBackground(new java.awt.Color(21, 21, 21));
        tdetails.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        tdetails.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(tdetails);

        deptcgpa = new JButton("GPA Graph of DEPT");
        deptcgpa.setSize(test1.width, (int) (dimh * 15) / 2);
        deptcgpa.setLocation(0, ((int) (dimh * 15) / 2) * 2);
        deptcgpa.setName("deptgpa");
        deptcgpa.addMouseListener(this);
        deptcgpa.setBackground(new java.awt.Color(21, 21, 21));
        deptcgpa.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        deptcgpa.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(deptcgpa);

        deptgparnge = new JButton("GPA Range Search");
        deptgparnge.setSize(test1.width, (int) (dimh * 15) / 2);
        deptgparnge.setLocation(0, ((int) (dimh * 15) / 2) * 3);
        deptgparnge.setName("deptgparnge");
        deptgparnge.addMouseListener(this);
        deptgparnge.setBackground(new java.awt.Color(21, 21, 21));
        deptgparnge.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deptgparnge.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(deptgparnge);

        sbjctgparnge = new JButton("Subject Range Search");
        sbjctgparnge.setSize(test1.width, (int) (dimh * 15) / 2);
        sbjctgparnge.setLocation(0, ((int) (dimh * 15) / 2) * 4);
        sbjctgparnge.setName("sbjctrnge");
        sbjctgparnge.addMouseListener(this);
        sbjctgparnge.setBackground(new java.awt.Color(21, 21, 21));
        sbjctgparnge.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        sbjctgparnge.setForeground(new java.awt.Color(255, 255, 255));
        
        graphsidepanel.add(sbjctgparnge);
        
        
        deptgpapecentage = new JButton("GPA % of DEPT");
        deptgpapecentage.setSize(test1.width, (int) (dimh * 15) / 2);
        deptgpapecentage.setLocation(0, ((int) (dimh * 15) / 2) * 5);
        deptgpapecentage.setName("deptgpaper");
        deptgpapecentage.addMouseListener(this);
        deptgpapecentage.setBackground(new java.awt.Color(21, 21, 21));
        deptgpapecentage.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deptgpapecentage.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(deptgpapecentage);
        
        subjectgpapecentage = new JButton("Subject % of DEPT");
        subjectgpapecentage.setSize(test1.width, (int) (dimh * 15) / 2);
        subjectgpapecentage.setLocation(0, ((int) (dimh * 15) / 2) * 6);
        subjectgpapecentage.setName("subjectgpaper");
        subjectgpapecentage.addMouseListener(this);
        subjectgpapecentage.setBackground(new java.awt.Color(21, 21, 21));
        subjectgpapecentage.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        subjectgpapecentage.setForeground(new java.awt.Color(255, 255, 255));
        graphsidepanel.add(subjectgpapecentage);
        
        
        
        

        graphsidepanel.setVisible(false);

        pane.add(graphsidepanel);

        //******************Graph side panel finished****************//
        
        
        roll.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                roll.setEditable(true);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                graphsidepanel.setVisible(false);
                resultsidepanel.setVisible(false);
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        mainPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                graphsidepanel.setVisible(false);
                resultsidepanel.setVisible(false);
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                graphsidepanel.setVisible(false);
                resultsidepanel.setVisible(false);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        //**********Background Result Start***********/
        
        
        
        

        pane.add(mainPanel);
        
        
        
        
        
        
        /*
         mainPanel.removeAll();
         mainPanel.repaint();mainPanel.revalidate();
         mainPanel.add(basicpanel);mainPanel.repaint();mainPanel.revalidate();
       */  
        BasicInformation ob = new BasicInformation();
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(new JScrollPane(ob.getContentPane()));
        mainPanel.repaint();
        mainPanel.revalidate();

        //JPanel objJPanel=new JPanel(bio1);
    }

    private JTextField createTextField(int x, int y, int lx, int ly) {

        JTextField text = new JTextField();
        text.setLocation(lx, ly);
        text.setSize(x, y);
        //text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        return text;
    }

    public JButton createButton(String s, int lx, int ly, int x, int y) {

        JButton btn = new JButton(s);
        btn.setBounds(lx, ly, x, y);
        btn.setBackground(new java.awt.Color(21, 21, 21));
        //btn.setBackground(new java.awt.Color(240, 240, 240));
        btn.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        btn.setForeground(new java.awt.Color(255, 255, 255));
        //btn.setForeground(new java.awt.Color(21, 21, 21));
        btn.setFocusable(false);
        //btn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        return btn;
    }

    private JLabel createLabel(String s, int x, int y, int lx, int ly) {
        JLabel tempLabel = new JLabel(s);
        tempLabel.setSize(x, y);
        tempLabel.setLocation(lx, ly);
        tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tempLabel.setBackground(new Color(131, 180, 177));
        tempLabel.setForeground(Color.white);
        tempLabel.setOpaque(true);
        return tempLabel;
    }

    public void createshow() {
        mainFrame = new JFrame();
        //loginFrame.setSize(700,700);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //loginFrame.setLocation(dimension.width/2-loginFrame.getSize().width/2, dimension.height/2-loginFrame.getSize().height/2);
        mainFrame.setSize(dimension.width, dimension.height - 35);
        Mainpanel ob = new Mainpanel();
        ob.addcomponent(mainFrame);
        mainFrame.setTitle("MIST Result Analysis");
        mainFrame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Mainpanel ob= new Mainpanel();
                ob.createshow();
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    boolean check()
    {
        if(roll.getText().toString().isEmpty())return false;
        else return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        graphsidepanel.setVisible(false);
        resultsidepanel.setVisible(false);
        JButton btn = (JButton) e.getSource();
        String btnname = btn.getName();
        

        if (btnname.equals("basicinfo")) {
            if(check()){
            BasicInformation ob = new BasicInformation();
            ob.setinfo(Integer.parseInt(roll.getText()));
            //ob.setSize(mainPanel.getSize());
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
            }
            else
            {
                JOptionPane.showMessageDialog(mainFrame, "Please enter a ID number");
            }
        }

        if (btnname.equals("plainview")) {
            
            DatabaseHelper obj = new DatabaseHelper();
            id=obj.getid(Integer.parseInt(roll.getText().toString()));
            
            PlainResultView obj1 = new PlainResultView();
            obj1.showinfo(Integer.toString(id));
            
            
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.add(new JScrollPane(obj1));
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        
        if(btnname.equals("backresult"))
        {
            
            BackgroundResult obj1 = new BackgroundResult();
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.add(obj1);
            mainPanel.repaint();mainPanel.revalidate();
        }
        
        if(btnname.equals("scholarship"))
        {
            
            Scholarship obj1 = new Scholarship();
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.add(new JScrollPane(obj1.getContentPane()));
            mainPanel.repaint();mainPanel.revalidate();
        }
        
        if(btnname.equals("logout"))
        {
            Login obj=new Login();
           // obj.showgui();
            obj.setVisible(true);
            mainFrame.setVisible(false);
            
        }

        if (btnname.equals("setroll")) {
            roll.setEditable(false);
            DatabaseHelper obj = new DatabaseHelper();
            id=obj.getid(Integer.parseInt(roll.getText().toString()));
        
        }
        if (btnname.equals("mistresult")) {
            resultsidepanel.setVisible(true);
        }
        if (btnname.equals("graphview")) {
            resultsidepanel.setVisible(true);
            graphsidepanel.setVisible(true);
        }
        if(btnname.equals("tgpa"))
        {
            
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.repaint();
            //JComponent scene = SceneToJComponent.loadScene(ob);
            TermWiseGpaBarchart ob=new TermWiseGpaBarchart(roll.getText());
            mainPanel.add(ob);
            mainPanel.repaint();
            mainPanel.revalidate();
            
            
        }
        if(btnname.equals("deptgpa"))
        {
            
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.repaint();
            //JComponent scene = SceneToJComponent.loadScene(ob);
            TotalGpaLineChart ob=new TotalGpaLineChart();
            mainPanel.add(ob.getContentPane());
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if(btnname.equals("tdetails"))
        {
            
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.repaint();
            SubjectGPAPercentage ob = new SubjectGPAPercentage(roll.getText());
            mainPanel.add(ob.getContentPane());
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if(btnname.equals("sbjctrnge"))
        {
            
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.repaint();
            SubjectRangeView ob = new SubjectRangeView();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if(btnname.equals("deptgparnge"))
        {
            
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
            mainPanel.removeAll();
            mainPanel.repaint();
            GpaRangeView ob = new GpaRangeView();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if(btnname.equals("deptgpaper"))
        {
            GpaPerofDept ob = new GpaPerofDept();
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
            
        }
        if(btnname.equals("subjectgpaper"))
        {
            SubjectperofDept ob = new SubjectperofDept();
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if(btnname.equals("extraactivity"))
        {
            Extra ob = new Extra();
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.add(new JScrollPane(ob.getContentPane()));
            mainPanel.repaint();
            mainPanel.revalidate();
            
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        JButton btn = (JButton) e.getSource();
        String btnname = btn.getName();

        if (btnname.equals("setroll") || btnname.equals("basicinfo") || btnname.equals("backresult") || btnname.equals("scholarship")) {
            graphsidepanel.setVisible(false);
            resultsidepanel.setVisible(false);
        }
        if (btnname.equals("mistresult")) {
            resultsidepanel.setVisible(true);
        }
        if (btnname.equals("graphview")) {
            resultsidepanel.setVisible(true);
            graphsidepanel.setVisible(true);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
