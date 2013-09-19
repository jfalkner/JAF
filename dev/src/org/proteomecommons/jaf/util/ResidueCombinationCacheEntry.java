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
package org.proteomecommons.jaf.util;

import org.proteomecommons.jaf.*;
import java.util.*;

public class ResidueCombinationCacheEntry implements Comparable {
	// keep track of residues/mods
	public Residue[] residues;

	// cache a residue name comparator
	ResidueNameComparator nameSort = new ResidueNameComparator();

	double massInDaltons;

	// getter for the mass
	public double getMassInDaltons() {
		return massInDaltons;
	}

	// helper for searching
	public ResidueCombinationCacheEntry(double mass) {
		massInDaltons = mass;
	}

	// constructor for making a blank cache entry
	public ResidueCombinationCacheEntry(Residue residue) {
		// set for each of the residues
		residues = new Residue[1];
		residues[0] = residue;
		this.massInDaltons = residue.getMassInDaltons();
	}

	// constructor for duping a cache entry
	public ResidueCombinationCacheEntry(ResidueCombinationCacheEntry ce,
			Residue r) {
		// replicate the array
		residues = new Residue[ce.residues.length + 1];
		// copy the entries
		System.arraycopy(ce.residues, 0, residues, 0, ce.residues.length);
		// add the new residue to the end
		residues[residues.length - 1] = r;

		// sort for later use
		Arrays.sort(residues, nameSort);

		// update weight
		this.massInDaltons = ce.massInDaltons + r.getMassInDaltons();
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < residues.length; i++) {
			string += residues[i].getName();
		}
		return string;
	}

	public boolean equals(ResidueCombinationCacheEntry e) {
		// check length
		if (e.residues.length != residues.length) {
			return false;
		}
		// match residues
		for (int i = 0; i < residues.length; i++) {
			if (!residues[i].equals(e.residues[i])) {
				return false;
			}
		}
		return true;
	}

	// so these can be sorted/searched fast
	public int compareTo(Object a) {
		ResidueCombinationCacheEntry pa = (ResidueCombinationCacheEntry) a;
		double compare = massInDaltons - pa.massInDaltons;
		if (compare == 0) {
			return 0;
		}
		if (compare < 0) {
			return -1;
		}
		return 1;
	}

}