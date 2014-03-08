package com.klangner.jantor.modularity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.klangner.ast.ICompilationUnit;
import com.klangner.ast.IImport;
import com.klangner.ast.IModule;
import com.klangner.ast.INode;
import com.klangner.ast.IPackage;

public class DependencyAnalyzer {
	
	public class Dependency{
		String source;
		String target;
		
		String getKey(){ 
			return source + ":" + target;
		}
	}

	private List<IPackage> packages = new ArrayList<IPackage>();
	private List<IModule> modules = new ArrayList<IModule>();
	private HashMap<String, Dependency> dependencies = new HashMap<String,Dependency>();
	
	
	public DependencyAnalyzer(INode ast){
		scanTree(ast);
	}
	
	private void scanTree(INode rootNode) {
		
		if(rootNode instanceof IPackage){
			processPackage((IPackage) rootNode);
		}
		else if(rootNode instanceof IModule){
			processModule((IModule) rootNode);
		}
		else if(rootNode instanceof ICompilationUnit){
			processCompilationUnit((ICompilationUnit) rootNode);
		}
		
		for(int i = 0; i < rootNode.getChildCount(); i++){
			scanTree(rootNode.getChild(i));
		}
	}

	private void processPackage(IPackage rootNode) {
		packages.add(rootNode);
	}

	private void processModule(IModule rootNode) {
		modules.add(rootNode);
	}

	private void processCompilationUnit(ICompilationUnit rootNode) {
		for(int i = 0; i < rootNode.getChildCount(); i++){
			if(rootNode.getChild(i) instanceof IImport){
				IImport importNode = (IImport) rootNode.getChild(i);
				Dependency dependency = new Dependency();
				dependency.source = getLastPackageName();
				dependency.target = importNode.getPackageName();
				dependencies.put(dependency.getKey(), dependency);
			}
		}
	}

	private String getLastPackageName() {
		if(packages.size() > 0){
			return packages.get(packages.size()-1).getText();
		}
		return "";
	}

	public List<IPackage> getPackages(){
		return packages;
	}
	
	public List<IModule> getModules(){
		return modules;
	}
	
	public Collection<Dependency> getPackageDependencies(){
		return dependencies.values();
	}
	
	public Collection<String> getModuleDependencies(IModule moduleNode){
		HashSet<String> moduleSet = new HashSet<String>();
		Collection<String> moduleDependencies = new ArrayList<String>();
		for(Dependency dependency : dependencies.values()){
			if(dependency.source.startsWith(moduleNode.getName())){
				if(!moduleSet.contains(dependency.target)){
					moduleSet.add(dependency.target);
					moduleDependencies.add(dependency.target);
				}
			}
		}
		return moduleDependencies;
	}
}
