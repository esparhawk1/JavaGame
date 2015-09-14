/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

public class Beast extends GameCreature{
	private String type;
	
	/*
	 * constructor
	 */
	public Beast(String name, int hp, Bag bag, String type){
		super(name, hp, bag);
		this.setType(type);
	}
	
	private void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
	}
	/*
	 * Hits current beast with passed in item, returns true if hit occurs, false otherwise.
	 */
	public boolean injured(Item item){
		if(item!=null){
			health.hit(((Weapon)item).getPoints()*2);
			return true;
		}
		return false;
	}

	@Override
	public boolean pickup(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drop(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dropAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CreatureResponse processCommand(String command,
			GameCreature creature, Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean heal(Item item) {
		// TODO Auto-generated method stub
		return false;
	}
}
