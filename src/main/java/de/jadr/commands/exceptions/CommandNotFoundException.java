package de.jadr.commands.exceptions;

import java.beans.ExceptionListener;

public class CommandNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandNotFoundException(String error) {
		super(error);
	}
	public CommandNotFoundException() {
	}
	
	

}
