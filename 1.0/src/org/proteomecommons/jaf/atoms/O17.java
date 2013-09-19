package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * TODO: add the other oxygen isotopes.
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class O17 extends Atom {
	public O17() {
	  super("O17",  16.99913150, 0.038);
	  // set the isotopes
	  setIsotopes(new Atom[0]);
	}
}
