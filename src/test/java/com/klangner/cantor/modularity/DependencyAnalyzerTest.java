package com.klangner.cantor.modularity;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;
import com.klangner.ast.IParser;
import com.klangner.ast.java.JavaASTParser;

public class DependencyAnalyzerTest {

	private static String DATASET_PATH = "datasets/";
	
	
	@Test
	public void packagesCount() {
		INode ast = getProjectAST("project2");
		DependencyAnalyzer analyzer = new DependencyAnalyzer(ast);
		List<IPackage> packages = analyzer.getPackages();
		assertEquals(4, packages.size());
	}

	private INode getProjectAST(String projectName) {
		IParser parser = new JavaASTParser();
		INode rootNode = parser.parseProject(DATASET_PATH+projectName);
		return rootNode;
	}

	@Test
	public void moduleCount() {
		INode ast = getProjectAST("project2");
		DependencyAnalyzer analyzer = new DependencyAnalyzer(ast);
		List<IModule> modules = analyzer.getModules();
		assertEquals(2, modules.size());
	}

}
