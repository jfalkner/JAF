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
 * An abstract interface for representing an atom. The read-only approach to
 * this interface forces code that uses this framework to play nicely with the
 * rest of the framework.
 * 
 * @author Jayson Falkner -jfalkner@umich.edu
 *  
 */
public interface Atom {
	// flag for unknown atomic mass.
	public static int UNKNOWN_MASS = -1;

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

	/**
	 * The probability of this atom occuring amongst its other isotopic forms.
	 * 
	 * @return
	 */
	public double getProbability();

	/**
	 * Gets the mass of this atom in daltons.
	 * 
	 * @return
	 */
	public double getMassInDaltons();

	/**
	 * Gets the arbitrary name for this atom.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Gets the other occuring isotopic forms of this atom.
	 * 
	 * @return
	 */
	public Atom[] getIsotopes();
}