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

/**
 *
 * @author Jayson
 */
public class GenericModification implements Modification {
    // reference the known modifications
    public static final GenericModification Methylation = new GenericModification("Methylation", new Atom[]{Atom.H, Atom.H, Atom.C}, new Atom[0], true, false);
    public static final GenericModification diMethylation = new GenericModification("di-Methylation", new Atom[]{Atom.H, Atom.H, Atom.H, Atom.H, Atom.C, Atom.C}, new Atom[0], false, false);
    public static final GenericModification triMethylation = new GenericModification("tri-Methylation", new Atom[]{Atom.H, Atom.H, Atom.H, Atom.H, Atom.H, Atom.H, Atom.C, Atom.C, Atom.C}, new Atom[0], false, false);
    public static final GenericModification NTermAcetylation = new GenericModification("N-term Acetylation", new Atom[]{Atom.H, Atom.H, Atom.C, Atom.C, Atom.O}, new Atom[0], true, false);
    
    private String name = null;
    private Atom[] atomsLost = null;
    private Atom[] atomsAdded = null;
    private boolean isNTerminusOnly = false;
    private boolean isCTerminusOnly = false;
    
    public GenericModification(String name, Atom[] atomsAdded, Atom[] atomsLost) {
        this.name = name;
        this.atomsLost = atomsLost;
        this.atomsAdded = atomsAdded;
    }
    
    public GenericModification(String name, Atom[] atomsAdded, Atom[] atomsLost, boolean isNTermOnly, boolean isCTermOnly) {
        this(name, atomsAdded, atomsLost);
        isNTerminusOnly = isNTermOnly;
        isCTerminusOnly = isCTermOnly;
    }
    
    public boolean isNTerminusOnly() {
        return isIsNTerminusOnly();
    }
    
    public boolean isCTerminusOnly() {
        return isIsCTerminusOnly();
    }
    
    public String getName() {
        return name;
    }
    
    public Atom[] getAtomsLost() {
        return atomsLost;
    }
    
    public Atom[] getAtomsAdded() {
        return atomsAdded;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAtomsLost(Atom[] atomsLost) {
        this.atomsLost = atomsLost;
    }
    
    public void setAtomsAdded(Atom[] atomsAdded) {
        this.atomsAdded = atomsAdded;
    }
    
    public boolean isIsNTerminusOnly() {
        return isNTerminusOnly;
    }
    
    public void setIsNTerminusOnly(boolean isNTerminusOnly) {
        this.isNTerminusOnly = isNTerminusOnly;
    }
    
    public boolean isIsCTerminusOnly() {
        return isCTerminusOnly;
    }
    
    public void setIsCTerminusOnly(boolean isCTerminusOnly) {
        this.isCTerminusOnly = isCTerminusOnly;
    }
    
}
