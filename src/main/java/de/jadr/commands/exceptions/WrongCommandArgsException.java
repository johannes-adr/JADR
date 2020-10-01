package de.jadr.commands.exceptions;

public class WrongCommandArgsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongCommandArgsException(String usage) {
		super(usage);
	}
	
}
