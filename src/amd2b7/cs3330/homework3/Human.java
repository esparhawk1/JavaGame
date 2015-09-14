/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

public class Human extends GameCreature implements Creature{
	/*
	 * constructor
	 */
	public Human(String name, int hp, Bag emptyBag){
		super(name, hp, emptyBag);
	}
	
	@Override
	public boolean pickup(Item item){
		boolean add=bag.addItem(item);
		return add;
	}
	
	@Override
	public boolean drop(Item item){
		boolean drop=bag.dropItem(item);
		return drop;
	}
	
	@Override
	public void dropAll(){
		bag.dropItems();
		}
	
	public boolean attack(Beast beast, Item item){
		boolean hit=beast.injured(item);
		return hit;
	}
	
	@Override
	public boolean heal(Item item){
		int start;
		int end;
		start=health.getHealthPoints();
		health.heal(((Healer)item).getPoints());
		end=health.getHealthPoints();
		if(end>start){
			return true;
		}
		return false;
	}
	
	public boolean isLiving(){
		return this.health.getAlive();
	}
	
	public Bag getBag(){
		return this.bag;
	}
	
	public boolean injured(Item item){
		if(item!=null){
			if(item instanceof Weapon){
				health.hit(((Weapon)item).getPoints());
				return true;
			}
			if(item instanceof Healer){
				health.hit(((Healer)item).getPoints());
				return true;
			}
		}
		return false;
	}

	@Override
	public CreatureResponse processCommand(String command,
			GameCreature creature, Item item) {
		// TODO Auto-generated method stub
		return null;
	}
}
