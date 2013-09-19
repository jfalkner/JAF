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

import org.proteomecommons.jaf.residues.*;

import java.util.*;


public class GenericResidue implements Residue {
    private double massInDaltons = 0;
    // flag for n-term specificity
    private boolean nTerminusOnly = false;
    
    // flag for c-term specificity
    private boolean cTerminusOnly = false;
    
    // std residues, provide both full name and abbrev
    public static final CommonResidue Alanine = new Alanine();
    
    public static final CommonResidue A = Alanine;
    
    public static final CommonResidue Arginine = new Arginine();
    
    public static final CommonResidue R = Arginine;
    
    public static final CommonResidue AsparticAcid = new AsparticAcid();
    
    public static final CommonResidue D = AsparticAcid;
    
    public static final CommonResidue Cysteine = new Cysteine();
    
    public static final CommonResidue C = Cysteine;
    
    public static final CommonResidue GlutamicAcid = new GlutamicAcid();
    
    public static final CommonResidue E = GlutamicAcid;
    
    public static final CommonResidue Glutamine = new Glutamine();
    
    public static final CommonResidue Q = Glutamine;
    
    public static final CommonResidue Glycine = new Glycine();
    
    public static final CommonResidue G = Glycine;
    
    public static final CommonResidue Histidine = new Histidine();
    
    public static final CommonResidue H = Histidine;
    
    public static final CommonResidue Isoleucine = new Isoleucine();
    
    public static final CommonResidue I = Isoleucine;
    
    public static final CommonResidue Leucine = new Leucine();
    
    public static final CommonResidue L = Leucine;
    
    public static final CommonResidue Lysine = new Lysine();
    
    public static final CommonResidue K = Lysine;
    
    public static final CommonResidue Methionine = new Methionine();
    
    public static final CommonResidue M = Methionine;
    public static final CommonResidue Asparagine = new Asparagine();
    
    public static final CommonResidue N = Asparagine;
    
    public static final CommonResidue Phenylalanine = new Phenylalanine();
    
    public static final CommonResidue F = Phenylalanine;
    
    public static final CommonResidue Proline = new Proline();
    
    public static final CommonResidue P = Proline;
    
    public static final CommonResidue Serine = new Serine();
    
    public static final CommonResidue S = Serine;
    
    public static final CommonResidue Threonine = new Threonine();
    
    public static final CommonResidue T = Threonine;
    
    public static final CommonResidue Tryptophan = new Tryptophan();
    
    public static final CommonResidue W = Tryptophan;
    
    public static final CommonResidue Tyrosine = new Tyrosine();
    
    public static final CommonResidue Y = Tyrosine;
    
    public static final CommonResidue Valine = new Valine();
    
    public static final CommonResidue V = Valine;
    
    // array for easy use
    public static CommonResidue[] getCommonResidues() {
        return new CommonResidue[] {
            Alanine, Arginine, Asparagine, AsparticAcid, Cysteine, GlutamicAcid, Glutamine,
                    Glycine, Histidine, Isoleucine, Leucine, Lysine, Methionine,
                    Phenylalanine, Proline, Serine, Threonine, Tryptophan, Tyrosine,
                    Valine };
    }
    
    public static Residue[] getAllResidues(){
        return (Residue[])allResidues.toArray(new Residue[0]);
    }
    
    public static void addResidue(Residue r){
        if(!allResidues.contains(r)){
            allResidues.add(r);
        }
    }
    
