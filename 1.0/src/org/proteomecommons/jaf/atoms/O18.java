package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * TODO: add the other oxygen isotopes.
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class O18 extends Atom {
	public O18() {
	  super("O18",   17.9991604, 0.205);
	  // set the isotopes
	  setIsotopes(new Atom[0]);
	}
}
