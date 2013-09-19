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
 * Generates HTML tables of atoms.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 *  
 */
public class HTMLAtomReference {
	public static void main(String[] args) {

		System.out.println("<html>");
		System.out.println("<head><title>Atom Reference</title>");
		System.out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../styles.css\"></head>");
		System.out.println("<body>");

		System.out.println("<h1>Atom Reference</h1>");
		System.out.println("<p>This table provides a reference of all the atoms contained in the JAF. These values are taken from the NIST's list, http://physics.nist.gov.</p>");
		System.out.println("<table cellspacing=\"0\" cellpadding=\"0\">");
		System.out.println("<tr><th>Name</th><th>Mass</th><th>Natural Abundance</th></tr>");

		// display all combos
		boolean zebraStripe = false;
		// get a set of default atoms
		DefaultAtomSet das = new DefaultAtomSet();
		Atom[] atoms = das.getAtoms();
		for (int i = 0; i < atoms.length; i++) {
			Atom a = atoms[i];
			String start = "<td>";
			if (zebraStripe) {
				start = "<td class=\"zebra_stripe\">";
			}
			zebraStripe = !zebraStripe;
			// start a row
			System.out.print("<tr>");
			// print the name
			System.out.print(start+a.getName()+"</td>");
			// print the mass
			System.out.print(start+a.getMassInDaltons()+"</td>");
			// print the natural abundance
			System.out.print(start+a.getProbability()+"</td>");
			// end a row
			System.out.print("</tr>");
		}

		System.out.println("</table>");

		System.out.println("</body>");
		System.out.println("</html>");

	}
}