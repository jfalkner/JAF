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

/**
 * A class that prints out the common residue names, abbreviations, and masses.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class PrintCommonResidues {
	public static void main(String[] args) {
		CommonResidue[] commonResidues = GenericResidue.getCommonResidues();
		for (int i = 0; i < commonResidues.length; i++) {
			System.out.println(commonResidues[i].getName() + "\t"
					+ commonResidues[i].getThreeLetter() + "\t"
					+ commonResidues[i].getOneLetter() + "\t"
					+ commonResidues[i].getMassInDaltons());
		}
	}
}