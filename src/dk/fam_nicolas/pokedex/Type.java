/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;
import java.util.ArrayList;

/**
 * @author Bertram
 *
 */
public class Type
{
	private String type1;
	private String type2;
	
	/**
	 * For pokemons with a single type
	 * @param type1 the primary type
	 */
	Type(String type1)
	{
		this(type1, null);
	}
	
	/**
	 * For pokemons with two types
	 * @param type1 the primary type
	 * @param type2 the secondary type
	 */
	Type(String type1, String type2)
	{
		this.type1 = type1;
		this.type2 = type2;
	}

	ArrayList<TypeEfficacy> getDefences()
	{
		ArrayList<TypeEfficacy> defences = new ArrayList<TypeEfficacy>();
		
		DatabaseController dbc = new DatabaseController("veekun-pokedex.sqlite");
		ArrayList<ArrayList<Object>> typeEfficacy = dbc.request(
					"SELECT t1.identifier, t2.identifier, te.damage_factor " +
					"FROM types AS t1, types AS t2, type_efficacy AS te " + 
					"WHERE t1.id=te.damage_type_id AND t2.id=te.target_type_id");
		for(ArrayList<Object> row:typeEfficacy)
		{
			String attackingType = (String)row.get(0);
			String defendingType = (String)row.get(1);
			int efficacy = (int)row.get(2);
			if (defendingType == type1 || defendingType == type2)
			{
				defences.add(new TypeEfficacy(attackingType, efficacy));
			}
		}
		
		return calculateMultiMathcup(defences);
	}
	
	private ArrayList<TypeEfficacy> calculateMultiMathcup(ArrayList<TypeEfficacy> input)  //TODO: finish this properly
	{
		ArrayList<TypeEfficacy> output = new ArrayList<TypeEfficacy>();
		
		return new ArrayList<TypeEfficacy>();
	}
	
	/**
	 * To get a printable string of the types
	 * @return a string suited for printing the type
	 */
	String getPrintable()
	{
		if (type2 == null)
		{
			return type1;
		}
		else
		{
			return type1 + " " + type2;
		}
	}
	
	
	/**
	 * @return the primary type
	 */
	String getType1() 
	{
		return type1;
	}
	
	
	/**
	 * @return the secondary type
	 */
	String getType2()
	{
		return type2;
	}
}
