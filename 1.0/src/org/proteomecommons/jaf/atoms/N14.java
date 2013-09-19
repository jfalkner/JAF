package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * TODO: add the other oxygen isotopes.
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class N14 extends Atom {
	public N14() {
	  super("N14",  14.0030740052, 99.632);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{N, N15});
		}
		return super.getIsotopes();
	}
}
