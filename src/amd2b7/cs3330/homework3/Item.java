/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

public abstract class Item {
	private String name;
	private String level;
	private int weight;
	
	/*
	 * String name: Name of an item
	 * int value: value of the item
	 * int weight: weight of the item
	 * 
	 * Item constructor
	 */
	public Item(String name, String level, int weight){
		this.setName(name);
		this.setLevel(level);
		this.setWeight(weight);
	}
	
	/*
	 * String name: string that will become the items name
	 * sets name of items
	 */
	private void setName(String name){
		this.name=name;
	}
	
	/*
	 * int value: number that will become the items value
	 * sets value of the item
	 */
	private void setLevel(String level){
		this.level=level;
	}
	
	/*
	 * int weight: the number that will become the weight of the item
	 * sets weight of the item
	 */
	private void setWeight(int weight){
		this.weight=weight;
	}
	
	/*
	 * gets the name of an item
	 */
	public String getName(){
		return this.name;
	}
	
	/*
	 * gets the value of an item
	 */
	public String getLevel(){
		return this.level;
	}
	
	/*
	 * gets the weight of an item
	 */
	public int getWeight(){
		return this.weight;
	}
}

