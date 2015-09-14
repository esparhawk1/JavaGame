/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RoomEscape{
	private static Scanner userInput;
	private static ArrayList<Item> gameItems;
	private static ArrayList<Beast> gameBeasts;
	private static ArrayList<Beast> currentRoomBeasts;
	private static Human currentPlayer;
	private static Random random;
	
	public static void main(String[] args) {
		//Make the game's items/beasts/commands for use later
        initGameData();
        //Pick a name
        System.out.print("Enter Character Name: ");
        String name = userInput.nextLine();
        //Pick how many rooms they wish to play
        System.out.print("Enter number of levels: ");
        //Hope you use a number or it'll blow up...
        int maxRooms = Integer.parseInt(userInput.nextLine());
        //Instantiate the commandprocessor to check validity of commands the user inputs
        CommandProcessor commandProcessor=new CommandProcessor();
        //New thing I added to display commands at beginning of the game because I know text based games that tell you nothing about how to start really suck
        String response="\nCommands:\n";
        ArrayList<String> commands=commandProcessor.getCommands();
		for(String c:commands){
			response=response+c+"\n";
		}
		System.out.println(response);
		//Create new player with the inputted name, 100hp, and a bag with a crowbar
		Bag playerBag=new Bag();
		Weapon crowbar=new Weapon("Standard Crowbar", "", 5, -30);
		playerBag.addItem(crowbar);
        currentPlayer = new Human(name,100, playerBag);
        int roomsBeat = 0;//The one variable to rule them all...or dictate when the game is over.
         
        while (roomsBeat < maxRooms) {
                 /*
                  * Create room bad guys and items
                  */
                System.out.println("Entering Room " + (roomsBeat + 1));
                System.out.println(" ");
                initCurrentRoomBeasts();//Calls method to create a new array list filled with a new set of gamebeasts so each "room" has it's own chunk of memory
                int creaturesDestroyed = 0;//The 2nd variable to rule most of the others...or dictate when a room is over.
                int runCounter=0;
                /*Checks to see if player is alive and said player hasn't "cleared" the room
                * Picks a random number from 0-100 to simulate probability
                * Declares and item and beast to null
                * checks the encounterProb against preset probability chances to other find an item or find a beast
                * If the item choice gets picked it will grab a random item from the gameItems, tell the player what they found, and then run noBattle
                * 		noBattle will take in a command, check to make sure it's valid (return a response if invalid), then run that command based off another method.
                * If the encounterProb rolls high enough, a beast is found. A beast is chosen from the currentRoomBeasts (NOT GAMEBEASTS), tell the player they encountered a thing, then do battle.
                * 		battle will prompt user to pick a weapon from their bag, if they pick something they dont own it will tell them, if the weapon is valid, the beast will get hit and it's health updated based on the value of the weapon
                * 		once beasty is dead, the creaturesDestroyed variable will increment by 1.
                * Once the while loop completes (by killing 2 beasts), roomsBeat will increment by 1.
                * Once the outter while completes by roomsBeat being greater than or equal to the user input number of rooms, the game will end.
               	*/
                while (currentPlayer.isLiving() && creaturesDestroyed < 2) {
                        int encounterProb = random.nextInt(100);
                        Item foundItem = null;
                        Beast foundEnemy = null;
                        /*
                         * 60 percent chance of being a Beast.
                         */
                        if (encounterProb <= 40) {
                                foundItem = findItem();
                                System.out.println("You have discovered the item "+foundItem.getName());
                                while (noBattle(foundItem)); 
                                
                        }
                        else {
                                foundEnemy = findEnemy();
                                System.out.println("You have encountered a(n) " + foundEnemy.getName());
                                if (battle (foundEnemy)) {
                                        creaturesDestroyed++;
                                }
                                else{
                                	System.out.println("You ran away!");
                                	runCounter++;
                                }
                                if(!currentPlayer.isLiving() || runCounter>=5){
                                        System.out.println("You are dead!");
                                        return;
                                }
                         }
                }
                roomsBeat++;
        }
        System.out.println("You have beat RoomEscape!");
	}
	 
	/*
	 * @param: Beast battlingBeast - the beast chosen by "findEnemy()" and the thing the player needs to slay
	 * 
	 * Displays all of the players belongings in his/her bag.
	 * Prompts user with "attack <beastName> with " so they can pick which weapon they wish to use.
	 * Concatonate(still can't spell this right) the prompt with the user input and send it to the commandprocessor for...processing.
	 * If the action is invalid (due to invalid weapon choice due to weapon not being in the players bag) an error response will be returned and displayed.
	 * If the action (aka weapon) is valid, when the command processes, the beast's hp will update within the processing
	 * method will check to see if beasty is alive after hp is update and if the beasty is not alive, it will say that it has died
	 * else it will display beasty name and hp leftover.
	 * 
	 * Returns boolean: True once battle has completed, false in order to restart another battle rotation.
	 */
	private static boolean battle (Beast battlingBeast) {
		while (currentPlayer.isLiving()) {
			System.out.println(battlingBeast.getName() + " HP: " + battlingBeast.currentHealthPoints());
			System.out.println(currentPlayer.getName()+" HP: "+currentPlayer.currentHealthPoints());
			displayBagContents();
			String attackString = userInput.nextLine();
			System.out.println(" ");
			CreatureResponse response = currentPlayer.processCommand(attackString, battlingBeast,null);
			if(response.getResponse().toLowerCase().equals("ran away")){
				return false;
			}
			if (!response.getValidAction()) {
				System.out.println(response.getResponse());
			}
			if (currentPlayer.isLiving() && !battlingBeast.isLiving()) {
				System.out.println(battlingBeast.getName() + " is killed");
				System.out.println(" ");
				return true;
			}
		}
		return false;
	}

	/*
	 * gets the current player's bag and lists the crap it holds...
	 */
	private static void displayBagContents () {
		System.out.println("Contents of Bag:");
		System.out.println("# Type\t\tName\t\t\tPoints\tWeight");
		Bag playerBag = currentPlayer.getBag();
		int x=1;
		for (Item i : playerBag.getItems()) {
			if(i instanceof Weapon){
			System.out.printf("%d WEAPON\t%s\t%d\t%d\n", x,i.getName(),((Weapon)i).getPoints(),i.getWeight());
			++x;
			}
			if(i instanceof Healer){
				System.out.printf("%d HEALER\t\t%s\t\t\t%d\t%d\n", x,i.getName(),((Healer)i).getPoints(),i.getWeight());
			}
		}
		System.out.println(" ");
	}

	/*
	 * @param: Item foundItem - Item chosen by findItem in main.
	 * 
	 * Disaply bag contents of current player.
	 * Prompt for command
	 * If command is empty of skip(I added a skip command that only works in noBattle so they can't avoid beasts) it will return false and end the noBattle phase. 
	 * Otherwise, it will take in whatever command, process it, if invalid it will continue to prompt user til valid command is given.
	 * Again, if it's empty it will end the current nobattle phase.
	 * Personal addition: Return's false on pickup command so it will process the command and then end the phase without needing to enter skip or an empty line.
	 * Returns true if everything is processed and valid.
	 */
	private static boolean noBattle (Item foundItem) {
		displayBagContents();
		System.out.print("Command: ");
		String input = userInput.nextLine();
		if (input.isEmpty() || input.toLowerCase().equals("skip")) {
			return false;
		}
		CreatureResponse response = currentPlayer.processCommand(input, null,foundItem);
		while (!response.getValidAction()) {
			System.out.print(response.getResponse() + ", Try Again: ");
			input = userInput.nextLine();
			response = currentPlayer.processCommand(input, null,foundItem);                
			if (input.isEmpty()) {
				return false;
			}
		}        
		System.out.println(response.getResponse());
		if(input.toLowerCase().equals("pickup")){
			return false;
		}
		return true;
	}

	/*
	 * Same as homework 1...
	 * Picks a number from 1-4, displays a message that many times then picks a random item from the gameItems and returns it...
	 */
	private static Item findItem(){
		int scavenge_attempts=random.nextInt(3)+1;
		int item_id=random.nextInt(gameItems.size());
		for(int i=0;i<=scavenge_attempts;++i){
			System.out.println("Searching area...\n");
		}
		Item foundItem=gameItems.get(item_id);
		return foundItem;
	}

	/*
	 * See above, replace "item" with "beast"
	 */
	private static Beast findEnemy(){
		int scavenge_attempts=random.nextInt(3)+1;
		int beast_id=random.nextInt(gameBeasts.size());
		for(int i=0;i<=scavenge_attempts;++i){
			System.out.println("Searching area...\n");
		}
		Beast foundBeast=currentRoomBeasts.get(beast_id);
		return foundBeast;
	}

	/*
	 * Copies all of gameBeasts into a new arraylist for the currentRoom. 
	 * Virtually creates an individual room with it's own indivudal objects (this is ridiculously cool to me for some reason...probably the idea of how segregated memory is currently creating a virtual map of the game.)
	 */
	private static void initCurrentRoomBeasts(){
		currentRoomBeasts=new ArrayList<Beast>();
		for(Beast obeast:gameBeasts){
			Beast currentBeast=new Beast(obeast.getName(), obeast.currentHealthPoints(), obeast.getBag(), obeast.getType());
			currentRoomBeasts.add(currentBeast);
		}
	}

	/*
	 * Load all data needed to run the game. Items, beasts, commands, the scanner, everything.
	 */
	private static void initGameData(){
		userInput=new Scanner(System.in);
		random=new Random(1337);
		GameDataReader gameDataReader=new GameDataReader();
		gameItems=gameDataReader.getGameItems();
		gameBeasts=gameDataReader.getGameBeasts();
	}
}