    public static boolean removeResidue(Residue r){
        return allResidues.remove(r);
    }
    // linked List of all residues
    private static LinkedList allResidues = new LinkedList();
    static {
        Residue[] commonResidues = getCommonResidues();
        // add all the common residues
        for (int i=0;i<commonResidues.length;i++){
            allResidues.add(commonResidues[i]);
        }
        // add misc other residues (i.e. modifications)
        // the ModifiedResidue constructor takes the following args
        // name of mod, residue, atoms added, atoms lost
        // oxidized methionine
        allResidues.add(new GenericModifiedResidue("Oxidation", Residue.M, new Atom[]{Atom.O}, new Atom[0]));
        // pyro-glu on n-term Q, you can't stop this mod
        allResidues.add(new GenericModifiedResidue(Residue.Q, new GenericModification("Pyro-glu N-term Q", new Atom[0], new Atom[]{Atom.H, Atom.H, Atom.H, Atom.N}, true, false )));
        // S-carbamoylmethlcysteine cyclization (n-term) aka pyro-cmC
        allResidues.add(new GenericModifiedResidue(Residue.C, new GenericModification("Pyro-glu N-term Q", new Atom[0], new Atom[]{Atom.H, Atom.H, Atom.H, Atom.N}, true, false )));
        // pyro-glu from n-term E, you can't stop this mod
        allResidues.add(new GenericModifiedResidue(Residue.E, new GenericModification("Pyro-glu N-term E", new Atom[0], new Atom[]{Atom.H, Atom.H, Atom.O}, true, false )));
        // lysine acetylation
        allResidues.add(new GenericModifiedResidue(Residue.K, new GenericModification("Acetylation", new Atom[]{Atom.H, Atom.H, Atom.C, Atom.C, Atom.O}, new Atom[0], true, false )));
        // n-term acetylation (common mod)
        allResidues.add(new GenericModifiedResidue(Residue.A, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.C, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.D, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.E, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.F, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.G, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.H, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.I, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.K, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.L, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.M, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.N, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.P, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.Q, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.R, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.S, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.T, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.V, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.W, GenericModification.NTermAcetylation));
        allResidues.add(new GenericModifiedResidue(Residue.Y, GenericModification.NTermAcetylation));
        // allow for methylation
        allResidues.add(new GenericModifiedResidue(Residue.A, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.C, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.D, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.E, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.F, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.G, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.H, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.I, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.K, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.L, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.M, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.N, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.P, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.Q, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.R, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.S, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.T, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.V, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.W, GenericModification.Methylation));
        allResidues.add(new GenericModifiedResidue(Residue.Y, GenericModification.Methylation));
        // di-methylation
        allResidues.add(new GenericModifiedResidue(Residue.K, GenericModification.diMethylation));
        allResidues.add(new GenericModifiedResidue(Residue.R, GenericModification.diMethylation));
        // tri-methylation
        allResidues.add(new GenericModifiedResidue(Residue.K, GenericModification.triMethylation));
        allResidues.add(new GenericModifiedResidue(Residue.R, GenericModification.triMethylation));
        
        // c-term amidation
//        allResidues.add(new GenericModifiedResidue(Residue.A, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));
//        allResidues.add(new GenericModifiedResidue(Residue.C, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.D, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.E, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.F, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.G, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.H, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.I, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.K, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.L, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.M, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.N, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.P, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.Q, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.R, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.S, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.T, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.V, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.W, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
//        allResidues.add(new GenericModifiedResidue(Residue.Y, new GenericModification("C-term Amidation", new Atom[]{Atom.H, Atom.N}, new Atom[]{Atom.O}, false, true)));;
        // asparagine deamidation
        allResidues.add(new GenericModifiedResidue("Deamidation", Residue.N, new Atom[]{Atom.O}, new Atom[]{Atom.H, Atom.N}));
        // glutamine deamidation
        allResidues.add(new GenericModifiedResidue("Deamidation", Residue.Q, new Atom[]{Atom.O}, new Atom[]{Atom.H, Atom.N}));
        // double oxidation of tryptophan
        allResidues.add(new GenericModifiedResidue("Formylkynurenin", Residue.W, new Atom[]{Atom.O, Atom.O}, new Atom[0]));
        // oxidation of tryptophan
        allResidues.add(new GenericModifiedResidue("Kynurenin", Residue.W, new Atom[]{Atom.O}, new Atom[]{Atom.C}));
        // oxidation of tryptophan
        allResidues.add(new GenericModifiedResidue("Oxolactone", Residue.W, new Atom[]{Atom.O}, new Atom[]{Atom.H, Atom.H}));
        // oxidation of histone
        allResidues.add(new GenericModifiedResidue("Oxidation", Residue.H, new Atom[]{Atom.O}, new Atom[0]));
        // acrylamide adducts
        allResidues.add(new GenericModifiedResidue("Acrylamide", Residue.C, new Atom[]{Atom.H, Atom.H, Atom.H, Atom.H, Atom.H, Atom.C, Atom.C, Atom.C, Atom.N, Atom.O}, new Atom[0]));
        // cysteine's blocked with iodoacetamide derivative
        allResidues.add(new GenericModifiedResidue("Iodoacetamide derivative", Residue.C, new Atom[]{Atom.H, Atom.H, Atom.H, Atom.C, Atom.C, Atom.N, Atom.O}, new Atom[0]));
        // cysteine's blocked with iodoacetic acid
        allResidues.add(new GenericModifiedResidue("Iodoacetic acid derivative", Residue.C, new Atom[]{Atom.H, Atom.H, Atom.C, Atom.C, Atom.O, Atom.O}, new Atom[0]));
        // Methyl ester
        allResidues.add(new GenericModifiedResidue("Methyl ester", Residue.E, new Atom[]{Atom.H, Atom.H, Atom.C}, new Atom[0]));
        // Sulphone 
        allResidues.add(new GenericModifiedResidue("Sulphone", Residue.M, new Atom[]{Atom.O, Atom.O}, new Atom[0]));
    }
    
