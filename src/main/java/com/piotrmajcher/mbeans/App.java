package com.piotrmajcher.mbeans;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame mainFrame = new JFrame("Lightbulbs");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LightBulbsPanel lightBulbsPanel = new LightBulbsPanel();
        
        mainFrame.setContentPane(lightBulbsPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
     
  	    MyBeanAgent agent = new MyBeanAgent(lightBulbsPanel);
  	    System.out.println("MyBeanAgent is running...");
    }
}
