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
public class HTMLAllResidueReference {
    public static void main(String[] args) {
        
        System.out.println("<html>");
        System.out.println("<head><title>Residue Reference</title>");
        System.out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../styles.css\"></head>");
        System.out.println("<body>");
        
        System.out.println("<h1>Residue Reference</h1>");
        System.out.println("<p>This page provides a list of all the common residues and many of the well known residues observed in the JAF (i.e. potential modifications of known residues).</p>");
        System.out.println("<ul>");
        System.out.println("  <li><a href=\"#common\">Common Residues</a></li>");
        System.out.println("  <li><a href=\"#all\">All Residues (alphabetical order)</a></li>");
        System.out.println("</ul>");
        
        {
            System.out.println("<h2><a name=\"common\"></a>Common Residues</h2>");
            System.out.println("<table cellpadding=\"0\" cellspacing=\"0\">");
            System.out.println("<tr><th>Name</th><th>FASTA Abbr.</th><th>Mass</th></tr>");
            
            // display all combos
            boolean zebraStripe = false;
            Residue[] allResidues = GenericResidue.getCommonResidues();
            for (int i = 0; i < allResidues.length; i++) {
                Residue r = allResidues[i];
                String start = "<td>";
                if (zebraStripe) {
                    start = "<td class=\"zebra_stripe\">";
                }
                zebraStripe = !zebraStripe;
                // start a row
                System.out.print("<tr>");
                // print the name
                System.out.print(start+r.getName()+"</td>");
                // print the FASTA abbr.
                System.out.print(start+r.getFASTAChar()+"</td>");
                // print the mass
                System.out.print(start+r.getMassInDaltons()+"</td>");
                // end a row
                System.out.print("</tr>");
            }
            System.out.println("</table>");
        }
        
        {
            System.out.println("<h2><a name=\"all\"></a>All Residues</h2>");
            System.out.println("<table cellpadding=\"0\" cellspacing=\"0\">");
            System.out.println("<tr><th>Name</th><th>FASTA Abbr.</th><th>Mass</th></tr>");
            // display all combos
            boolean zebraStripe = false;
            Residue[] allResidues = GenericResidue.getAllResidues();
            for (int i = 0; i < allResidues.length; i++) {
                Residue r = allResidues[i];
                String start = "<td>";
                if (zebraStripe) {
                    start = "<td class=\"zebra_stripe\">";
                }
                zebraStripe = !zebraStripe;
                // start a row
                System.out.print("<tr>");
                // print the name
                System.out.print(start+r.getName()+"</td>");
                // print the FASTA abbr.
                System.out.print(start+r.getFASTAChar()+"</td>");
                // print the mass
                System.out.print(start+r.getMassInDaltons()+"</td>");
                // end a row
                System.out.print("</tr>");
            }
            System.out.println("</table>");
        }
        
        System.out.println("</body>");
        System.out.println("</html>");
    }
}