package com.piotrmajcher.mbeans;

import java.util.EventObject;

import javax.swing.JPanel;

public class LightChangedEvent extends EventObject {
	
	private boolean wasTurnedOn;
	
	public LightChangedEvent(Integer lightBulbNumber, boolean wasTurnedOn) {
		super(lightBulbNumber);
		this.wasTurnedOn = wasTurnedOn;
	}
	
	public Integer getSource() {
		return (Integer) super.getSource();
	}
	
	public boolean wasTurnedOn() {
		return this.wasTurnedOn;
	}

}
