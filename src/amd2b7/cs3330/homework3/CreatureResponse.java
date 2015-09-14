/*
 * Alexander Drake
 * 14148312
 * Homework 3
 * CS3330 LAB D
 * 9 November 2014
 */

package amd2b7.cs3330.homework3;

public class CreatureResponse {
	private String response;
	private boolean validAction;
	
	/*
	 * constructor
	 * setters...getters...thats it
	 */
	public CreatureResponse(String response, boolean validAction){
		this.setResponse(response);
		this.setValidAction(validAction);
	}
	
	private void setResponse(String response){
		this.response=response;
	}
	
	private void setValidAction(boolean validAction){
		this.validAction=validAction;
	}
	
	public boolean getValidAction(){
		return this.validAction;
	}
	
	public String getResponse(){
		return this.response;
	}
}
