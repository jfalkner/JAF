package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * TODO: add the other oxygen isotopes.
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class O16 extends Atom {
	public O16() {
	  super("O", 15.9949146221, 99.757);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{O, O17, O18});
		}
		return super.getIsotopes();
	}
}
