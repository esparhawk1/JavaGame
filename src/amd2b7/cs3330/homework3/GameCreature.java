
/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

import java.util.ArrayList;

public abstract class GameCreature implements Creature {
	private CommandProcessor commandProcessor;
	private String name;
	protected Health health;
	protected Bag bag;
	
	public GameCreature(String name, int hp, Bag bag){
		this.setName(name);
		this.createHealth(hp);
		this.initBag(bag);
		this.initCommandProcessor();	
	}

	private void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	/*
	 * Gives current human a bag with a crowbar in it, allows for each human to have their own bag, and possibly multiple bags...
	 */
	private void initBag(Bag emptyBag){
		this.bag=emptyBag;
	}
	
	/*
	 * gives the human its own health object.
	 */
	private void createHealth(int hp){
		this.health=new Health(hp);
	}
	
	/*
	 * returns the human's health object.
	 */
	public Health getHealth(){
		return this.health;
	}
	
	public int currentHealthPoints(){
		return health.getHealthPoints();
	}
	
	/*
	 * instantiates a commandprocessor object for this human so that they can process their own commands, allows for multiple humans (homework3?)
	 */
	private void initCommandProcessor(){
		commandProcessor=new CommandProcessor();
	}
	
	/*
	 * Takes in the passed command, the beast and/or item found from main before going to battle or noBattle.
	 * Splits command and begins comparing first word in the command via switch statement.
	 * Runs through a ton of checks depending on which command is chosen and does the necessary changes and calls for the command to occur.
	 * Attack will check for syntax and the weapon player choose, then call to hit the beast.
	 * Pickup will check the weight of the bag and add passed item if the total weight is ok
	 * drop will search player bag for a passed item name and remove the item from the bag if it is found
	 * help will display the list of commands
	 * skip does nothing, it's just there to validate the command as a real command since the skip checks are in battle/nobattle.
	 */
	public CreatureResponse processCommand(String command, Beast beast, Item item){
		String response="";
		boolean validAction=true;
		String[] splitCommands=null;
		splitCommands=command.split(" ");
		validAction=commandProcessor.validateUserCommand(splitCommands[0]);
		/*
		if(!validAction){
			CreatureResponse newHumanResponse=new CreatureResponse("Invalid Game Command",false);
		}
		*/
		switch(splitCommands[0]){
			case ("attack"):{
				//System.out.println("Confusion");
				if(beast==null){
					//System.out.println("Beast=NULL");
					response="There's no beasty to attack";
					validAction=false;
					break;
				}
				System.out.println(beast.getName());
				if(splitCommands.length!=4 || !splitCommands[1].equals("with")){
					response="Invalid attack syntax, use 'attack with <weapon name>'";
					validAction=false;
					break;
				}
				//System.out.println(beastName);
				if(((Human)this).isLiving()){
					Item attackItem=bag.getItem(splitCommands[2]+" "+splitCommands[3]);
					//System.out.println("Attacking creature with "+attackItem.getName());
					if(!((Human)this).attack(beast, attackItem)){
						response="Not a valid weapon";
						validAction=false;
					}
					else{
						System.out.println("Attack successful");
						Bag beastbag=beast.getBag();
						Item beastitem=beastbag.getItem(0);
						//System.out.println(((Weapon)beastitem).getPoints());
						this.injured((Weapon)beastitem);
						System.out.println(beast.getName()+" attacked you with "+((Weapon)beastitem).getName());
					}
				}
				break;
			}
			case("pickup"):{
				if(this.pickup(item)){
					response="Item added successfully";
					validAction=true;
				}
				else{
					response="Item not added";
					validAction=false;
				}
				break;
			}
			case("drop"):{
				if(splitCommands.length>=2){
					Item drop=bag.getItem(splitCommands[1]+" "+splitCommands[2]);
					if(this.drop(drop)){
						response="Item successfully dropped";
						validAction=true;
					}
					else{
						response="Item not found in bag";
						validAction=false;
					}
				}
				else{
					response="Improper drop syntax";
				}
				break;
			}
			case("help"):{
				ArrayList<String> commands=commandProcessor.getCommands();
				for(String c:commands){
					response=response+c+"\n";
				}
				validAction=true;
				break;
			}
			case("skip"):{
				Bag beastbag=beast.getBag();
				Item beastitem=beastbag.getItem(0);
				this.injured((Weapon)beastitem);
				validAction=true;
				break;
			}
			case("runaway"):{
				bag.dropItems();
				response="Ran Away";
				validAction=true;
				break;
			}
			case("heal"):{
				if(((Human)this).isLiving()){
					if(splitCommands.length>=3){
						Item heal=bag.getItem(splitCommands[1]+" "+splitCommands[2]);
						if(heal!=null){
							this.heal(heal);
							bag.dropItem(heal);
							Bag beastbag=beast.getBag();
							Item beastitem=beastbag.getItem(0);
							this.injured((Weapon)beastitem);	
							validAction=true;
							break;
						}
						else{
							validAction=false;
							response="You don't have that item to heal with";
							break;
						}
					}
					else{
						validAction=true;
						response="Improper heal syntax";
						break;
					}
				}
			}
			default:{
				response="Invalid Command";
				validAction=false;
			}
		}
		CreatureResponse newHumanResponse=new CreatureResponse(response, validAction);
		return newHumanResponse;
	}

	public boolean isLiving() {
		if(health.getAlive()){
			return true;
		}
		return false;
	}
	
	public Bag getBag(){
		return this.bag;
	}
}
