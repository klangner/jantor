package com.klangner.jantor.modularity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;
import com.klangner.ast.IParser;
import com.klangner.ast.java.JavaASTParser;
import com.klangner.jantor.modularity.DependencyAnalyzer;
import com.klangner.jantor.modularity.DependencyAnalyzer.Dependency;

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
		Collection<IModule> modules = analyzer.getModules();
		assertEquals(2, modules.size());
	}

	@Test
	public void packageDependency() {
		INode ast = getProjectAST("project2");
		DependencyAnalyzer analyzer = new DependencyAnalyzer(ast);
		Collection<Dependency> dependency = analyzer.getPackageDependencies();
		assertEquals(2, dependency.size());
	}

	@Test
	public void moduleDependency() {
		INode ast = getProjectAST("project2");
		DependencyAnalyzer analyzer = new DependencyAnalyzer(ast);
		List<IModule> modules = analyzer.getModules();
		assertEquals(2, modules.size());
		IModule moduleNode;
		if(modules.get(0).getText().contains("impl1")){
			moduleNode = modules.get(0); 
		}
		else{
			moduleNode = modules.get(1);
		}
		
		Collection<String> moduleDependencies = analyzer.getModuleDependencies(moduleNode);
		assertEquals(1, moduleDependencies.size());
		assertTrue(moduleDependencies.contains("impl2"));
	}

}
