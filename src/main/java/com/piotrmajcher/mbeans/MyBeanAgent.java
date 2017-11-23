package com.piotrmajcher.mbeans;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MyBeanAgent {
	 private MBeanServer mbs = null;

	   public MyBeanAgent(LightBulbsPanel lightBulbsPanel) {

	      // Get the platform MBeanServer
	       mbs = ManagementFactory.getPlatformMBeanServer();

	      LightBulbsController lightBulbsController = new LightBulbsController();
	      lightBulbsController.setLightBulbsPanel(lightBulbsPanel);
	      ObjectName name = null;

	      try {
	         // Uniquely identify the MBeans and register them with the platform MBeanServer 
	         name = new ObjectName("LightBulbsController:name=LightBulbsController");
	         mbs.registerMBean(lightBulbsController, name);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
}
