/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;


/**
 * @author Bertram
 *
 */
public class Pokemon 
{
	private String name;
	private int dexNumber;
	private Type type;

	Pokemon()
	{
		this.name = "ERROR - BLANK POKÉMON";
		this.dexNumber = 0;
	}
	
	/**
	 * @param name english name of the Pokémon
	 * @param dexNumber the Pokedex number of the Pokémon
	 */
	Pokemon(int dexNumber, String name)
	{
		super();
		this.name = name;
		this.dexNumber = dexNumber;
	}
	
	/**
	 * @return the name
	 */
	String getName() 
	{
		return name;
	}

	/**
	 * @return the pokedex number
	 */
	int getDexNumber() 
	{
		return dexNumber;
	}
	
	/**
	 * @return the type(s) of the pokemon
	 */
	Type getType()
	{
		return type;
	}
}