    // redisue's common name
    private String name;
    
    // residue's common abbrevation
    private char fastaChar = '?';
    
    // atoms in this residue
    private Atom[] atoms = null;
    
    private DissociationConstant pKa = null;
    
    public DissociationConstant getDissociationConstant() {
        return pKa;
    }
    
    public void setDissociationConstant(DissociationConstant pKa) {
        this.pKa = pKa;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public char getFASTAChar() {
        return fastaChar;
    }
    public void setFASTAChar(char c){
        this.fastaChar = c;
    }
    
    public double getMassInDaltons() {
        // check cache
        if (massInDaltons != 0) {
            return massInDaltons;
        }
        // make a new mass
        massInDaltons = 0.0;
        // add up atoms
        for (int i = 0; i < atoms.length; i++) {
            massInDaltons += atoms[i].getMassInDaltons();
        }
        return massInDaltons;
    }
    
    public String toString() {
        return name;
    }
    
    public Atom[] getAtoms() {
        return atoms;
    }
    
    public void setAtoms(Atom[] atoms) {
        this.atoms = atoms;
    }
    
    /**
     * Helper method to get a residue by its name or FASTA character. Residues that are modified are generally specified using the FASTA char and the modification's name in braces, e.g. "M(Oxidation)" is an oxidized methionine.
     *
     * @param name
     * @return
     */
    public static Residue getResidueByName(String name) {
        // TODO: cache the array for speed
        Residue[] allResidues = getAllResidues();
        for (int i = 0; i < allResidues.length; i++) {
            // check if the name equals the raw name or if it equals the FASTA char
            if (allResidues[i].getName().equals(name) || name.equals(Character.toString(allResidues[i].getFASTAChar()))) {
                return allResidues[i];
            }
        }
        return null;
    }
    
    /**
     * Returns a residues based on its FASTA character. This method only considers the known residues.
     * @param c
     * @return
     */
    public static Residue getResidueByFASTAChar(char c) {
        Residue[] commonResidues = getCommonResidues();
        for (int i=0;i<commonResidues.length;i++){
            if (commonResidues[i].getFASTAChar()==c){
                return commonResidues[i];
            }
        }
        return null;
    }
    
    /**
     * Make an array of residues that is the size of the string.
     */
    public static Residue[] getResidues(String sequence) throws Exception {
        // ll for residues
        LinkedList residues = new LinkedList();
        // parse out each residue
        for (int i=0;i<sequence.length();i++){
            // assume the name is one letter
            String name = ""+sequence.charAt(i);
            // if it isn't the last, check for a modification
            if (i<sequence.length()-1){
                // check for '('
                if (sequence.charAt(i+1)=='('){
                    name += "(";
                    // parse the rest
                    for (i=i+2;i<sequence.length()&&sequence.charAt(i)!=')';i++){
                        name+=sequence.charAt(i);
                    }
                    name += ")";
                }
            }
            // get the residue
            Residue res = GenericResidue.getResidueByName(name);
            // null check
            if (res == null){
                throw new Exception("Can't find residue named "+name);
            }
            residues.addLast(res);
        }
        
        // return the buffer
        return (Residue[])residues.toArray(new Residue[residues.size()]);
    }
}