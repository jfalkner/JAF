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

import java.util.Comparator;

import org.proteomecommons.jaf.Residue;

/**
 * Helper Comparator object to sort residues by alphabetical order.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 *  
 */
public class ResidueNameComparator implements Comparator {

	// comparison method
	public int compare(Object a, Object b) {
		Residue aa = (Residue) a;
		Residue bb = (Residue) b;
		
		if (aa.getFASTAChar() > bb.getFASTAChar()) {
			return 1;
		}
		if (aa.getFASTAChar() < bb.getFASTAChar()) {
			return -1;
		}
		

		return 0;
	}
}