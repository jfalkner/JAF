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
import java.io.*;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 * @author Pete Ulintz - pulintz@umich.edu
 *
 * Abstraction of a sequence of amino acids. Any sequence is valid, but commonly
 * this is thought of as a chunk of a protein, usually a tryptic fragment.
 */
public class Peptide {
    // the atoms modifying the n-term
    private Atom[] nTermModification = new Atom[] { Atom.H };
    
    // nominal mass, in case atoms aren't known
    private double nTermMass = 0;
    
    // the atoms modifying the c-term
    private Atom[] cTermModification = new Atom[] { Atom.H, Atom.O };
    
    // nominal mass in case atoms aren't known
    private double cTermMass = 0;
    
    // residues that make up the peptide
    private Residue[] residues;
    
    /**
     * Returns the residue of this peptide, in order, as an array of Residue
     * objects.
     *
     * @return
     */
    public Residue[] getResidues() {
        return residues;
    }
    
    /**
     * Sets the residues that make this peptide.
     *
     * @param residues
     */
    public void setResidues(Residue[] residues) {
        this.residues = residues;
        // check for nulls
        nullCheck();
    }
    
    /**
     * Returns the residues of this peptide, in order, as a sequence of
     * characters.
     *
     * @return
     */
    public char[] getResiduesAsChars() {
        char[] chars = new char[residues.length];
        for (int i = 0; i < residues.length; i++) {
            chars[i] = residues[i].getFASTAChar();
        }
        return chars;
    }
    
    /**
     * A setter that allows one to specify the n-term mass in terms of daltons.
     * This isn't recommended since it may result in inaccurate masses. You
     * should always try to specify the n-term modification in terms of atoms.
     * But if you don't know the atomic make-up, this method suffices.
     *
     * @param mass
     *            The nominal n-term mass.
     */
    public void setNTerminusMass(double mass) {
        // null the atoms
        nTermModification = null;
        // set the mass
        nTermMass = mass;
    }
    
    /**
     * A setter that allows one to specify the c-term mass in terms of daltons.
     * This isn't recommended since it may result in inaccurate masses. You
     * should always try to specify the c-term modification in terms of atoms.
     * But if you don't know the atomic make-up, this method suffices.
     *
     * @param mass
     *            The nominal c-term mass.
     */
    public void setCTerminusMass(double mass) {
        // null the atoms
        cTermModification = null;
        // set the mass
        cTermMass = mass;
    }
    
    /**
     * The preferred method of specifying what atoms are on the c-terminus.
     *
     * @param atoms
     */
    public void setCTerminus(Atom[] atoms) {
        this.cTermModification = atoms;
    }
    
    /**
     * The preferred method of specifying what atoms are on the n-terminus.
     *
     * @param atoms
     */
    public void setNTerminus(Atom[] atoms) {
        this.nTermModification = atoms;
    }
    
    /**
     * Helper method to get the mass of the current n-terminus mass.
     *
     * @return
     */
    public double getNTerminusMass() {
        // if the atoms are null, fall back on the nominal value
        if (nTermModification == null) {
            return nTermMass;
        }
        // calc the mass given the atoms
        double mass = 0;
        for (int i = 0; i < nTermModification.length; i++) {
            mass += nTermModification[i].getMassInDaltons();
        }
        return mass;
    }
    
    /**
     * Helper method to get the mass of the current c-terminus mass.
     *
     * @return
     */
    public double getCTerminusMass() {
        // if the atoms are null, fall back on the nominal value
        if (cTermModification == null) {
            return cTermMass;
        }
        // calc the mass given the atoms
        double mass = 0;
        for (int i = 0; i < cTermModification.length; i++) {
            mass += cTermModification[i].getMassInDaltons();
        }
        return mass;
    }
    
    /**
     * Get the peptide's mass.
     *
     * @return
     */
    public double getMass() {
        // start the mass with n-term and c-term
        double mass = Atom.H.getMassInDaltons() * 2 + Atom.O.getMassInDaltons();
        
        // add up all the residues
        for (int i = 0; i < residues.length; i++) {
            mass += residues[i].getMassInDaltons();
        }
        return mass;
    }
    
    /**
     * A helper method of getting the charged mass of the peptide. Something
     * most mass spec code needs to do.
     *
     * @param charge
     * @return
     */
    public double getMassWithCharge(int charge) {
        return (getMass() + charge * Atom.H.getMassInDaltons()) / charge;
    }
    
    /**
     * Helper method of getting the mass of the peptide assuming that the n-term
     * and c-term consist of the given nominal masses. This method is intended
     * to give flexibility to code that is modifying the n-term or c-term.
     * Ideally you should use the setter method for the n-term and c-term.
     *
     * @param nTerm
     * @param cTerm
     * @return
     */
    public double getMass(double nTerm, double cTerm) {
        // start the mass with n-term and c-term
        double mass = nTerm + cTerm;
        
        // add up all the residues
        for (int i = 0; i < residues.length; i++) {
            mass += residues[i].getMassInDaltons();
        }
        return mass;
    }
    
