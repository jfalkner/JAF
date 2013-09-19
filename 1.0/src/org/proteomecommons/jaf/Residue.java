/*
 * Copyright 2004 Jayson Falkner
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.proteomecommons.jaf;
import org.proteomecommons.jaf.residues.*;

import java.util.*;
import java.io.*;

public class Residue {
	
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
	public static CommonResidue[] commonResidues = new CommonResidue[]{
			Alanine, Arginine, AsparticAcid, Cysteine, GlutamicAcid, Glutamine, Glycine, Histidine, Isoleucine, Leucine, Lysine, Methionine, Phenylalanine, Proline, Serine, Threonine, Tryptophan, Tyrosine, Valine
	};
	// redisue's common name
	private String name;

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
	

	public double getMassInDaltons() {
		double mass = 0.0;
		// add up atoms
		for (int i = 0; i < atoms.length; i++) {
			mass += atoms[i].getMassInDaltons();
		}
		return mass;
	}

	public String toString() {
		return name;
	}
	
	public Atom[] getAtoms() {
		return atoms;
	}
	public void setAtoms(Atom[] atoms){
		this.atoms = atoms;
	}
	
	// debug the std residues
	public static void main (String[] args){
		System.out.println("Common Residues");
		for (int i=0;i<commonResidues.length;i++){
			System.out.println(commonResidues[i].getName()+"\t"+commonResidues[i].getMassInDaltons());
		}
	}
}