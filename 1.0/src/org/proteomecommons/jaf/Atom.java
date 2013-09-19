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
package org.proteomecommons.jaf;

import org.proteomecommons.jaf.atoms.*;
import java.util.LinkedList;

/**
 * 
 * An abstract class for representing an atom.
 * 
 * @author Jayson Falkner -jfalkner@umich.edu
 *  
 */
public class Atom {
	// std atom: http://physics.nist.gov
	public static final Atom H = new H1();

	public static final Atom H2 = new H2();

	public static final Atom O = new O16();

	public static final Atom O17 = new O17();

	public static final Atom O18 = new O18();

	public static final Atom N = new N14();

	public static final Atom N15 = new N15();

	public static final Atom C = new C12();

	public static final Atom C13 = new C13();

	public static final Atom P = new P31();

	public static final Atom S = new S32();

	public static final Atom S33 = new S33();

	public static final Atom S34 = new S34();

	public static final Atom S36 = new S36();

	// reference to all known atoms
	public static final Atom[] atoms = new Atom[]{
		H, H2, O, O17, O18, N, N15, C, C13, P, S, S33, S34, S36
	};

	private double massInDaltons;

	private LinkedList names = new LinkedList();

	private double probability;

	private Atom[] isotopes;

	public double getProbability() {
		return probability/100;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public void setMassInDaltons(double massInDaltons) {
		this.massInDaltons = massInDaltons;
	}

	public double getMassInDaltons() {
		return massInDaltons;
	}

	public String getName() {
		if (names == null || names.size() != 0) {
			return null;
		}
		return (String) names.getFirst();
	}

	public void setName(String name) {
		this.names.addFirst(name);
	}

	// gets the isotopes
	public Atom[] getIsotopes() {
		return isotopes;
	}

	public void setIsotopes(Atom[] isotopes) {
		this.isotopes = isotopes;
	}

	/**
	 * Public constructor that loads all the values required for an atom.
	 * 
	 * @param name
	 * @param massInDaltons
	 * @param probability
	 */
	public Atom(String name, double massInDaltons, double probability) {
		this.names.add(name);
		this.massInDaltons = massInDaltons;
		this.probability = probability;
	}

	/**
	 * A customized toString method. Helpful for debugging.
	 */
	public String toString() {
		return "(" + (String) names.getFirst() + ", " + massInDaltons + ")";
	}
}