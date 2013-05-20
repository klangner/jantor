package com.klangner.cantor;

import java.util.Collection;
import java.util.List;

import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;
import com.klangner.ast.IParser;
import com.klangner.ast.java.JavaASTParser;
import com.klangner.cantor.modularity.DependencyAnalyzer;

public class CantorApp {

	public static void main(String[] args) {
		
		if(args.length >= 2){
			CantorApp app = new CantorApp();
			String command = args[0];
			String srcPath = args[1];
			app.run(command, srcPath);
		}
		else{
			printHelp();
		}
	}

	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("cantor.jar <command> src_path");
	}

	private void run(String command, String srcPath) {
		
		if(command.equals("list_modules")){
			listModules(srcPath);
		}
		else if(command.equals("list_packages")){
			listPackages(srcPath);
		}
		else if(command.equals("list_dependencies")){
			listDependecies(srcPath);
		}
		else{
			printHelp();
		}
	}

	private void listModules(String srcPath) {
		DependencyAnalyzer analyzer = getDependencyAnalyzer(srcPath);
		List<IModule> modules = analyzer.getModules();
		System.out.println("Modules:");
		for(IModule module: modules){
			System.out.println(module.getText());
		}
	}

	private DependencyAnalyzer getDependencyAnalyzer(String srcPath) {
		IParser parser = new JavaASTParser();
		INode ast = parser.parseProject(srcPath);
		DependencyAnalyzer analyzer = new DependencyAnalyzer(ast);
		return analyzer;
	}

	private void listPackages(String srcPath) {
		DependencyAnalyzer analyzer = getDependencyAnalyzer(srcPath);
		List<IPackage> packages = analyzer.getPackages();
		System.out.println("Packages:");
		for(IPackage p : packages){
			System.out.println(p.getText());
		}
	}

	private void listDependecies(String srcPath) {
		DependencyAnalyzer analyzer = getDependencyAnalyzer(srcPath);
		Collection<IModule> modules = analyzer.getModules();
		for(IModule module : modules){
			System.out.println("Module: " + module.getName());
			Collection<String> dependecies = analyzer.getModuleDependencies(module);
			for(String dependency : dependecies){
				System.out.println("- " + dependency);
			}
			System.out.println();
		}
	}

}
