package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class C12 extends Atom {
	public C12() {
	  super("C12",  12.0000000, 98.93);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{C, C13});
		}
		return super.getIsotopes();
	}
}
