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
package org.proteomecommons.jaf.util;

import org.proteomecommons.jaf.*;
import org.proteomecommons.jaf.residues.*;
import javax.swing.*;
import org.proteomecommons.jaf.Atom;
import java.util.*;

/**
 * An program that calculates isotope distributions given a chemical compound.
 * This is helpful for checking if the isotope distribution observed from mass
 * spec matches the expected.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class CalculateIsotopeDistribution {
	double monoisotopic = 0;

	double totalProb = 0;

	double increment = 0.25;

	double[] dist = new double[(int) (20 / increment) + 1];

	// average atoms, used in approximations
	Atom[] averageAtoms = null;

	double[] averageAtomsCounts = null;

	/**
	 * Resets this isotope districution calculator. Use this method if you would
	 * like to calculate multiple isotope distributions using the same instance
	 * of the calculator.
	 *  
	 */
	public void reset() {
		totalProb = 0;
		monoisotopic = 0;
		for (int i = 0; i < dist.length; i++) {
			dist[i] = 0;
		}
	}

	public double[] calculate(Residue[] residues) {
		// hash for the residues
		Hashtable hash = new Hashtable();
		for (int i = 0; i < residues.length; i++) {
			Atom[] as = residues[i].getAtoms();
			for (int j = 0; j < as.length; j++) {
				// check if it has been seen
				Integer in = (Integer) hash.get(as[j]);
				// if not, make a new int
				if (in == null) {
					in = new Integer(1);
				}
				// make a new int with the right value
				else {
					in = new Integer(in.intValue() + 1);
				}
				hash.put(as[j], in);
			}
		}

		// make the values/counts
		Atom[] atoms = new Atom[hash.values().size()];
		int[] counts = new int[hash.values().size()];
		int count = 0;
		for (Enumeration en = hash.keys(); en.hasMoreElements();) {
			Atom key = (Atom) en.nextElement();
			Integer integer = (Integer) hash.get(key);
			atoms[count] = key;
			counts[count] = integer.intValue();
			count++;
		}

		// calculate using the atoms and counts
		return calculate(atoms, counts);
	}

	public double[] approximateIsotopeDistribution(double massInDaltons) {
		// reset
		reset();

		// find the average atoms
		if (averageAtoms == null) {
			synchronized (this) {
				// hash for the residues
				Hashtable hash = new Hashtable();
				for (int i = 0; i < Residue.commonResidues.length; i++) {
					Atom[] as = Residue.commonResidues[i].getAtoms();
					for (int j = 0; j < as.length; j++) {
						// check if it has been seen
						Integer in = (Integer) hash.get(as[j]);
						// if not, make a new int
						if (in == null) {
							in = new Integer(1);
						}
						// make a new int with the right value
						else {
							in = new Integer(in.intValue() + 1);
						}
						hash.put(as[j], in);
					}
				}

				// make the values/counts
				Atom[] atoms = new Atom[hash.values().size()];
				double[] counts = new double[hash.values().size()];
				int count = 0;
				int totalAtoms = 0;
				for (Enumeration en = hash.keys(); en.hasMoreElements();) {
					Atom key = (Atom) en.nextElement();
					Integer integer = (Integer) hash.get(key);
					atoms[count] = key;
					counts[count] = integer.intValue();
					totalAtoms += counts[count];
					count++;
				}

				// set them appropriately
				averageAtoms = atoms;
				averageAtomsCounts = counts;
				for (int i = 0; i < averageAtomsCounts.length; i++) {
					averageAtomsCounts[i] /= totalAtoms;
				}
			}
		}

		// approximate the number of atoms using the averages
		Atom[] atoms = averageAtoms;
		int[] counts = new int[atoms.length];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = (int) Math.round(averageAtomsCounts[i] * massInDaltons);
		}
		// calc the monoisotopic
		double mono = 0;
		for (int i = 0; i < atoms.length; i++) {
			mono += atoms[i].getMassInDaltons() * counts[i];
		}
		// adjust
		double diff = mono / massInDaltons;
		for (int i = 0; i < atoms.length; i++) {
			counts[i] = (int) Math.round(counts[i] / diff);
		}

		return calculate(atoms, counts);
	}

	// calculates the isotope distribution and returns the points
	public double[] calculate(Atom[] atoms, int[] counts) {
		// first calc the monoisotopic
		double monoProb = 1;
		for (int i = 0; i < atoms.length; i++) {
			monoisotopic += atoms[i].getMassInDaltons() * counts[i];
			// update the total probability
			monoProb *= Math.pow(atoms[i].getProbability(), counts[i]);
		}
		// update the mono prob
		updateDist(monoisotopic, monoProb);

		// try all variations
		for (int i = 1; i < 3; i++) {
			calcIsotopes(atoms, counts, i);
		}

		// use doub
		calcDoubIsotopes(atoms, counts);

		return dist;
	}

	// helper to calculate single isotopes
	private void calcIsotopes(Atom[] atoms, int[] counts, int variations) {
		// do all single variations
		for (int i = 0; i < atoms.length; i++) {
			Atom[] isotopes = atoms[i].getIsotopes();
			// do each isotope
			for (int j = 1; j < isotopes.length; j++) {
				double prob = 1;
				double mass = 0;
				// count normal isotopes
				if (counts[i] > variations) {
					prob *= Math.pow(atoms[i].getProbability(),
							(counts[i] - variations));
					mass += atoms[i].getMassInDaltons()
							* (counts[i] - variations);
				}
				// count the changed isotope
				prob *= Math.pow(isotopes[j].getProbability(), variations);
				mass += isotopes[j].getMassInDaltons() * variations;

				// count the normal prob for all the other atoms
				for (int k = 0; k < atoms.length; k++) {
					//skip the changed isotope
					if (k == i) {
						continue;
					}
					// count the normal atomes
					prob *= Math.pow(atoms[k].getProbability(), counts[k]);
					mass += atoms[k].getMassInDaltons() * counts[k];
				}

				// multiply by the number of similar atoms
				for (int k = 0; k < variations; k++) {
					prob *= (counts[i] - variations);
				}

				// add the probability
				updateDist(mass, prob);
			}
		}
	}

	// helper to calculate single isotopes
	private void calcDoubIsotopes(Atom[] atoms, int[] counts) {
		// do first variations
		for (int i = 0; i < atoms.length - 1; i++) {
			Atom[] isotopes = atoms[i].getIsotopes();
			// do the second variations
			for (int second = i + 1; second < atoms.length; second++) {
				Atom[] secIsotopes = atoms[second].getIsotopes();
				// do each isotope
				for (int j = 1; j < isotopes.length; j++) {
					double prob = 1;
					double mass = 0;
					// count normal isotopes
					if (counts[i] > 1) {
						prob *= Math.pow(atoms[i].getProbability(),
								(counts[i] - 1));
						mass += atoms[i].getMassInDaltons() * (counts[i] - 1);
					}
					// count the changed isotope
					prob *= Math.pow(isotopes[j].getProbability(), 1);
					mass += isotopes[j].getMassInDaltons() * 1;

					// do all the second isotopes
					for (int k = 1; k < secIsotopes.length; k++) {
						double secProb = prob;
						double secMass = mass;
						// count normal isotopes
						if (counts[second] > 1) {
							secProb *= Math.pow(atoms[second].getProbability(),
									(counts[second] - 1));
							secMass += atoms[second].getMassInDaltons()
									* (counts[second] - 1);
						}
						// count the changed isotope
						secProb *= secIsotopes[k].getProbability();
						secMass += secIsotopes[k].getMassInDaltons();

						// count the normal prob for all the other atoms
						for (int l = 0; l < atoms.length; l++) {
							//skip the changed isotope
							if (l == i || l == second) {
								continue;
							}
							// count the normal atomes
							secProb *= Math.pow(atoms[l].getProbability(),
									counts[l]);
							secMass += atoms[l].getMassInDaltons() * counts[l];
						}

						// multiply by the number of similar atoms
						secProb *= (counts[second] - 1) * (counts[i] - 1);

						// add the probability
						updateDist(secMass, secProb);
					}
				}
			}
		}
	}

	// helper method to update distributions
	private void updateDist(double mass, double probability) {
		// update the sample point
		for (int i = 0; i < dist.length; i++) {
			double distMass = i * increment + monoisotopic;
			// find where this peak would occur
			if (i * increment + monoisotopic >= mass) {
				dist[i] += probability;
				totalProb += probability;
				break;
			}
		}
	}

	public static void main(String[] args) {
		// alanine
		CalculateIsotopeDistribution cid = new CalculateIsotopeDistribution();

		//		Residue[] residues = new Residue[] { Residue.C };
		//		double[] dist = cid.calculate(residues);

		cid.approximateIsotopeDistribution(2000);

		// display the stats
		System.out.println("Monoisotopic:\t" + cid.monoisotopic);
		System.out.println("Total Probability:\t" + cid.totalProb);
		for (int i = 0; i < cid.dist.length; i++) {
			// skip blanks
			if (cid.dist[i] == 0) {
				continue;
			}
			System.out.println((i * cid.increment) + "\t" + cid.dist[i]);
		}

		// show the pane
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IsotopeDistributionPanel idp = new IsotopeDistributionPanel();
		idp.setIsotopeDistribution(cid);
		f.add(idp);
		f.setSize(400, 400);
		f.setVisible(true);
	}
}

class CalculateIsotopeDistributionTimerTask extends java.util.TimerTask {
	private CalculateIsotopeDistribution cid;

	int count = 0;

	public CalculateIsotopeDistributionTimerTask(
			CalculateIsotopeDistribution cid) {
		this.cid = cid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		count++;
		System.out.print("*");
		if (count % 10 == 0) {
			System.out.println("% prob: " + cid.totalProb);
		}

	}
}

