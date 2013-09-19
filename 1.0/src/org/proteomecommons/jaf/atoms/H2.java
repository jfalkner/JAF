package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class H2 extends Atom {
	public H2() {
	  super("H2", 2.0141017780, 0.0115);
	  // set the isotopes
	  setIsotopes(new Atom[0]);
	}
}
