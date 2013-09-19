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
 * An abstraction representing a set of atoms in a controlled environment. This
 * allows for code to distinguish between situations where different abundances
 * of atomic isotopes are present, e.g. heavy labeled mass spec reagents.
 * 
 * @author Jayson Falkner -jfalkner@umich.edu
 *  
 */
public interface AtomSet {

	/**
	 * Returns an arbitrary name for this set of atoms, e.g. "Natural Abundance"
	 * or "99% C13".
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns the atom that matches the given name.
	 * 
	 * @param name
	 * @return
	 */
	Atom getAtom(String name);

	/**
	 * Returns the Atom instance that matches the given character.
	 * 
	 * @param c
	 * @return The atom or null if no atom matches.
	 */
	Atom getAtom(char c);

	/**
	 * Returns the mass of the atom that matches the given name.
	 * 
	 * @param name
	 * @return The mass or Atom.UNKOWN_MASS if no atom matches.
	 */
	double getMass(String name);

	/**
	 * Returns the mass of the atom that matches the given character.
	 * 
	 * @param c
	 * @return The mass or Atom.UNKOWN_MASS if no atom matches.
	 */
	double getMass(char c);

	/**
	 * Returns all the atoms in this set.
	 * 
	 * @return An array of atoms representing the atoms in this set.
	 */
	Atom[] getAtoms();
}