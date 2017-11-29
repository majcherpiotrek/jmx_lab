package com.piotrmajcher.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class LightBulbsController extends NotificationBroadcasterSupport implements LightBulbsControllerMBean {
	
	private LightBulbsPanel lightBulbsPanel;
	private LightSequencePlayer lightSequencePlayer;
	private long SEQUENCE_NUMBER = 1;
	
	public LightBulbsController() {
		
	}
	
	public void changeLight(int lightBulbNumber) {
		if (lightBulbsPanel != null) {
			lightBulbsPanel.changeLight(lightBulbNumber);
		}
	}
	
	public void turnLightOn(int lightBulbNumber) {
		if (lightBulbsPanel != null) {
			lightBulbsPanel.turnLightOn(lightBulbNumber);
		}
	}
	
	public void turnLightOff(int lightBulbNumber) {
		if (lightBulbsPanel != null) {
			lightBulbsPanel.turnLightOff(lightBulbNumber);
		}
	}
	
	
	public void setLightBulbsPanel(LightBulbsPanel lightBulbsPanel) {
		this.lightBulbsPanel = lightBulbsPanel;
		this.lightSequencePlayer = new LightSequencePlayer(lightBulbsPanel);
		this.lightBulbsPanel.addLightchangedEventListener(new LightChangedEventListener() {
			
			public void onLightChangedEvent(LightChangedEvent e) {
				createAndSendNotification(e);	
			}
		});
	}

	protected void createAndSendNotification(LightChangedEvent e) {
		Notification notification = new Notification(
				Notification.class.toString(), 
				e.getSource(), 
				SEQUENCE_NUMBER++, 
				System.currentTimeMillis(), 
				"Lightbulb nr " + e.getSource() + (e.wasTurnedOn() ? " was turned on." : " was turned off"));
		super.sendNotification(notification);
	}

	public void playSequence(String sequenceDescription) {
		if (lightBulbsPanel != null && lightSequencePlayer != null) {
			lightSequencePlayer.playSequence(sequenceDescription);
		}
	}

	public void playRandomSequence() {
		if (lightBulbsPanel != null && lightSequencePlayer != null) {
			lightSequencePlayer.playRandomSequence();
		}
	}
	
	@Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
        	Notification.class.toString()
        };

        String name = "Light changed notification";
        String description = "Light changed in the lights panel controlled by this MBean";
        MBeanNotificationInfo info = 
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

}
