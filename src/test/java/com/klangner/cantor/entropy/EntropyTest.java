package com.klangner.cantor.entropy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.klangner.cantor.entropy.Entropy;

public class EntropyTest {

	private static String DATASET_PATH = "datasets/";
	
	
	@Test
	public void entropy0() {
		Entropy entropy = new Entropy();
		double value = entropy.calculateFileEntropy(DATASET_PATH+"project1/Entropy0.java");
		assertEquals(0, value, 0.01);
	}

	@Test
	public void entropy1() {
		Entropy entropy = new Entropy();
		double value = entropy.calculateFileEntropy(DATASET_PATH+"project1/Entropy1.java");
		assertEquals(1, value, 0.1);
	}

}
