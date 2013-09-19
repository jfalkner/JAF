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
package org.proteomecommons.jaf.pepcalc;

import java.util.ArrayList;
import java.util.Iterator;

import org.proteomecommons.io.NonspecificPeptideReader;
import org.proteomecommons.io.PeptideAsProteinReader;
import org.proteomecommons.io.PeptideReader;
import org.proteomecommons.io.SingleNucleotidePolymorphismPeptideReader;
import org.proteomecommons.io.SinglePeptideReader;
import org.proteomecommons.io.TrypticPeptideReader;
import org.proteomecommons.jaf.Peptide;
import org.proteomecommons.jaf.util.PICalculator;
import org.proteomecommons.jaf.util.TabbPICalculator;

/**
 * A model of a peptide calculator.
 * @author Jarret Falkner
 * @author Jayson Falkner - jfalkner@umich.edu
 *
 */
public class PeptideCalcModel {
    private Peptide pep = null;
    private ArrayList viewers = new ArrayList();
    private String digest = "Tryptic";
    private int pepCharge = 0;
    // default pI calculator is Tabb's
    private PICalculator pICalculator= new TabbPICalculator();
    
    /**
     * Specifies the peptide in the model.
     */
    public void setPeptide(Peptide newPep){
        this.pep = newPep;
    }
    
    /**
     * Specifies the peptide in the model from a well-formatted string.
     * @param pepSeq a string recognized by the JAF framework as a peptide.
     * @throws Exception on poorly formatted strings.
     */
    public void setPeptide(String pepSeq) throws Exception{
        this.pep = new Peptide(pepSeq);
        notifyViewers();
    }
    /**
     *
     * @return this model's current peptide
     */
    public Peptide getPeptide(){
        return pep;
    }
    
    /**
     * Viewer registration for the model.
     * @param viewer a component that would like to be notified after changes within this model
     */
    public void addViewer(PeptideCalcModelViewer viewer){
        viewers.add(viewer);
    }
    
    private void notifyViewers(){
        Iterator vit = viewers.iterator();
        while(vit.hasNext()){
            PeptideCalcModelViewer current = (PeptideCalcModelViewer)vit.next();
            current.notifyViewer();
        }
    }
    
    /**
     * Check if the model has a valid peptide
     */
    public boolean hasPeptide() {
        return pep != null;
    }
    
    /**
     * Allows the model to perform different digests.
     * @param digest Tryptic, SNP, or Non-specific are valid digests
     */
    public void setDigestMode(String digest){
        this.digest  = digest;
    }
    
    /**
     *
     * @return a PeptideReader of the appropriate digest from setDigestMode().
     */
    public PeptideReader getDigest(){
        if(digest.equals("Tryptic")){
            return new TrypticPeptideReader(new PeptideAsProteinReader("User specified peptide", pep));
        } else if(digest.equals("SNP")){
            return new SingleNucleotidePolymorphismPeptideReader(new SinglePeptideReader(pep));
        } else if (digest.equals("Non-specific")){
            return new NonspecificPeptideReader(new PeptideAsProteinReader("",pep));
        }
        return null;
    }
    
    /**
     *
     * @param charge specify the charge of the peptide within the model
     * @throws NumberFormatException on poorly formatted strings
     */
    public void setPeptideCharge(String charge) throws NumberFormatException{
        pepCharge = Integer.parseInt(charge);
        notifyViewers();
    }
    
    /**
     *
     * @return the mass of the model's current peptide with the currently set charge.
     */
    public double getPeptideMassWithCharge(){
        if(hasPeptide()){
            if(pepCharge == 0){
                return pep.getMass();
            }
            return pep.getMassWithCharge(pepCharge);
        } else {
            return 0.0;
        }
    }
    
    /**
     *
     * @return the value of the charge on the modeled peptide.
     */
    public int getCurrentCharge() {
        return pepCharge;
    }
    
    public PICalculator getPICalculator() {
        return pICalculator;
    }
    
    public void setPICalculator(PICalculator pICalculator) {
        this.pICalculator = pICalculator;
        notifyViewers();
    }
}
