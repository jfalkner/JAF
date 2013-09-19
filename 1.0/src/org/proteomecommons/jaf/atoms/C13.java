	package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class C13 extends Atom {
	public C13() {
	  super("C13",  13.0033548378, 1.07);
	  // set the isotopes
	  setIsotopes(new Atom[0]);
	}
}
