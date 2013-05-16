package com.klangner.cantor.modularity;

import java.util.ArrayList;
import java.util.List;

import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;

public class DependencyAnalyzer {
	
	public class Dependency{
		String source;
		String target;
	}

	private List<IPackage> packages = new ArrayList<IPackage>();
	private List<IModule> modules = new ArrayList<IModule>();
	private List<Dependency> dependencies = new ArrayList<Dependency>();
	
	
	public DependencyAnalyzer(INode ast){
		scanTree(ast);
	}
	
	private void scanTree(INode rootNode) {
		
		if(rootNode instanceof IPackage){
			packages.add((IPackage) rootNode);
		}
		else if(rootNode instanceof IModule){
			modules.add((IModule) rootNode);
		}
		for(int i = 0; i < rootNode.getChildCount(); i++){
			scanTree(rootNode.getChild(i));
		}
	}

	public List<IPackage> getPackages(){
		return packages;
	}
	
	public List<IModule> getModules(){
		return modules;
	}
	
	public List<Dependency> getPackageDependencies(){
		return dependencies;
	}
}
