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
 * An abstract class for representing an atom.
 * 
 * @author Jayson Falkner -jfalkner@umich.edu
 *  
 */
public class GenericAtom implements Atom{
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
		if (names == null || names.size() == 0) {
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
	public GenericAtom(String name, double massInDaltons, double probability) {
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