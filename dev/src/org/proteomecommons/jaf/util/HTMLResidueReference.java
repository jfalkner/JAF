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
public class HTMLResidueReference {
	public static void main(String[] args) {

		System.out.println("<html>");
		System.out.println("<head><title>Residue Reference</title>");
		System.out
				.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../styles.css\"></head>");
		System.out.println("<body>");

		System.out.println("<h1>Residue Reference</h1>");
		System.out
				.println("<p>This table provides a reference of the 20 common amino acid residues.</p>");
		System.out.println("<table>");
		System.out
				.println("<tr><th>Name</th><th>Three Letter Abbr.</th><th>FASTA Abbr.</th><th>Mass</th></tr>");

		// display all combos
		boolean zebraStripe = false;
		CommonResidue[] commonResidues = GenericResidue.getCommonResidues();
		for (int i = 0; i < commonResidues.length; i++) {
			CommonResidue r = commonResidues[i];
			String start = "<td>";
			if (zebraStripe) {
				start = "<td class=\"zebra_stripe\">";
			}
			zebraStripe = !zebraStripe;
			// start a row
			System.out.print("<tr>");
			// print the name
			System.out.print(start+r.getName()+"</td>");
			// print the three letter
			System.out.print(start+r.getThreeLetter()+"</td>");
			// print the FASTA abbr.
			System.out.print(start+r.getFASTAChar()+"</td>");
			// print the mass
			System.out.print(start+r.getMassInDaltons()+"</td>");
			// end a row
			System.out.print("</tr>");
		}

		System.out.println("</table>");

		System.out.println("</body>");
		System.out.println("</html>");

	}
}