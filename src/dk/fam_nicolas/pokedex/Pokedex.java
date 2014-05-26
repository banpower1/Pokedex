/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;
import java.util.*;


/**
 * @author Bertram
 *
 */
public class Pokedex
{
	private static DatabaseController dbc;
	private Scanner scanIn;
	private String userInput;
	private String[] userInputArray;
	private boolean keepGoing;
	
	/**
	 * The Pokedex in text format, handles all input and calls for the pokedex
	 */
	public Pokedex()
	{
		keepGoing = true;
		scanIn = new Scanner(System.in);
		dbc = new DatabaseController("veekun-pokedex.sqlite");
		while(keepGoing)
		{
			userInput = scanIn.nextLine();
			userInputArray = userInput.toLowerCase().split(" ");
			switch(userInputArray[0])
			{
				case "pinfo": try{pokemonInfo(new Pokemon(userInputArray[1]));} catch (eNotAPokemon e){System.out.println("Invalid pokemon name, please try again");}
					break;
				case "ptype": try{pokemonType(new Pokemon(userInputArray[1]));} catch (eNotAPokemon e){System.out.println("Invalid pokemon name, please try again");}
					break;
				case "pweak": try{pokemonWeakness(new Pokemon(userInputArray[1]));} catch (eNotAPokemon e){System.out.println("Invalid pokemon name, please try again");}
					break;
				case "help": System.out.println("The help command hasn't been filled yet, please annoy the developer into making one");
					break;
				case "exit": keepGoing = false;
						System.out.println("Thank you for using Banpower1's Pokedex, have a nice day.");
					break;
				default: System.out.println("Please use a valid command. Type \"Help\" to show all commands");
					break;
			}
		}
		
		scanIn.nextLine();
	}

	/**
	 * prints basic information about the pokemon
	 * @param poke a pokemon
	 */
	public void pokemonInfo(Pokemon poke)
	{
		System.out.print(Utilities.capitalize(poke.getName()) + ": [Dex: #" + poke.getDexNumber() + "] [Type: " + poke.getType().getPrintable() + "] ");
		//System.out.print("Bulbasaur: [Dex: #1] [Type: Grass/Poison] [Abilities: Overgrow/Chlorophyll (DW)] [Evolution: Ivysaur/Level 16]");
	}
	
	/**
	 * prints information about the pokemons type and its weaknesses
	 * @param poke a pokemon
	 */
	public void pokemonType(Pokemon poke)
	{
		String pokemonTypeMessage;
		pokemonTypeMessage = Utilities.capitalize(poke.getName()) + " is a " + poke.getType().getPrintable() + " type";
		System.out.println(pokemonTypeMessage);
		
		pokemonWeakness(poke);
	}	

	/**
	 * prints information about a pokemons weaknesses
	 * @param poke a pokemon
	 */
	public void pokemonWeakness(Pokemon poke)
	{
		System.out.println("Defensive matchups:");
		ArrayList<TypeEfficacy> defenses = poke.getType().getDefences();
		ArrayList<String> defenseResult = new ArrayList<String>();
		for(TypeEfficacy efficacy:defenses)
		{
			defenseResult.add(efficacy.getPrintable());
		}

		int longest = 0;
		for(String i:defenseResult)
		{
			if(i.length() > longest)
			{
				longest = i.length();
			}
		}

		ArrayList<String> defensePrint = new ArrayList<String>(); 
		for(String i:defenseResult)
		{
			int spaceToAdd = longest - i.length();
			String space = "";
			for(int j = 0; j < spaceToAdd; j++)
			{
				space = space + " ";
			}
			defensePrint.add(i.substring(0, i.length()-5) + space + i.substring(i.length()-5, i.length()));
		}

		for(String i:defensePrint)
		{
			System.out.println(i);
		}
	}
}
