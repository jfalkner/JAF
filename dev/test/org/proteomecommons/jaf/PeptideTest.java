package org.proteomecommons.jaf;

import junit.framework.TestCase;

public class PeptideTest extends TestCase {

	/*
	 * Test method for 'org.proteomecommons.jaf.Peptide.getNumberOfModifiedResidues()'
	 */
	public void testGetNumberOfModifiedResidues() {
		Peptide p;
		try{
			p = new Peptide("WFM");
			assertEquals(p.getNumberOfModifiedResidues(), 0);
		} catch (Exception e){
			fail();
		}
		
		try {
			p = new Peptide("WFM(Oxidation)");
			assertEquals(p.getNumberOfModifiedResidues(), 1);
		} catch (Exception e){
			fail();
		}
	}

}
