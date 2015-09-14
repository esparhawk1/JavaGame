/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

/*
 * The health object constructor...
 */
public class Health {
	private int healthPoints;
	private boolean alive;
	
	public Health(int hp){
		this.setHealth(hp);
		this.setAlive(true);
	}
	
	private void setHealth(int hp){
		this.healthPoints=hp;
	}
	
	private void setAlive(boolean boop){
		this.alive=boop;
	}
	
	public int getHealthPoints(){
		return this.healthPoints;
	}
	
	public boolean getAlive(){
		return this.alive;
	}
	
	/*
	 * int hitpoints: value of the passed item that the player wishes to use on beasty.
	 * Sets the current health object of the current beast or player (which is called for) and sets current health to be the current health minus the passed parameter
	 * checks to see if new value is less than or equal to zero and sets Alive to false if it passed.
	 */
	public void hit(int hitPoints){
		this.setHealth(this.getHealthPoints()+hitPoints);
		if(this.healthPoints<=0){
			this.setAlive(false);
		}
	}
	
	public void heal(int healPoints){
		this.setHealth(this.getHealthPoints()+healPoints);
	}
}
