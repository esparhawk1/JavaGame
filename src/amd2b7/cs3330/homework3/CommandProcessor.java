/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */
package amd2b7.cs3330.homework3;

import java.util.ArrayList;

public class CommandProcessor {
	private ArrayList<String> commands;
	
	/*
	 * constructor
	 */
	public CommandProcessor(){
		this.setGameCommands();
	}
	
	/*
	 * Creates the list of commands from the gamedatareader
	 */
	private void setGameCommands(){
		GameDataReader gameDataReader=new GameDataReader();
		this.commands=gameDataReader.getGameCommands();
	}
	
	/*
	 * returns the list of commands
	 */
	public ArrayList<String> getCommands(){
		return this.commands;
	}
	
	/*
	 * Checks passed command against the commands in the list.
	 */
	public boolean validateUserCommand(String command){
		for(String check:commands){
			if(check.equals(command)){
				return true;
			}
		}
		return false;
	}
}
