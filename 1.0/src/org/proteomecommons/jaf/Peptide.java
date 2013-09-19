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
package org.proteomecommons.jaf;

import java.text.*;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 * @author Pete Ulintz - pulintz@umich.edu
 * 
 * Abstraction of a sequence of amino  acids. Any sequence is valid, but commonly this is thought of as a chunk of a protein, usually a tryptic fragment.
 */
public class Peptide {
	public Residue[] residues;
	
	/**
	 * Public constructor.
	 * @param residues The array of residue objects that make up this peptide.
	 * @param mods The array of modification indexes that match the given residues.
	 * @param config A reference to the configuration file used for the residues/mods.
	 */
	public Peptide(Residue[] residues) {
		this.residues = residues;
	}
	
	/** 
	 * Calculates pI of peptide using a theoretical 'titration-like' calculation
	 * @return pI value
	 * @throws Exception
	 */
	public double getPI() throws Exception {
//		int iterations = 100;
//		double pHmin = 0.00;
//		double pHmax = 14.00;
//		double tolerance = 0.005;   // precision of the calculation
//		
//		// sequence must be longer than 2 amino acids
//		if (residues.length <= 2) {
//			return 0.00;
//		}
//		
//		// count amino acid occurrences
//		// ?? possible residues should come from model ??
//		int[] aaCount = new int[127];
//		for (int i=0;i<this.residues.length;i++) {
//	  		// get amino acid
//	  		char aa = this.residues[i].oneLetter;
//	  		// increment the count for this aa
//	  		aaCount[(int)aa]++;
//	  	}
//		
//		// load a config file
//		ModelConfiguration standardModel = config;
//		char nTerm = this.residues[0].oneLetter;
//		char cTerm = this.residues[this.residues.length-1].oneLetter;
//
//		// calculate charge at converging pH values
//		// pI is the pH at which the net charge on the peptide is zero
//		for (int i=0;i<iterations;i++) {
//			// current pH
//			double pH = pHmin + (pHmax - pHmin)/2.0;
//			// calc negative charge
//			double negCharge = ( - aaCount[(int)'D']*(1/(1 + Math.pow(10,(-pH + standardModel.getResidue('D').pKa.internal))))
//				     - aaCount[(int)'E']*(1/(1 + Math.pow(10, (-pH + standardModel.getResidue('E').pKa.internal))))
//				     - aaCount[(int)'C']*(1/(1 + Math.pow(10, (-pH + standardModel.getResidue('C').pKa.internal))))
//				     - aaCount[(int)'Y']*(1/(1 + Math.pow(10, (-pH + standardModel.getResidue('Y').pKa.internal)))) ); 
//			negCharge = negCharge - (1/(1 + Math.pow(10, (-pH + standardModel.getResidue(cTerm).pKa.cTerminus))));
//			// calc positive charge
//			double posCharge = ( aaCount[(int)'H']*(1/(1 + Math.pow(10,(pH - standardModel.getResidue('H').pKa.internal))))
//				     + aaCount[(int)'K']*(1/(1 + Math.pow(10, (pH - standardModel.getResidue('K').pKa.internal))))
//				     + aaCount[(int)'R']*(1/(1 + Math.pow(10, (pH - standardModel.getResidue('R').pKa.internal)))) ); 
//			posCharge = posCharge + (1/(1 + Math.pow(10, (pH - standardModel.getResidue(cTerm).pKa.nTerminus))));
//			double totalCharge = negCharge + posCharge;
//			// return pH if no net charge within precision tolerance
//			if (Math.abs(totalCharge) < tolerance) {
//				return pH;
//			}
//			// adjust pH, bound based on sign of charge
//			if (totalCharge > 0.00) {
//				pHmin = pH;
//			}
//			else {
//				pHmax = pH;
//			}	
//		}
//		
//		// calculation should only get here if it can't converge
//		// To do: throw exception	
		return -1.0;
	}
	
	
}
