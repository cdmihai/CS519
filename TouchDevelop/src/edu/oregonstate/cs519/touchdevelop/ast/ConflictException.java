package edu.oregonstate.cs519.touchdevelop.ast;

public class ConflictException extends Exception {
	
	private String update;

	public ConflictException(String update) {
		this.update = update;
	}
	
	public String getProblemUpdate() {
		return update;
	}

}
