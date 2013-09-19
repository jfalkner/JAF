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
import org.proteomecommons.jaf.*;

/**
 * This is a helper class to make adding pICalculators easier.
 *
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class GenericPICalculator implements PICalculator {
    private static HashMap calculators = new HashMap();;
    private static HashMap negativePKa = new HashMap();
    private static HashMap positivePKa = new HashMap();
    private static double nTermPKa = 8.0;
    private static double cTermPKa = 3.1;
    
    public void setNTerminus(double pKa) {
        nTermPKa = pKa;
    }
    public void setCTerminus(double pKa){
        cTermPKa = pKa;
    }
    public void setPositive(String name, double pKa) {
        positivePKa.put(name, new Double(pKa));
    }
    public void setNegative(String name, double pKa) {
        negativePKa.put(name, new Double(pKa));
    }
    
    /**
     * This method calculates a pI value based on a given set of residues.
     */
    public double calculatePI(Residue[] residues) {
        double min = 3;
        double max = 12;
        // binary search over the range of pH values (3-12)
        double index = 0;
        // worst case, limit to 2^32 precision
        for (int limit=0;limit<32;limit++) {
            // try different pH values
            index = (min+max)/2;
            // calc the partial charge at that value
            double charge = calcCharge(index, residues);
//            System.out.println("pH "+index+" charge is: "+charge);
            if (charge > 0) {
                min = index;
//                System.out.println("Uppig min to "+min);
            } else if (charge < 0) {
                max = index;
//                System.out.println("Lowering max to "+max);
            }
            //if we happen upon 0 charge, return the value
            else {
                return index;
            }
        }
        // fall back on a value that is really close
        return index;
    }
    
    /**
     * Estimates a charge based on a give pH and set of residues.
     */
    private double calcCharge(double pH, Residue[] residues){
        double partialCharge = 0;
        for (int i=0;i<residues.length;i++){
            String name = residues[i].getName();
            
            // check for known positive charge groups
            Double pos = (Double)positivePKa.get(name);
            if(pos != null) {
                double chargeRatio = Math.pow(10, pos.doubleValue()-pH);
//                System.out.println("  Adding charge: "+chargeRatio/(chargeRatio+1));
                partialCharge += chargeRatio/(chargeRatio+1);
            }
            
            // check for known negative charge groups
            Double neg = (Double)negativePKa.get(name);
            if(neg != null) {
                double chargeRatio = Math.pow(10, pH-neg.doubleValue());
//                System.out.println("  Subtracting charge: "+chargeRatio/(chargeRatio+1));
                partialCharge -= chargeRatio/(chargeRatio+1);
            }
        }
        
        // adjust for n-term
        double nTermChargeRatio = Math.pow(10, nTermPKa-pH);
        partialCharge += nTermChargeRatio/(nTermChargeRatio+1);
        // adjust for c-term
        double cTermChargeRatio = Math.pow(10, pH-cTermPKa);
        partialCharge -= cTermChargeRatio/(cTermChargeRatio+1);
        
        return partialCharge;
    }
    
    public double calculatePI(Peptide p) {
        return calculatePI(p.getResidues());
    }
    
    public double calculatePI(Residue r) {
        return calculatePI(new Residue[]{r});
    }
    
    public static PICalculator getPICalculator() {
        return getPICalculator("Tabb");
    }
    
    public static PICalculator getPICalculator(String name){
        // check on init
        synchronized (calculators){
            if (calculators.size()==0){
                calculators.put("Tabb", new TabbPICalculator());
                calculators.put("Solomon", new SolomonPICalculator());
            }
        }
        
        // fall back on Tabb's pI caclulator
        if (name == null) {
            name = "Tabb";
        }
        // null if the calculator isn't registered
        return (PICalculator)calculators.get(name);
    }
    
    /**
     * Helper method to register new pI calculators.
     */
    public static void setPICalculator(String name, PICalculator calculator) {
        // init if needed
        getPICalculator(null);
        // register the new calculator
        calculators.put(name, calculator);
    }
}
