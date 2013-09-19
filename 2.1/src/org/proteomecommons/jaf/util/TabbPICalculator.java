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

import java.util.HashMap;
import org.proteomecommons.jaf.GenericResidue;
import org.proteomecommons.jaf.Peptide;
import org.proteomecommons.jaf.Residue;

/**
 * This is a pI calculator based on David Tabb's whitepaper entitled, "An algorithm for isoelectric point estimation."
 *
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class TabbPICalculator extends GenericPICalculator {
    
    public TabbPICalculator() {
        GenericResidue gr = new GenericResidue();
        // basic residues
        setPositive(Residue.H.getName(), 6.5);
        setPositive(Residue.K.getName(), 10);
        setPositive(Residue.R.getName(), 12);
        // acidic residues
        setNegative(Residue.C.getName(), 8.5);
        setNegative(Residue.D.getName(), 4.4);
        setNegative(Residue.E.getName(), 4.4);
        setNegative(Residue.Y.getName(), 10);
        // set the n/c term
        setNTerminus(8.0);
        setCTerminus(3.1);
    }
    
    public static void main(String[] args) throws Exception  {
        TabbPICalculator tpic = new TabbPICalculator();
        Peptide p = new Peptide("ACDK");
        System.out.println(p+" pI is "+tpic.calculatePI(p));
    }
}
