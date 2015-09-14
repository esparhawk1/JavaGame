/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

import java.util.ArrayList;

/*
 * Holds items and their total weight...
 */
public class Bag {
	private ArrayList<Item> items;
	private int weight;
	
	/*
	 * constructor
	 */
	public Bag(){
		initBag();
	}
	
	/*
	 * instantiates a bag, not done in constructor to allow for creation of multiple bags, homework3?
	 */
	private void initBag(){
		items=new ArrayList<Item>();
		weight=0;
	}
	
	/*
	 * A getter for the arraylist, returns the whole list object.
	 */
	public ArrayList<Item> getItems(){
		return this.items;
	}
	
	/*
	 * Searches arraylist in current bag for an item that matches the name given.
	 * If it finds it, returns the item, if it doesn't it will return null.
	 */
	public Item getItem(String name){
		for(Item item:items){
			if(((item.getName()).toLowerCase()).equals(name.toLowerCase())){
				return item;
			}
		}
		return null;
	}
	
	/*
	 * returns how many items are currently in players bag.
	 */
	public int getSize(){
		return items.size();
	}
	
	/*
	 * Will check to make sure player can hold additional passed in item by checking the weight. Return error response if it's too heavy.
	 * If player doth hoist the item will be added to the arraylist of items in the bag and the weight updated.
	 */
	public boolean addItem(Item item){
		if(weight+item.getWeight()>50){
			System.out.println("Item to heavy, dost thou even hoist?");
			return false;
		}
		else{
			items.add(item);
			this.weight+=item.getWeight();
			return true;
		}
	}
	
	/*
	 * Checks to see if passed item is null (needed since we made getItem return null...)
	 * Sets passed item's name to lowercase (not actually needed, just leftover from what I thought was a fix)
	 * Takes every item from bag's arraylist, gets the name, sets it to lowercase, compares it to the name of the passed item.
	 * If the names equal, the item in the bag is removed and the weight updated, if they aren't equal then it will simply return false (and a message appears from the commandprocessor)
	 */
	public boolean dropItem(Item item){
		if(item==null){
			return false;
		}
		String b=(item.getName()).toLowerCase();
		for(Item bagItem:items){
			String a=(bagItem.getName()).toLowerCase();
			if(a.equals(b)){
				items.remove(item);
				this.weight-=item.getWeight();
				return true;
			}
		}
		return false;
	}
	
	public void dropItems(){
		items.clear();
		this.weight=0;
	}
	
	public Item getItem(int index){
		return items.get(index);
	}
}
