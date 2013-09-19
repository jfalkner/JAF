	package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class S32 extends Atom {
	public S32() {
	  super("S32",  31.97207069, 94.93);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{S, S33, S34, S36});
		}
		return super.getIsotopes();
	}

}
