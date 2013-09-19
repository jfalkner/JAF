/*
 * Copyright 2005 The Regents of the University of Michigan
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

public interface Residue {
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

	/**
	 * Returns the arbitrary name of this residue.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Returns the FASTA single character representation of this residue.
	 * 
	 * @return
	 */
	public char getFASTAChar();

	/**
	 * Returns this residue's mass in daltons.
	 * 
	 * @return
	 */
	public double getMassInDaltons();

	/**
	 * Returns the array of atoms that comprise this residue.
	 * 
	 * @return
	 */
	public Atom[] getAtoms();
}