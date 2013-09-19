/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.proteomecommons.jaf.residues;

import org.proteomecommons.jaf.*;

/**
 * @author root
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Arginine extends CommonResidue {
	public Arginine() {
		setName("Arginine");
		setThreeLetter("Arg");
		setOneLetter('R');
		// set the atoms
		this.setAtoms(new Atom[] { Atom.H, Atom.H, Atom.H, Atom.H, Atom.H,Atom.H,Atom.H, Atom.H, Atom.H, Atom.H, Atom.H,Atom.H,
				Atom.C, Atom.C, Atom.C, Atom.C, Atom.C, Atom.C,Atom.N,Atom.N,Atom.N,Atom.N, Atom.O });
		// set pKa
		this.setDissociationConstant(new DissociationConstant(12, 7.5, 3.55));
	}

}