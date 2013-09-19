package org.proteomecommons.jaf;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class CommonResidue extends Residue {

	// residue's single letter abbreviation
	private char oneLetter = '?';

	private String threeLetter = "???";
	
	public char getOneLetter() {
		return oneLetter;
	}

	public void setOneLetter(char c) {
		oneLetter = c;
	}

	public String getThreeLetter() {
		return threeLetter;
	}

	public void setThreeLetter(String threeLetter) {
		this.threeLetter = threeLetter;
	}
}
