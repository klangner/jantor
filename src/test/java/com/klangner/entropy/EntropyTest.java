package com.klangner.entropy;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntropyTest {

	private static String DATASET_PATH = "src/test/datasets/";
	
	
	@Test
	public void entropy0() {
		Entropy entropy = new Entropy();
		float value = entropy.calculateFileEntropy(DATASET_PATH+"project1/Entropy0.java");
		assertEquals(0, value, 0.01);
	}

	@Test
	public void entropy1() {
		Entropy entropy = new Entropy();
		float value = entropy.calculateFileEntropy(DATASET_PATH+"project1/Entropy1.java");
		assertEquals(1, value, 0.1);
	}

}
