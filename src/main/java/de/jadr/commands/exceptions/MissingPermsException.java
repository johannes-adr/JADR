package de.jadr.commands.exceptions;

public class MissingPermsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingPermsException(String usage) {
		super(usage);
	}
	
}
