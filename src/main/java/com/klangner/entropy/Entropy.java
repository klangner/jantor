package com.klangner.entropy;

import com.klangner.ast.INode;
import com.klangner.ast.IParser;
import com.klangner.ast.java.JavaASTParser;


public class Entropy {
	
	public double calculateFileEntropy(String filePath){
		double score = 0;
		IParser parser = new JavaASTParser();
		INode rootNode = parser.parseFile(filePath);
		int childCount = rootNode.getChildCount();
		score = Math.log(childCount)/Math.log(2);
		return score;
	}
}
