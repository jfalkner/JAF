/*
 *    Copyright 2005 The Regents of the University of Michigan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.proteomecommons.jaf.residues;

import org.proteomecommons.jaf.*;

/**
 * Abstraction for Histidine.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class Histidine extends CommonResidue {
	public Histidine() {
		setName("Histidine");
		setThreeLetter("His");
		setOneLetter('H');
		// set the atoms
		this
				.setAtoms(new Atom[] { Atom.H, Atom.H, Atom.H,Atom.H, Atom.H, Atom.H, Atom.H,
						Atom.C, Atom.C,Atom.C, Atom.C,Atom.C, Atom.C, Atom.N,Atom.N,Atom.N, Atom.O});
		// set pKa
		this.setDissociationConstant(new DissociationConstant(5.98, 7.5, 3.55));
	}

}