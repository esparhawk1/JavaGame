/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

public class Weapon extends Item {
	private int damagePoints;
	
	public Weapon(String name, String level, int weight, int damagePoints){
		super(name, level, weight);
		this.setDamage(damagePoints);
	}
	
	private void setDamage(int damage){
		this.damagePoints=damage;
	}
	
	public int getPoints(){
		return this.damagePoints;
	}
}
