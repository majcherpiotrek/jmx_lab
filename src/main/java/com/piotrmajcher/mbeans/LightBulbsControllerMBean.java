package com.piotrmajcher.mbeans;

public interface LightBulbsControllerMBean {

	public void changeLight(int lightBulbNumber);
	
	public void turnLightOn(int lightBulbnumber);
	
	public void turnLightOff(int lightBulbnumber);
	
	public void playSequence(String sequenceDescription);
	
	public void playRandomSequence();
}
