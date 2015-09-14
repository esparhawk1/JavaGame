/*
 /*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import amd2b7.cs3330.homework3.Item;

public class GameDataReader {
	
	/*
	 * Reads in file GameItems.csv
	 * instantiates an item arraylist
	 * creates scanner for the file
	 * while loops runs, taking lines as strings from the file
	 * parsing them into strItems string array 
	 * then sends those values to the item constructor
	 * places newly made item object into an item arraylist
	 * close scanner
	 * catch possible stack of errors
	 * returns that array of items
	 */
	public ArrayList<Item> getGameItems(){
		File file=new File("GameData/GameItems.csv");
		ArrayList<Item> items=new ArrayList<Item>();
		Item item=null;
		try{
			Scanner sc=new Scanner(file);
			while(sc.hasNextLine()){
				String s=sc.nextLine();
				String[] strItem=s.split("[, ]");
				String type=strItem[0];
				String level=strItem[1];
				String name=strItem[2];
				int weight=Integer.parseInt(strItem[3]);
				int damage=Integer.parseInt(strItem[4]);
				if(type.equals("Weapon")){
					item=new Weapon(level+" "+name, level, weight, 0-damage);
				}
				else{
					item=new Healer(level+" "+name, level, weight, damage);
				}
				items.add(item);
			}
		sc.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	return items;
	}
	
	
	/*
	 * Reads in file GameBeasts.csv
	 * instantiates an beast arraylist
	 * creates scanner for the file
	 * while loops runs, taking lines as strings from the file
	 * parsing them into strItems string array 
	 * then sends those values to the beast constructor
	 * places newly made beast object into an beast arraylist
	 * close scanner
	 * catch possible stack of errors
	 * returns that array of beasts
	 */
	public ArrayList<Beast> getGameBeasts(){
		File file=new File("GameData/GameCreatures.csv");
		ArrayList<Beast> beasts=new ArrayList<Beast>();
		try{
			Scanner sc=new Scanner(file);
			while(sc.hasNextLine()){
				String s=sc.nextLine();
				String[] strItem=s.split("[, ]");
				String level=strItem[0];
				String type=strItem[1];
				String name=level+" "+type;
				int hp=Integer.parseInt(strItem[2]);
				String ilevel=strItem[3];
				String iname=strItem[4];
				int weight=Integer.parseInt(strItem[5]);
				int damage=0-Integer.parseInt(strItem[6]);
				Weapon weapon=new Weapon(ilevel+iname, ilevel, weight, damage);
				Bag bag=new Bag();
				bag.addItem(weapon);
				Beast beast=new Beast(name, hp, bag, type);
				beasts.add(beast);
			}
		sc.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	return beasts;
	}
	
	
	/*
	 * Reads in file Commands.txt
	 * instantiates a String arraylist
	 * creates scanner for the file
	 * while loops runs, taking lines as strings from the file
	 * parsing them into strItems string array 
	 * places parsed string into the string arraylist
	 * close scanner
	 * catch possible stack of errors
	 * returns that array of items
	 */
	public ArrayList<String> getGameCommands(){
		File file=new File("GameData/Commands.txt");
		ArrayList<String> commands=new ArrayList<String>();
		try{
			Scanner sc=new Scanner(file);
			while(sc.hasNextLine()){
				String s=sc.nextLine();
				commands.add(s);
			}
		sc.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	return commands;
	}
}
