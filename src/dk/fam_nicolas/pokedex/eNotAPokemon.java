/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;

/**
 * @author Bertram
 * no idea what i'm doing here, but i trying to make a proper exception to fit with the rest of the package
 */
class eNotAPokemon extends Exception
{
	eNotAPokemon()
	{
		super();
	}
	
	eNotAPokemon(String message)
	{
		super(message);
	}
	
	eNotAPokemon(String message, Throwable cause)
	{
		super(message, cause);
	}
}
