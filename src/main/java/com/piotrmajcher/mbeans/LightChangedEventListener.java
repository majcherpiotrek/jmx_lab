package com.piotrmajcher.mbeans;

import java.util.EventListener;

public interface LightChangedEventListener extends EventListener {
	void onLightChangedEvent(LightChangedEvent e);
}
