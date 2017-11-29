package com.piotrmajcher.mbeans;

public interface ILightBulbsPanel {
	
	public void changeLight(int lightbulbNumber);
	
	public void turnLightOn(int lightbulbNumber);
	
	public void turnLightOff(int lightbulbNumber);
	
	public void addLightchangedEventListener(LightChangedEventListener listener);
}
