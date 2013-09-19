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
package org.proteomecommons.jaf;

import org.proteomecommons.jaf.atoms.*;
import java.util.LinkedList;

/**
 * 
 * The set of naturally occurring atoms including natural abundances of
 * isotopes. You should use this class if you are working with natural
 * abundances of atoms and you don't really care about other situations.
 * 
 * @author Jayson Falkner -jfalkner@umich.edu
 *  
 */
public class DefaultAtomSet implements AtomSet {
	// std atom: http://physics.nist.gov
	public static final double H = Atom.H.getMassInDaltons();

	public static final double H2 = Atom.H2.getMassInDaltons();

	public static final double O = Atom.O.getMassInDaltons();

	public static final double O17 = Atom.O17.getMassInDaltons();

	public static final double O18 = Atom.O18.getMassInDaltons();

	public static final double N = Atom.N.getMassInDaltons();

	public static final double N15 = Atom.N15.getMassInDaltons();

	public static final double C = Atom.C.getMassInDaltons();

	public static final double C13 = Atom.C13.getMassInDaltons();

	public static final double P = Atom.P.getMassInDaltons();

	public static final double S = Atom.S.getMassInDaltons();

	public static final double S33 = Atom.S33.getMassInDaltons();

	public static final double S34 = Atom.S34.getMassInDaltons();

	public static final double S36 = Atom.S36.getMassInDaltons();

	public String getName() {
		return "Naturally Occuring Atoms";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proteomecommons.jaf.AtomSet#getAtom(char)
	 */
	public Atom getAtom(char c) {
		return getAtom(Character.toString(c));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proteomecommons.jaf.AtomSet#getAtom(java.lang.String)
	 */
	public Atom getAtom(String name) {
		// get the atom based on name
		// TODO: all for full names.
		if (name.equals("H")) {
			return Atom.H;
		} else if (name.equals("C")) {
			return Atom.C;
		} else if (name.equals("N")) {
			return Atom.N;
		} else if (name.equals("O")) {
			return Atom.O;
		} else if (name.equals("S")) {
			return Atom.S;
		} else if (name.equals("P")) {
			return Atom.P;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proteomecommons.jaf.AtomSet#getAtoms()
	 */
	public Atom[] getAtoms() {
		return new Atom[] { Atom.H, Atom.H2, Atom.O, Atom.O17, Atom.O18,
				Atom.N, Atom.N15, Atom.C, Atom.C13, Atom.P, Atom.S, Atom.S33,
				Atom.S34, Atom.S36 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proteomecommons.jaf.AtomSet#getMass(char)
	 */
	public double getMass(char c) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proteomecommons.jaf.AtomSet#getMass(java.lang.String)
	 */
	public double getMass(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
}