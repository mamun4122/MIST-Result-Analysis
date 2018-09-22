/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

//import splash.*;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String args[])throws Exception
    {
        Splash s=new Splash();
        s.setVisible(true);
      // UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        Thread t=Thread.currentThread();
        t.sleep(3000);
        s.dispose();
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                new Login().setVisible(true);
            }
        });
    }
 
}