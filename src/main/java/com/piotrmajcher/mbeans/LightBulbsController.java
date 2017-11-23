package com.piotrmajcher.mbeans;

public class LightBulbsController implements LightBulbsControllerMBean {
	
	private LightBulbsPanel lightBulbsPanel;
	
	public LightBulbsController() {
		
	}
	
	public void changeLight(int lightBulbNumber) {
		if (lightBulbsPanel != null) {
			lightBulbsPanel.changeLight(lightBulbNumber);
		}
	}
	
	public void setLightBulbsPanel(LightBulbsPanel lightBulbsPanel) {
		this.lightBulbsPanel = lightBulbsPanel;
	}

}
