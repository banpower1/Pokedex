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

	/**
	 * Creates a list of the weaknesses for this type
	 * @return a list of the types and their effectivity against this type
	 */
	ArrayList<TypeEfficacy> getDefences()
	{
		ArrayList<TypeEfficacy> defences = new ArrayList<TypeEfficacy>();
		
		DatabaseController dbc = new DatabaseController("veekun-pokedex.sqlite");
		ArrayList<ArrayList<Object>> typeEfficacy = dbc.request(
					"SELECT t1.identifier, t2.identifier, te.damage_factor " +
					"FROM types AS t1, types AS t2, type_efficacy AS te " + 
					"WHERE t1.id=te.damage_type_id AND t2.id=te.target_type_id " +
					"AND t2.identifier='" + type1 + "'");
		if(type2 !=null)
		{
			typeEfficacy.addAll(dbc.request("SELECT t1.identifier, t2.identifier, te.damage_factor " +
					"FROM types AS t1, types AS t2, type_efficacy AS te " + 
					"WHERE t1.id=te.damage_type_id AND t2.id=te.target_type_id " +
					"AND t2.identifier='" + type2 + "'"));
		}
		for(ArrayList<Object> row:typeEfficacy)
		{
			String attackingType = (String)row.get(0);
			String defendingType = (String)row.get(1);
			int efficacy = (int)row.get(2);
			if (defendingType.equals(type1) || defendingType.equals(type2));
			{
				defences.add(new TypeEfficacy(attackingType, efficacy));
			}
		}
		
		return removeNormals(calculateMultiMathcup(defences));
	}
	
	/**
	 * used by getDefences() to multiply all multiples
	 * @param input a full list of defensive matchups for a dual typed pokemon
	 * @return a list of defensive matchups for the dual type in question
	 */
	private ArrayList<TypeEfficacy> calculateMultiMathcup(ArrayList<TypeEfficacy> input)
	{
		ArrayList<TypeEfficacy> output = new ArrayList<TypeEfficacy>();
		for (int i = 0; i < input.size(); i++)
		{
			boolean foundMatch = false;
			for (int j = i + 1; j < input.size(); j++)
			{
				//if element i.type() == j.type()
				if (input.get(i).getType().getType1().equals(input.get(j).getType().getType1()))
				{
					//add TypeEfficacy which is named same and is product of the two
					output.add(new TypeEfficacy(input.get(i).getType().getType1(), input.get(i).getEfficacy() * input.get(j).getEfficacy()));
					foundMatch = true;
				}
			}
			if(!foundMatch)
			{
				output.add(input.get(i));
			}
		}
		
		return output;
	}
	
	/**
	 * Used by getDefences to remove all "boring" defensive matchups (those were the defender takes 100% damage from the attacks)
	 * @param input a list of defensive matchups containing "boring" entries
	 * @return a list of defensive matchups without any "junk" information
	 */
	private ArrayList<TypeEfficacy> removeNormals(ArrayList<TypeEfficacy> input)
	{
		ArrayList<TypeEfficacy> output = new ArrayList<TypeEfficacy>();
		
		for(TypeEfficacy i:input)
		{
			if(!(i.getEfficacy() == 1.0))
			{
				output.add(i);
			}
		}
		
		return output;
	}
	
	/**
	 * To get a printable string of the types
	 * @return a string suited for printing the type
	 */
	String getPrintable()
	{
		if (type2 == null)
		{
			return Utilities.capitalize(type1);
		}
		else
		{
			return Utilities.capitalize(type1) + " " + Utilities.capitalize(type2);
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
