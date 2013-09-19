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

import java.util.LinkedList;

/**
 * Convenient abstraction for a protein. This class also provides lots of
 * methods for manipulating proteins, such as getting peptides.
 *
 * @author Jayson Falkner - jfalkner@umich.edu
 *
 */
public class Protein {
    private String name;
    
    private Residue[] sequenceResidues = null;
    
    public Protein(String name, String sequence){
        this.name = name;
        try {
            this.sequenceResidues = GenericResidue.getResidues(sequence);
        } catch (Exception e){
            throw new RuntimeException("Invalid protein sequence: "+sequence);
        }
    }
    
    public Protein(String name, Residue[] residues){
        this.name = name;
        this.sequenceResidues = residues;
    }
    
    public Protein(byte[] name, byte[] sequence) {
        this.name = new String(name);
        try {
            this.sequenceResidues = GenericResidue.getResidues(new String(sequence));
        } catch (Exception e){
            throw new RuntimeException("Invalid protein sequence: "+sequence);
        }
    }
    
    public Protein(char[] name, char[] sequence) {
        this.name = new String(name);
        try {
            this.sequenceResidues = GenericResidue.getResidues(new String(sequence));
        } catch (Exception e){
            throw new RuntimeException("Invalid protein sequence: "+sequence);
        }
    }
    
    public String getSequence() {
        return new Peptide(sequenceResidues).toString();
    }
    
    public Residue[] getSequenceAsResidues() {
        return sequenceResidues;
    }
    
    public String getName() {
        return new String(name);
    }
    
    public String toString() {
        return getName()+": "+new Peptide(sequenceResidues).toString();
    }
}