    /**
     * Customized toString() method that generates a string representation of
     * this peptide as described in the documentation.
     */
    public String toString() {
        StringWriter sw = new StringWriter();
        for (int i = 0; i < residues.length; i++) {
            // check for null
            if (residues[i] == null){
                sw.write("?");
                continue;
            }
            // if not null
            if (residues[i] instanceof ModifiedResidue){
                sw.write(residues[i].getName());
            }
            else {
              sw.write(residues[i].getFASTAChar());
            }
        }
        return sw.toString();
    }
    
    /**
     * Returns a count of how many residues are in this peptide.
     *
     * @return the number of residues in the peptide.
     */
    public int getResidueCount() {
        return residues.length;
    }
    
    /**
     * Public constructor.
     *
     * @param residues
     *            The array of residue objects that make up this peptide.
     * @param mods
     *            The array of modification indexes that match the given
     *            residues.
     * @param config
     *            A reference to the configuration file used for the
     *            residues/mods.
     */
    public Peptide(Residue[] residues) {
        setResidues(residues);
    }
    
    /**
     * Public constructor that builds peptides from given sequences. The
     * sequences must be in the peptide format described by this projects
     * documentation.
     *
     * @param sequence
     */
    public Peptide(String sequence) throws Exception {
        // null check 
        if (sequence == null || sequence.length() == 0){
            throw new Exception("Can't make a peptide from "+null);
        }
        
        // loads the residues and parses out modifications
        residues = org.proteomecommons.jaf.GenericResidue.getResidues(sequence);
//        residues = new Residue[0];
    }
    
    /**
     * Helper method to generate new peptides by replacing a known residues with
     * a another. This class is helpful when considering residue modifications.
     *
     * @param residue
     * @param replaceWith
     * @return
     */
    public Peptide replaceAll(Residue residue, Residue replaceWith) {
        Residue[] res = new Residue[residues.length];
        for (int i = 0; i < res.length; i++) {
            if (residues[i].equals(residue)) {
                res[i] = replaceWith;
            } else {
                res[i] = residues[i];
            }
        }
        return new Peptide(res);
    }
    
    /**
     * Helper method to generate new peptides by replacing a known residues with
     * a another. This class is helpful when considering residue modifications.
     *
     * @param residue
     * @param replaceWith
     * @return
     */
    public Peptide replaceAll(char fastaChar, Residue replaceWith) {
        Residue[] res = new Residue[residues.length];
        for (int i = 0; i < res.length; i++) {
            if (residues[i].getFASTAChar() == fastaChar) {
                res[i] = replaceWith;
            } else {
                res[i] = residues[i];
            }
        }
        return new Peptide(res);
    }
    
    private void nullCheck() {
        for (int i=0;i<residues.length;i++){
            if (residues[i] == null){
                throw new RuntimeException("The residues sequence can't have a null Residue in it.");
            }
        }
    }
    
    public boolean equals(Object o){
    	return o.toString().equals(toString());
    }
    
    public int getNumberOfModifiedResidues(){
    	int numModifiedResidues = 0;
    	Residue[] residues = getResidues();
    	for(int residuesIndex = 0; residuesIndex < residues.length; residuesIndex++){
    		if ((residues[residuesIndex] instanceof ModifiedResidue)){
    			numModifiedResidues++;
    		}
    	}
    	return numModifiedResidues;
    }
    
    /**
     * Calculates pI of peptide using a theoretical 'titration-like' calculation
     *
     * @return pI value
     * @throws Exception
     */
    //	public double getPI() throws Exception {
    //		int iterations = 100;
    //		double pHmin = 0.00;
    //		double pHmax = 14.00;
    //		double tolerance = 0.005; // precision of the calculation
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
    //			double negCharge = ( - aaCount[(int)'D']*(1/(1 + Math.pow(10,(-pH +
    // standardModel.getResidue('D').pKa.internal))))
    //				     - aaCount[(int)'E']*(1/(1 + Math.pow(10, (-pH +
    // standardModel.getResidue('E').pKa.internal))))
    //				     - aaCount[(int)'C']*(1/(1 + Math.pow(10, (-pH +
    // standardModel.getResidue('C').pKa.internal))))
    //				     - aaCount[(int)'Y']*(1/(1 + Math.pow(10, (-pH +
    // standardModel.getResidue('Y').pKa.internal)))) );
    //			negCharge = negCharge - (1/(1 + Math.pow(10, (-pH +
    // standardModel.getResidue(cTerm).pKa.cTerminus))));
    //			// calc positive charge
    //			double posCharge = ( aaCount[(int)'H']*(1/(1 + Math.pow(10,(pH -
    // standardModel.getResidue('H').pKa.internal))))
    //				     + aaCount[(int)'K']*(1/(1 + Math.pow(10, (pH -
    // standardModel.getResidue('K').pKa.internal))))
    //				     + aaCount[(int)'R']*(1/(1 + Math.pow(10, (pH -
    // standardModel.getResidue('R').pKa.internal)))) );
    //			posCharge = posCharge + (1/(1 + Math.pow(10, (pH -
    // standardModel.getResidue(cTerm).pKa.nTerminus))));
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
    //		return -1.0;
    //	}
}
