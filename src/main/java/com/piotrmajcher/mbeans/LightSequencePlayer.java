package com.piotrmajcher.mbeans;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LightSequencePlayer {
	
	private static final String COMMANDS_DELIMITER = ";";
	private static final String COMMAND_ELEMENTS_DELIMITER = ":";
	
	private Map<Integer, Sequence> sequenceMap;
	private ILightBulbsPanel lightBulbsPanel;
	
	public LightSequencePlayer(ILightBulbsPanel lightBulbsPanel) {
		this.lightBulbsPanel = lightBulbsPanel;
	}
	
	protected class Command {
		long stateDurationSeconds;
		String stateSymbol; // + light on, - light off;
		
		@Override
		public String toString() {
			return "Command [callSecond=" + stateDurationSeconds + ", operation=" + stateSymbol + "]";
		}
	}
	
	protected class Sequence {
		List<Command> commands;
		
		Sequence() {
			this.commands = new LinkedList<LightSequencePlayer.Command>();
		}

		@Override
		public String toString() {
			return "Sequence [commands=" + commands + "]";
		}
	}
	
	public void playRandomSequence() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 100; j++) {
				int seconds = random.nextInt(4);
				String stateSymbol = j%2 == 0 ? "+" : "-";
				sb.append(i);
				sb.append(":");
				sb.append(seconds);
				sb.append(":");
				sb.append(stateSymbol);
				sb.append(";");
			}
		}
		
		playSequence(sb.toString());
	}
	
	
	public void playSequence(String sequenceDescription) {
		/*
		 * Example sequence: 0:5:+;1:3:-;4:3:-
		 * Means : lightbulb 0 : at second 5: turn on; lightbulb 1 : at second 3: turn off
		 */
		
		if (lightBulbsPanel != null && sequenceDescription != null) {
			sequenceMap = parseSequenceDescriptionToSequenceMap(sequenceDescription);
			Integer[] lightBulbsNumbers = new Integer[sequenceMap.size()];
			sequenceMap.keySet().toArray(lightBulbsNumbers);
			for (final Integer lightBulbNum : lightBulbsNumbers) {
				final Sequence seq = sequenceMap.get(lightBulbNum);
				if (seq != null) {
					Thread t = new Thread(new Runnable() {
						
						public void run() {
							for (Command command : seq.commands) {
								System.out.println("Executing command for lightbulb  " + lightBulbNum + " :" + command.toString());
								if (command.stateSymbol.equals("+")) {
									System.out.println("Turning light " + lightBulbNum + " on");
									lightBulbsPanel.turnLightOn(lightBulbNum);
								} else if (command.stateSymbol.equals("-")) {
									System.out.println("Turning light " + lightBulbNum + " off");
									lightBulbsPanel.turnLightOff(lightBulbNum);
								}
								try {
									Thread.sleep(command.stateDurationSeconds * 1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
					});
					
					t.start();
				}
				
				
			}
			
		} else {
			System.out.println("Cannot plasy sequence!");
		}
	}



	protected Map<Integer, Sequence> parseSequenceDescriptionToSequenceMap(String sequenceDescription) {
		Map<Integer, Sequence> resultSequenceMap = new HashMap();
		String[] commandsArray = sequenceDescription.split(COMMANDS_DELIMITER);
		if (commandsArray.length > 0) {
			for (int i = 0; i < commandsArray.length; i++) {
				String[] elements = commandsArray[i].split(COMMAND_ELEMENTS_DELIMITER);
				if (elements.length == 3) {
					Integer lightBulbNumber = Integer.valueOf(elements[0]);
					Command command = new Command();
					command.stateDurationSeconds = Long.valueOf(elements[1]);
					command.stateSymbol = elements[2];
					
					if (!resultSequenceMap.containsKey(lightBulbNumber)) {
						Sequence sequence = new Sequence();
						sequence.commands.add(command);
						resultSequenceMap.put(lightBulbNumber, sequence);
					} else {
						resultSequenceMap.get(lightBulbNumber).commands.add(command);
					}
				}
			}
		}
		
		return resultSequenceMap;
	}
}
