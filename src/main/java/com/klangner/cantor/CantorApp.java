package com.klangner.cantor;

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
		System.out.println("Command: " + command);
		System.out.println("src: " + srcPath);
	}

}
