/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

public class Healer extends Item {
	private int healPoints;
	
	public Healer(String name, String level, int weight, int points){
		super(name, level, weight);
		this.setHealPoints(points);
	}
	
	private void setHealPoints(int points){
		this.healPoints=points;
	}
	
	public int getPoints(){
		return this.healPoints;
	}
}
