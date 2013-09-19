	package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class P31 extends Atom {
	public P31() {
	  super("P31",   30.97376151, 100);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{P});
		}
		return super.getIsotopes();
	}

}
