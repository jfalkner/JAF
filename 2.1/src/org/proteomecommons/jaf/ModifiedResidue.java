/*
 * ModifiedResidue.java
 *
 * Created on August 22, 2005, 3:25 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.proteomecommons.jaf;

/**
 *
 * @author Jayson
 */
public interface ModifiedResidue extends Residue {
    Residue getBaseResidue();
    Modification getModification(); 
}
