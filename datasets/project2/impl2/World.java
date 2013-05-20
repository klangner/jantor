package impl2;

import impl1.CountryImpl;

public class World {

	private String message = "My message";
	
	public static void main(String[] args) {
		HelloWorld app = new HelloWorld();
		app.sendMessage();
	}
	
	private void sendMessage(){
		System.out.println(message);
	}

	private int getCount(){
		return 12;
	}
}
