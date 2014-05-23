/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;

import java.util.ArrayList;


/**
 * @author Bertram
 *
 */
public class Pokemon
{
	private String name;
	private int dexNumber;
	private Type type;

	/**
	 * constructor that looks for the pokemon in the database using name
	 * @param name english name of the pokemon
	 */
	Pokemon(String name) throws eNotAPokemon
	{
		DatabaseController pkmndbc = new DatabaseController("veekun-pokedex.sqlite");
		ArrayList<ArrayList<Object>> request = pkmndbc.request("SELECT id, identifier FROM pokemon WHERE identifier='" + name + "'");
		if(request.size() == 0)
		{
			throw new eNotAPokemon("Database returned empty list");
		}
		this.dexNumber = (int)request.get(0).get(0);
		this.name = (String)request.get(0).get(1);
		fetchType();
	}
	
	/**
	 * @param name english name of the Pokémon
	 * @param dexNumber the Pokedex number of the Pokémon
	 */
	Pokemon(int dexNumber, String name)
	{
		this.name = name;
		this.dexNumber = dexNumber;
	}
	
	/**
	 * fetches the type(s) for the pokemon and saves it in the pokemon
	 */
	void fetchType()
	{
		DatabaseController typedbc = new DatabaseController("veekun-pokedex.sqlite");
		ArrayList<ArrayList<Object>> request = typedbc.request("SELECT t.identifier, pt.slot " + 
					"FROM pokemon AS p, types AS t, pokemon_types AS pt " + 
					"WHERE p.identifier='" + this.name + "' AND p.id=pt.pokemon_id AND pt.type_id=t.id");
		
		//Check if the pokemon has 1 or two types
		if(request.size() == 1)
		{
			this.type = new Type((String)request.get(0).get(0));
		}
		else
		{
			//Check if index 0 type is primary type and place types accordingly
			if((int)request.get(0).get(1) == 1)
			{
				this.type = new Type((String)request.get(0).get(0), (String)request.get(1).get(0));
			}
			else
			{
				
				this.type = new Type((String)request.get(1).get(0), (String)request.get(0).get(0));
			}
			
		}
		
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
		if(this.type == null)
		{
			fetchType();
		}
		return type;
	}
}
