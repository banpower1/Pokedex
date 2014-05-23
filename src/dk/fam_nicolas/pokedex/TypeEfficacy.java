/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;

/**
 * @author Bertram
 *
 */
public class TypeEfficacy
{
	private Type type;
	private float efficacy;
		
	/**
	 * for creating the TypeEffacacy with the exact things
	 * @param type the name of the type
	 * @param efficacy the exact efficacy where 1 == 100%
	 */
	TypeEfficacy(String type, float efficacy)
	{
		this.type = new Type(type);
		this.efficacy = efficacy;
	}
	

	/**
	 * For constructing TypeEfficacy using the int stored in the database
	 * @param name the name of the type
	 * @param efficacy the efficacy in percentages 100 == 100%
	 */
	TypeEfficacy(String type, int efficacy)
	{
		Integer effecacyAsInteger = efficacy;
		this.efficacy = effecacyAsInteger.floatValue() / 100f;		
		this.type = new Type(type);
	}
	
	String getPrintable()
	{
		return "" + type.getType1() + efficacy;  
	}
	
	Type getType()
	{
		return type;
	}

	float getEfficacy()
	{
		return efficacy;
	}
}
