package com.piotrmajcher.mbeans;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class LightBulbsPanel extends JPanel implements ILightBulbsPanel{
	
	private static final int ROWS = 3;
	private static final int COLUMNS = 2;
	private static final int LIGHTBULBS = ROWS * COLUMNS;
	private static final Dimension LIGHTBULB_DIMENSION = new Dimension(200, 200);
	private static final Color OFF_COLOR = Color.LIGHT_GRAY;
	private static final Color OFF_COLOR_HOVER = Color.GRAY;
	private static final Color ON_COLOR = Color.YELLOW;
	private static final Color ON_COLOR_HOVER = Color.ORANGE;
	
	
	private List<JPanel> lightbulbsList;
	private List<LightChangedEventListener> lightChangedEventListenerList;
	
	public LightBulbsPanel() {
		super();
		
		setLayout(new GridLayout(ROWS,COLUMNS));
		lightbulbsList = new ArrayList<JPanel>(LIGHTBULBS);
		lightChangedEventListenerList = new LinkedList<LightChangedEventListener>();
		
		for (int i = 0; i < LIGHTBULBS; i++) {
			JPanel lightbulb = new JPanel();
			lightbulb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lightbulb.setPreferredSize(LIGHTBULB_DIMENSION);
			lightbulb.setBackground(OFF_COLOR);
			
			lightbulb.addMouseListener(getMouseListener());
			
			add(lightbulb);
			lightbulbsList.add(lightbulb);
		}
	}
	
	private MouseListener getMouseListener() {
		return new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {	
			}
			
			public void mouseExited(MouseEvent e) {
				changeColorMouseExited((JPanel)e.getSource());
				
			}
			
			public void mouseEntered(MouseEvent e) {
				changeColorMouseEntered((JPanel)e.getSource());
			}
			
			public void mouseClicked(MouseEvent e) {
				switchLightOnOff((JPanel)e.getSource());
			}
		};
	}
	
	private void changeColorMouseEntered(JPanel lightbulb) {
		Color currentColor = lightbulb.getBackground();
			
		if (currentColor.equals(OFF_COLOR)) {
			lightbulb.setBackground(OFF_COLOR_HOVER);
		} else if (currentColor.equals(ON_COLOR)) {
			lightbulb.setBackground(ON_COLOR_HOVER);
		} 
		repaint();
	}
	
	private void changeColorMouseExited(JPanel lightbulb) {
		Color currentColor = lightbulb.getBackground();
			
		if (currentColor.equals(OFF_COLOR_HOVER)) {
			lightbulb.setBackground(OFF_COLOR);
		} else if (currentColor.equals(ON_COLOR_HOVER)) {
			lightbulb.setBackground(ON_COLOR);
		} 
		repaint();
	}
	
	private void switchLightOnOff(JPanel lightbulb) {
		Color currentColor = lightbulb.getBackground();
			
		if (currentColor.equals(OFF_COLOR)) {
			lightbulb.setBackground(ON_COLOR);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), true);
		} else if (currentColor.equals(ON_COLOR)) {
			lightbulb.setBackground(OFF_COLOR);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), false);
		} else if (currentColor.equals(OFF_COLOR_HOVER)) {
			lightbulb.setBackground(ON_COLOR_HOVER);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), true);
		} else if (currentColor.equals(ON_COLOR_HOVER)) {
			lightbulb.setBackground(OFF_COLOR_HOVER);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), false);
		}
		repaint();
	}
	
	private void switchLightOn(JPanel lightbulb) {
		Color currentColor = lightbulb.getBackground();
		
		if (currentColor.equals(OFF_COLOR)) {
			lightbulb.setBackground(ON_COLOR);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), true);
		} else if (currentColor.equals(OFF_COLOR_HOVER)) {
			lightbulb.setBackground(ON_COLOR_HOVER);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), true);
		}
		
		repaint();
	}
	
	private void switchLightOff(JPanel lightbulb) {
		Color currentColor = lightbulb.getBackground();
		
		if (currentColor.equals(ON_COLOR)) {
			lightbulb.setBackground(OFF_COLOR);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), false);
		} else if (currentColor.equals(ON_COLOR_HOVER)) {
			lightbulb.setBackground(OFF_COLOR_HOVER);
			generateLightChangedEvent(lightbulbsList.indexOf(lightbulb), false);
		}
		repaint();
	}
	
	public void changeLight(int lightbulbNumber) {
		if (lightbulbNumber >= 0 && lightbulbNumber < LIGHTBULBS) {
			switchLightOnOff(lightbulbsList.get(lightbulbNumber));
		}
	}
	
	public void turnLightOn(int lightBulbNumber) {
		if (lightBulbNumber >= 0 && lightBulbNumber < LIGHTBULBS) {
			switchLightOn(lightbulbsList.get(lightBulbNumber));
		}
	}
	
	public void turnLightOff(int lightBulbNumber) {
		if (lightBulbNumber >= 0 && lightBulbNumber < LIGHTBULBS) {
			switchLightOff(lightbulbsList.get(lightBulbNumber));
		}
	}

	public void addLightchangedEventListener(LightChangedEventListener listener) {
		this.lightChangedEventListenerList.add(listener);
	}
	
	private void generateLightChangedEvent(Integer lightBulbNumber, boolean wasTurnedOn) {
		LightChangedEvent event = new LightChangedEvent(lightBulbNumber, wasTurnedOn);
		for (LightChangedEventListener listener : lightChangedEventListenerList) {
			listener.onLightChangedEvent(event);
		}
	}
}
