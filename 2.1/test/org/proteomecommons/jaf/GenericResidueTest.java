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
import junit.framework.TestCase;

/**
 *
 * @author Jayson
 */
public class GenericResidueTest extends TestCase{
    
    /**
     * Checks that a string can be converted to residues correctly.
     **/
    public void testStringToResidues() throws Exception {
        String seq = "ACDEKR";
        Residue[] residues = GenericResidue.getResidues(seq);
        if (!residues[0].equals(GenericResidue.A) ||
            !residues[1].equals(GenericResidue.C) ||
            !residues[2].equals(GenericResidue.D) ||
            !residues[3].equals(GenericResidue.E) ||
            !residues[4].equals(GenericResidue.K) ||
            !residues[5].equals(GenericResidue.R)) {
            // throw an exception
            throw new Exception("Can't convert FASTA string to residues.");
        }
    }

        /**
     * Checks that a string can be converted to residues correctly, including known modifications.
     **/
    public void testStringToResiduesWithModification() throws Exception {
        String seq = "ACDEM(Oxidation)R";
        Residue[] residues = GenericResidue.getResidues(seq);
        if (!residues[0].equals(GenericResidue.A) ||
            !residues[1].equals(GenericResidue.C) ||
            !residues[2].equals(GenericResidue.D) ||
            !residues[3].equals(GenericResidue.E) ||
            !residues[4].equals(GenericResidue.getResidueByName("M(Oxidation)")) ||
            !residues[5].equals(GenericResidue.R)) {
            // throw an exception
            throw new Exception("Can't convert FASTA string to residues when a modification is present.");
        }
        System.out.println("Peptide: "+new Peptide(residues));
    }

        /**
     * Checks that a string can be converted to residues correctly, including known modifications.
     **/
    public void testStringToResiduesWithAddedModification() throws Exception {
        String seq = "ACDEM(Foo Bar)R";
        // register the mod
        GenericModification mod = new GenericModification("Foo Bar", new Atom[0], new Atom[0]);
        Residue r = new GenericModifiedResidue(Residue.M, mod);
        GenericResidue.addResidue(r);
        // make the residues
        Residue[] residues = GenericResidue.getResidues(seq);
        if (!residues[0].equals(GenericResidue.A) ||
            !residues[1].equals(GenericResidue.C) ||
            !residues[2].equals(GenericResidue.D) ||
            !residues[3].equals(GenericResidue.E) ||
            !residues[4].equals(GenericResidue.getResidueByName("M(Foo Bar)")) ||
            !residues[5].equals(GenericResidue.R)) {
            // throw an exception
            throw new Exception("Can't handle custom modifications.");
        }
    }
}
