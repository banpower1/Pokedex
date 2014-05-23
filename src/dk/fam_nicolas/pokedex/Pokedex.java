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
				case "pinfo": System.out.println("We registered the Pinfo command :D");
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
	 * @return list of all Pokemons
	 */
	public ArrayList<Pokemon> Pokemons()
	{
		ArrayList<ArrayList<Object>> schema = dbc.request("SELECT id, identifier FROM pokemon");
		ArrayList<Pokemon> temp = new ArrayList<Pokemon>();
		
		for(ArrayList<Object> row:schema)
		{
			temp.add(new Pokemon((int)row.get(0), (String)row.get(1)));
		}
		
		return temp;
	}
	
	public Pokemon PokemonInfo(String pokeName)
	{
		return new Pokemon();
	}
}
