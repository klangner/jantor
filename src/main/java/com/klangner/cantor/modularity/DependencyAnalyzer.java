package com.klangner.cantor.modularity;

import java.util.ArrayList;
import java.util.List;

import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;

public class DependencyAnalyzer {

	List<IPackage> packages = new ArrayList<IPackage>();
	List<IModule> modules = new ArrayList<IModule>();
	
	
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
}
