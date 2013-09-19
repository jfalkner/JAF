package org.proteomecommons.jaf.atoms;

import org.proteomecommons.jaf.Atom;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class H1 extends Atom {
	public H1() {
	  super("H", 1.0078250321, 99.9885);
	}
	
	public Atom[] getIsotopes() {
		if (super.getIsotopes()==null){
			setIsotopes(new Atom[]{H, H2});
		}
		return super.getIsotopes();
	}
}
