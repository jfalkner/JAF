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
public class Alanine extends CommonResidue {
	public Alanine() {
		setName("Alanine");
		setThreeLetter("Ala");
		setOneLetter('A');
		// set the atoms
		this.setAtoms(new Atom[] { Atom.H, Atom.H, Atom.H, Atom.H, Atom.H,
				Atom.C, Atom.C, Atom.C, Atom.N, Atom.O });
		// set pKa
		this.setDissociationConstant(new DissociationConstant(0, 7.59, 3.55));
	}

}