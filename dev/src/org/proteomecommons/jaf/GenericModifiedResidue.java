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
import java.util.*;
/**
 * This class is an easy way to modify existing residues without having to create tons of extra classes.
 * @author Jayson
 */
public class GenericModifiedResidue extends GenericResidue implements ModifiedResidue{
    private Residue baseResidue;
    private Modification mod;
      
    public GenericModifiedResidue(Residue base, Modification m){
        this.baseResidue = base;
        this.mod = m;
        
        Atom[] resAtoms = base.getAtoms();
        Atom[] modifiedAtoms = new Atom[resAtoms.length+m.getAtomsAdded().length-m.getAtomsLost().length];
        
        // linked list of atoms
        LinkedList temp = new LinkedList();
        // add all original atoms
        for (int i=0;i<resAtoms.length;i++){
            temp.add(resAtoms[i]);
        }
        // add all added atoms
        for (int i=0;i<m.getAtomsAdded().length;i++){
            temp.add(m.getAtomsAdded()[i]);
        }
        
        // remove the appropriate atoms
        for (int i=0;i<m.getAtomsLost().length;i++){
            // iterate over atoms in temp
            for (Iterator it = temp.iterator();it.hasNext();){
                Atom a = (Atom)it.next();
                if (a.getName().equals(m.getAtomsLost()[i].getName())){
                    // remove
                    it.remove();
                    // break out, only remove that atom
                    break;
                }
            }
        }
        
        // set the correct atoms
        this.setAtoms((Atom[])temp.toArray(new Atom[0]));
        
        // set the name
        this.setName(base.getFASTAChar()+"("+m.getName()+")");
        
        // set the FASTA char
        this.setFASTAChar(base.getFASTAChar());
    }
    
    public GenericModifiedResidue(String name, Residue residue, Atom[] atomsAdded, Atom[] atomsLost) {
        this(residue, new GenericModification(name, atomsAdded, atomsLost));
    }
    
    public Residue getBaseResidue() {
        return baseResidue;
    }
    
    public Modification getModification() {
        return mod;
    }
}
