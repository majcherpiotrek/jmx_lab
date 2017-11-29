package com.piotrmajcher.mbeans;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class LightSequencePlayerTest extends TestCase {
	
	@Test
	public void sequenceDescriptionParserTest() {
		String testSequenceDescription = "0:3:+;0:5:-;0:7:+;1:1:+;2:5:+;3:6:+;3:8:-";
		LightSequencePlayer player = new LightSequencePlayer(null);
		
		Map<Integer, LightSequencePlayer.Sequence> result = player.parseSequenceDescriptionToSequenceMap(testSequenceDescription);
		System.out.println(result.toString());
		assertTrue(result.size() == 4);
		assertTrue(result.get(0).commands.size() == 3);
		assertTrue(result.get(1).commands.size() == 1);
		assertTrue(result.get(2).commands.size() == 1);
		assertTrue(result.get(3).commands.size() == 2);
	}
}
