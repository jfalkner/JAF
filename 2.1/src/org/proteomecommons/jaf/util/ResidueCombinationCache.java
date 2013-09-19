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

import java.util.*;

import org.proteomecommons.jaf.*;

public class ResidueCombinationCache {
	ResidueCombinationCacheEntry[] cache;

	// helper method to test the code
	public static void main(String[] args){
		// check args
		if (args.length <2) {
			System.out.println("usage: java ResidueCombinationCache <size> <mass(Da)> <tolerance(ppm)>");
			return;
		}
		// make a cache
		System.out.println("Making cache of size: "+args[0]);
		ResidueCombinationCache cache = new ResidueCombinationCache(Integer.parseInt(args[0]), GenericResidue.getAllResidues());
		System.out.println("Cache Complete, size: "+cache.getSize());
		// query some masses
		System.out.println("Searching for "+args[1]+" +/-"+args[2]+"ppm");
		ResidueCombinationCacheEntry[] hits = cache.getCache(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
		for (int i=0;i<hits.length;i++){
			System.out.println(hits[i]+", mass: "+hits[i].massInDaltons);
		}
	}
	
	/**
	 * The number of unique entries in the cache.
	 * @return The number of unique entries in the cache.
	 */
	public int getSize() {
		return cache.length;
	}
	
	/**
	 * Returns the mass of the largest entry in the residue cache.
	 * @return The mass of the largest entry in the cache.
	 */
	public double getLargestMass() {
		return cache[cache.length-1].massInDaltons;
	}
	/**
	 * Returns all cache entries that are within +/- the tolerance. Tolerance must be in ppm. 
	 */
	public ResidueCombinationCacheEntry[] getCache(double massInDaltons, double ppmTolerance) {
		// convert tolerance to ppm
		double tolerance = massInDaltons*ppmTolerance/1000000;
		ResidueCombinationCacheEntry ce = new ResidueCombinationCacheEntry(massInDaltons);
		ce.massInDaltons -= tolerance;
		// find the key
		int index = Arrays.binarySearch(cache, ce);
		if (index <0){
			index = (index+1)*-1;
		}
		if (index < 0 || index >= cache.length) {
			return null;
		}
		// make an array of cache entries
		LinkedList entries = new LinkedList();
		while (index >= 0 && index < cache.length
				&& cache[index].massInDaltons <= massInDaltons + tolerance) {
			entries.add(cache[index]);
			index++;
		}

		// return the hits
		return (ResidueCombinationCacheEntry[]) entries.toArray(new ResidueCombinationCacheEntry[0]);
	}

	// check if the given mass, with the given tolerance is in the cache
	public boolean hitCache(double massInDaltons, double tolerance) {
		ResidueCombinationCacheEntry ce = new ResidueCombinationCacheEntry(massInDaltons);
		ce.massInDaltons -= tolerance;
		// find the key
		int index = Arrays.binarySearch(cache, ce);
		if (index <0){
			index = (index+1)*-1;
		}
		if (index < 0 || index >= cache.length) {
			return false;
		}
		double b = cache[index].massInDaltons;
		if (index < 0 || index > cache.length || b > massInDaltons + tolerance) {
			return false;
		}
		return true;
	}

	public ResidueCombinationCache(int combinationSize, Residue[] residues) {

		// add entries accordingly
		for (int i=0;i<combinationSize;i++){
			// if the cache is null, add one of each residue
			if (cache==null) {
				// make the cache the given size
				cache = new ResidueCombinationCacheEntry[residues.length];
				for (int j=0;j<residues.length;j++){
					cache[j] = new ResidueCombinationCacheEntry(residues[j]);
				}
			}
			// tack on a residue to everything that exists
			else {
				// hash entries to ensure uniqueness
				HashMap entries = new HashMap();
				// add all that exist
				for (int j=0;j<cache.length;j++){
              	  entries.put(cache[j].toString(), cache[j]);
				}

				// make all the new entries
                for (int j=0;j<cache.length;j++) {
                	// add each residue
                	for (int k=0;k<residues.length;k++){
                		ResidueCombinationCacheEntry c =new ResidueCombinationCacheEntry(cache[j], residues[k]); 
                	  entries.put(c.toString(), c);
                	}
                }
                
                // add all the entries to the cache
                cache = (ResidueCombinationCacheEntry[])entries.values().toArray(new ResidueCombinationCacheEntry[0]);
			}
		}
		
		// sort the cache
		Arrays.sort(cache);
	}
}