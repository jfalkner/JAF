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
 * Generates HTML tables of residues.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 *  
 */
public class HTMLResidueCombinationTable {
	public static void main(String[] args) {
		int residues = 4;
		if (args.length > 0) {
			residues = Integer.parseInt(args[0]);
		}

		// make the cache
		ResidueCombinationCache cache = new ResidueCombinationCache(residues,
				GenericResidue.getCommonResidues());

		System.out.println("<html>");
		System.out
				.println("<head><title>Residue Combinations Sorted by Mass</title>");
		System.out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../styles.css\"></head>");
		System.out.println("<body>");

		System.out.println("<h1>Residue Combinations Sorted by Mass</h1>");
		System.out
				.println("<p>This table provides all unique residue combinations up to "
						+ residues
						+ " residues long. The combinations are sorted by mass, and the order of residues in a combination is sorted alphabetically. The table is generated using the ProteomeCommons.org JAF project, and it includes all of the standard amino acids and many of the commonly observed residues in mass spectrometry, including several side-chain modifications to the standard amino acids.</p>");
		System.out.println("<table cellpadding=\"0\" cellspacing=\"0\">");
		System.out
				.println("<tr><td><b>Residues</b></td><td><b>Mass</b></td><td><b>Plus n-term (H)</b></td><td><b>Plus c-term (OH)</b></td></tr>");

		// display all combos
		boolean zebraStripe = false;
		for (int i = 0; i < cache.cache.length; i++) {
			String c = "";
			if (zebraStripe) {
				c = " class=\"zebra_stripe\"";
			}
			zebraStripe = !zebraStripe;
			System.out.println("<tr><td"
					+ c
					+ ">"
					+ toString(cache.cache[i])
					+ "</td><td"
					+ c
					+ ">"
					+ cache.cache[i].getMassInDaltons()
					+ "</td><td"
					+ c
					+ ">"
					+ (cache.cache[i].getMassInDaltons() + Atom.H
							.getMassInDaltons())
					+ "</td><td"
					+ c
					+ ">"
					+ (cache.cache[i].getMassInDaltons()
							+ Atom.H.getMassInDaltons() + Atom.O
							.getMassInDaltons()) + "</td></tr>");
		}

		System.out.println("</table>");

		System.out.println("</body>");
		System.out.println("</html>");

	}

	private static String toString(ResidueCombinationCacheEntry entry) {
		String string = "";
		for (int i = 0; i < entry.residues.length; i++) {
			if (entry.residues[i] instanceof CommonResidue) {
				string += entry.residues[i].getFASTAChar();
			} else {
//				string += entry.residues[i].getName();
				string += entry.residues[i].getFASTAChar()+"<sup>*</sup>";
			}
		}

		return string;
	}
}