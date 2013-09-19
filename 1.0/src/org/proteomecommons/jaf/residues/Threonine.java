/*
 *    Copyright 2004 Jayson Falkner
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
 * Abstraction for Threonine.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class Threonine extends CommonResidue {
	public Threonine() {
		setName("Threonine");
		setThreeLetter("Thr");
		setOneLetter('T');
		// set the atoms
		this
				.setAtoms(new Atom[] { Atom.H, Atom.H, Atom.H,Atom.H, Atom.H,Atom.H, Atom.H,
						Atom.C, Atom.C,Atom.C,Atom.C, Atom.N, Atom.O, Atom.O});
		// set pKa
		this.setDissociationConstant(new DissociationConstant(0, 6.82, 3.55));
	}

}