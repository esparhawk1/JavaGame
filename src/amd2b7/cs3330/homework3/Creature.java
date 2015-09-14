/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

public interface Creature {
	public boolean pickup(Item item);
	public boolean drop(Item item);
	public void dropAll();
	public CreatureResponse processCommand(String command, GameCreature creature, Item item);
	public boolean injured(Item item);
	public boolean heal(Item item);
}
