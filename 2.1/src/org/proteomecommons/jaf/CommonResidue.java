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

import java.util.*;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class CommonResidue extends GenericResidue {

	// residue's single letter abbreviation
	private char oneLetter = '?';

	private String threeLetter = "???";
	
	private static HashMap codonsMappedToResidue = new HashMap();
	private static HashMap residueMappedToCodons = new HashMap();
	//generae both maps
	private static void populateHashMap() {
		//CommonResidue cr = CommonResidue.V;
		codonsMappedToResidue.put("GUU", CommonResidue.V);
		codonsMappedToResidue.put("GUC", CommonResidue.V);
		codonsMappedToResidue.put("GUA", CommonResidue.V);
		codonsMappedToResidue.put("GUG", CommonResidue.V);
		
		codonsMappedToResidue.put("UAU", CommonResidue.Y);
		codonsMappedToResidue.put("UAC", CommonResidue.Y);

		codonsMappedToResidue.put("UGG", CommonResidue.W);

		codonsMappedToResidue.put("CCU", CommonResidue.P);
		codonsMappedToResidue.put("CCC", CommonResidue.P);
		codonsMappedToResidue.put("CCA", CommonResidue.P);
		codonsMappedToResidue.put("CCG", CommonResidue.P);

		codonsMappedToResidue.put("UUU", CommonResidue.F);
		codonsMappedToResidue.put("UUC", CommonResidue.F);

		codonsMappedToResidue.put("AUG", CommonResidue.M);

		codonsMappedToResidue.put("AAA", CommonResidue.K);
		codonsMappedToResidue.put("AAG", CommonResidue.K);

		codonsMappedToResidue.put("UUA", CommonResidue.L);
		codonsMappedToResidue.put("UUG", CommonResidue.L);
		codonsMappedToResidue.put("CUU", CommonResidue.L);
		codonsMappedToResidue.put("CUC", CommonResidue.L);
		codonsMappedToResidue.put("CUA", CommonResidue.L);
		codonsMappedToResidue.put("CUG", CommonResidue.L);

		codonsMappedToResidue.put("AUU", CommonResidue.I);
		codonsMappedToResidue.put("AUC", CommonResidue.I);
		codonsMappedToResidue.put("AUA", CommonResidue.I);

		codonsMappedToResidue.put("CAU", CommonResidue.H);
		codonsMappedToResidue.put("CAC", CommonResidue.H);

		codonsMappedToResidue.put("GGU", CommonResidue.G);
		codonsMappedToResidue.put("GGC", CommonResidue.G);
		codonsMappedToResidue.put("GGA", CommonResidue.G);
		codonsMappedToResidue.put("GGG", CommonResidue.G);

		codonsMappedToResidue.put("CAA", CommonResidue.Q);
		codonsMappedToResidue.put("CAG", CommonResidue.Q);

		codonsMappedToResidue.put("GAA", CommonResidue.E);
		codonsMappedToResidue.put("GAG", CommonResidue.E);

		codonsMappedToResidue.put("UGU", CommonResidue.C);
		codonsMappedToResidue.put("UGC", CommonResidue.C);

		codonsMappedToResidue.put("GAU", CommonResidue.D);
		codonsMappedToResidue.put("GAC", CommonResidue.D);

		codonsMappedToResidue.put("AAU", CommonResidue.N);
		codonsMappedToResidue.put("AAC", CommonResidue.N);

		codonsMappedToResidue.put("CGU", CommonResidue.R);
		codonsMappedToResidue.put("CGC", CommonResidue.R);
		codonsMappedToResidue.put("CGA", CommonResidue.R);
		codonsMappedToResidue.put("CGG", CommonResidue.R);
		codonsMappedToResidue.put("AGA", CommonResidue.R);
		codonsMappedToResidue.put("AGG", CommonResidue.R);

		codonsMappedToResidue.put("GCU", CommonResidue.A);
		codonsMappedToResidue.put("GCC", CommonResidue.A);
		codonsMappedToResidue.put("GCA", CommonResidue.A);
		codonsMappedToResidue.put("GCG", CommonResidue.A);

		codonsMappedToResidue.put("UCU", CommonResidue.S);
		codonsMappedToResidue.put("UCC", CommonResidue.S);
		codonsMappedToResidue.put("UCA", CommonResidue.S);
		codonsMappedToResidue.put("UCG", CommonResidue.S);
		codonsMappedToResidue.put("AGU", CommonResidue.S);
		codonsMappedToResidue.put("AGC", CommonResidue.S);

		codonsMappedToResidue.put("ACU", CommonResidue.T);
		codonsMappedToResidue.put("ACC", CommonResidue.T);
		codonsMappedToResidue.put("ACA", CommonResidue.T);
		codonsMappedToResidue.put("ACG", CommonResidue.T);
		
		//populate another map in reverse
		Iterator it = codonsMappedToResidue.keySet().iterator();
		while(it.hasNext()){
			Object codonSeq = it.next();
			Object commonResidue = codonsMappedToResidue.get(codonSeq);
			if(residueMappedToCodons.get(commonResidue) == null){
				residueMappedToCodons.put(commonResidue, new ArrayList());
			}
			((ArrayList)residueMappedToCodons.get(commonResidue)).add(codonSeq);
		}
	}
	
	
	public char getOneLetter() {
		return oneLetter;
	}

	public void setOneLetter(char c) {
		oneLetter = c;
		// set the FASTA char
		setFASTAChar(c);
	}

	public String getThreeLetter() {
		return threeLetter;
	}

	public void setThreeLetter(String threeLetter) {
		this.threeLetter = threeLetter;
	}
	
	/**
	 * Helper method to get commonly used amino acids using their single letter abbreviations.
	 * @param c The single letter amino acid abbreviation.
	 * @return The residue that corresponds to the given abbreviation.
	 */
	public static CommonResidue getResidue(char c){
		// switch to quickly return the correct residue
		switch (c) {
		  case 'A': {return A;}
		  case 'C': {return C;}
		  case 'D': {return D;}
		  case 'E': {return E;}
		  case 'F': {return F;}
		  case 'G': {return G;}
		  case 'H': {return H;}
		  case 'I': {return I;}
		  case 'K': {return K;}
		  case 'L': {return L;}
		  case 'M': {return M;}
		  case 'P': {return P;}
		  case 'Q': {return Q;}
		  case 'R': {return R;}
		  case 'S': {return S;}
		  case 'T': {return T;}
		  case 'V': {return V;}
		  case 'W': {return W;}
		  case 'Y': {return Y;}
		}
		
		// fall back on null
		return null;
	}
	
	public String getAbbreviation(){
		return String.valueOf(getOneLetter());
	}
	
	/**
	 * Return the CommonResidue created by the specified codon.
	 * @param codon A 3 character string.
	 * @return the appropriate CommonResidue
	 */
	public static CommonResidue residueFromCodon(String codon){
		if(codonsMappedToResidue.size() == 0){
			populateHashMap();
		}
		return (CommonResidue)codonsMappedToResidue.get(codon);
	}
	
	/**
	 * Generate a peptide from the codon sequences in the specified string. 
	 * @param nucleotides
	 * @return the peptide made from the given codon sequence.  Null if the codon sequence is not divisible by 3.
	 */
	public static Peptide peptideFromCodons(String codons){
		if(codons.length()%3 != 0){
			System.out.println("The specified nucleotide string was not a multiple of 3.");
			return null;
		}
		ArrayList residues = new ArrayList();
		for(int subStringIndex = 0; subStringIndex < codons.length(); subStringIndex = subStringIndex +3){
			String thisCodon = codons.substring(subStringIndex, subStringIndex + 3);
			CommonResidue cr = residueFromCodon(thisCodon);
			if(cr == null){
				if(!thisCodon.equals("UAG") && !thisCodon.equals("UAA") && !thisCodon.equals("UGA")){
					System.out.println(thisCodon + " isn't a known codon->peptite relationship.");
				}
			} else {
				residues.add(cr);
			}
		}
		return new Peptide((CommonResidue[])residues.toArray(new CommonResidue[0]));
	}
	
	/**
	 * Generates permutations of the specified string, i.e. UUU -> CUU, AUU, GUU, CCU, etc
	 * @param codon
	 * @return
	 */
	private static String[] tweakCodons(String[] codons){
		Collection possibleCodons = new HashSet();
		for(int i = 0; i < codons.length; i++){
			innerTweak(codons[i], 0, possibleCodons);
		}
		return (String[])possibleCodons.toArray(new String[0]);
	}
	
	private static String[] innerTweak(String codon, int flipIndex, Collection possibleCodons){
		if(flipIndex >= codon.length()){
			return (String[])possibleCodons.toArray(new String[0]);
		} else {
			char[] temp = codon.toCharArray();
			temp[flipIndex] = 'G';
			possibleCodons.add(new String(temp));
			temp[flipIndex] = 'C';
			possibleCodons.add(new String(temp));
			temp[flipIndex] = 'A';
			possibleCodons.add(new String(temp));
			temp[flipIndex] = 'U';
			possibleCodons.add(new String(temp));
			return innerTweak(codon, flipIndex + 1, possibleCodons);
		}
	}
	
	//get possible snips -> array of possible residues if there is a snip
	/**
	 * Generate the possible Single Nucleotide Polymorphisms of the specified CommonResidue.
	 * For example, the codon sequence for Methionine is AUG.  So, this function generates 
	 * the residues made by swapping one character in the AUG sequence, such as GUG, UUG.  
	 * The sequences with one swapped character are then converted back into CommonResidues.
	 * @param findMySNPs the CommonResidue of which to find SNPs
	 */
	public static CommonResidue[] getSNPs(CommonResidue findMySNPs){
		if(codonsMappedToResidue.size() == 0){
			populateHashMap();
		}
		
		String[] codonsToTweak = (String[])((ArrayList)residueMappedToCodons.get(findMySNPs)).toArray(new String[0]);
		String[] tweakedCodons = tweakCodons(codonsToTweak);
		
		Set returnMe = new HashSet();
		for(int i = 0; i < tweakedCodons.length; i++){
			//filter out start/stop codons (they're mapped to null instead of a peptide)
			if(codonsMappedToResidue.get(tweakedCodons[i]) != null){
				returnMe.add(codonsMappedToResidue.get(tweakedCodons[i]));
			}
		}

		return (CommonResidue[])returnMe.toArray(new CommonResidue[0]);
	}
	
	public CommonResidue[] getSNPs(){
		return getSNPs(this);
	}
}
