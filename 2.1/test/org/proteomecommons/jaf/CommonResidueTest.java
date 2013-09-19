package org.proteomecommons.jaf;

import java.util.*;

import junit.framework.TestCase;

public class CommonResidueTest extends TestCase {

	public void testresidueFromCodon(){
		assertEquals(CommonResidue.residueFromCodon("AUU"), CommonResidue.I);
		assertEquals(CommonResidue.residueFromCodon("UGA"), null);
	}
	/*
	 * Test method for 'org.proteomecommons.jaf.CommonResidue.getSnips()'
	 */
	public void testGetSnipsParamGiven() {
		
		CommonResidue[] mSNPs = CommonResidue.getSNPs(CommonResidue.M);
		ArrayList test = new ArrayList();
		for(int i = 0; i < mSNPs.length; i++){
			test.add(mSNPs[i]);
		}
		//M = AUG; ACUG
		//char 1 = UUG, CUG, GUG
		assertTrue(test.contains(CommonResidue.residueFromCodon("UUG")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("CUG")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("GUG")));
		
		//char 2 = AGG, ACG, AAG
		assertTrue(test.contains(CommonResidue.residueFromCodon("AGG")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("ACG")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("AAG")));
		
		//char 3 = AUA, AUC, AUU
		assertTrue(test.contains(CommonResidue.residueFromCodon("AUA")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("AUC")));
		assertTrue(test.contains(CommonResidue.residueFromCodon("AUU")));
	}
	
	public void testGetSnipsNoParam() {
		CommonResidue.A.getSNPs();
	}
	
	public void testPeptideFromCodons(){
		String codons = "CUAAAA";
		Peptide p = CommonResidue.peptideFromCodons(codons);
		assertEquals(p.toString(), "LK");
	}
	
	public void testPeptideForPresentation(){
		System.out.println(CommonResidue.peptideFromCodons("GAGAAGUAU"));
		System.out.println(CommonResidue.peptideFromCodons("AGAAGUAUG"));
		System.out.println(CommonResidue.peptideFromCodons("GAAGUAUGU"));
		System.out.println(CommonResidue.peptideFromCodons("ACAUACUUC"));
		System.out.println(CommonResidue.peptideFromCodons("CAUACUUCU"));
		System.out.println(CommonResidue.peptideFromCodons("AUACUUCUC"));
	}
}
