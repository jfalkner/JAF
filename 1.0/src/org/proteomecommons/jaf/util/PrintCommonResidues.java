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
package org.proteomecommons.jaf.util;

import org.proteomecommons.jaf.*;

/**
 * A class that prints out the common residue names, abbreviations, and masses.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class PrintCommonResidues {
	public static void main(String[] args) {
		for (int i = 0; i < Residue.commonResidues.length; i++) {
			System.out.println(Residue.commonResidues[i].getName() + "\t"
					+ Residue.commonResidues[i].getThreeLetter() + "\t"
					+ Residue.commonResidues[i].getOneLetter() + "\t"
					+ Residue.commonResidues[i].getMassInDaltons());
		}
	}
}