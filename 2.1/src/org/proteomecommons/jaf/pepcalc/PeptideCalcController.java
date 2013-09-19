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
package org.proteomecommons.jaf.pepcalc;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.proteomecommons.jaf.Atom;
import org.proteomecommons.jaf.CommonResidue;
import org.proteomecommons.jaf.DefaultAtomSet;
import org.proteomecommons.jaf.GenericModifiedResidue;
import org.proteomecommons.jaf.GenericResidue;
import org.proteomecommons.jaf.Residue;
import org.proteomecommons.jaf.util.SolomonPICalculator;
import org.proteomecommons.jaf.util.TabbPICalculator;

/**
 * Controls and GUI components for the PeptideCalcModel.
 *
 * @author Jarret Falkner
 *
 */
public class PeptideCalcController extends JPanel implements ActionListener{
    /**
     * Watches the document containing the peptide sequence to processes user input
     * @author Jarret
     *
     */
    class PeptideCalcControllerSequenceListener implements DocumentListener{
        
        public void insertUpdate(DocumentEvent de) {
            updatePeptideInputField(de);
            
        }
        
        public void removeUpdate(DocumentEvent de) {
            updatePeptideInputField(de);
            
        }
        
        public void changedUpdate(DocumentEvent de) {
            updatePeptideInputField(de);
            
        }
        
        private void updatePeptideInputField(DocumentEvent de){
            try{
                model.setPeptide(de.getDocument().getText(0, de.getDocument().getLength()));
                pepText.setBackground(Color.WHITE);
            } catch (Exception e){
                pepText.setBackground(Color.RED);
            }
        }
    }
    
    /**
     * Panel and action handler for backspace and clear
     * @author Jarret
     *
     */
    class BackSpaceAndClearButtons extends JPanel implements ActionListener{
        public BackSpaceAndClearButtons(){
            setLayout(new GridLayout(1, 2));
            JButton jb = new JButton("Backspace");
            jb.setActionCommand("Backspace");
            jb.addActionListener(this);
            add(jb);
            
            jb = new JButton("Clear");
            jb.setActionCommand("Clear");
            jb.addActionListener(this);
            add(jb);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("Backspace")){
                int deleteBefore = pepText.getCaretPosition();
                String performBackspace = pepText.getText();
                if(deleteBefore >0){
                    pepText.setText(performBackspace.substring(0, deleteBefore-1) + performBackspace.substring(deleteBefore, performBackspace.length()));
                    pepText.setCaretPosition(deleteBefore-1);
                }
            } else if (ae.getActionCommand().equals("Clear")){
                pepText.setText("");
            }
            pepText.requestFocus();
            
        }
    }
    
    /**
     * A group of buttons for amino acids.
     * @author Jarret
     *
     */
    class AminoAcidButtons extends JPanel implements ActionListener{
        //sorts buttons for proper placement
        class CharComparator implements Comparator{
            public int compare(Object arg0, Object arg1) {
                return ((CommonResidue)arg0).getOneLetter() - ((CommonResidue)arg1).getOneLetter();
            }
        }
        class ToStringComparator implements Comparator{
            public int compare(Object one, Object two) {
                return one.toString().compareTo(two.toString());
            }
        }
        
        //semihack to make right click listeners for each jbutton in the calculator
        //these right click listeners display the possible mods for that amino acid.
        class RightClickListener implements MouseListener{
            private JButton jb;
            
            /**
             *
             * @param jb remember the button created with this listener so we can later lookup the amino acid on that buttom
             */
            public RightClickListener(JButton jb){
                this.jb = jb;
            }
            
            /**
             * Process the right clicks
             */
            public void mouseClicked(MouseEvent arg0) {
                if(SwingUtilities.isRightMouseButton(arg0)){
                    CommonResidue cr = CommonResidue.getResidue(jb.getActionCommand().charAt(0));
                    Residue[] generics = GenericResidue.getAllResidues();
                    
                    //this could be moved into the JAF
                    ArrayList residueMatches = new ArrayList();
                    for(int index = 0; index < generics.length; index++){
                        if(generics[index].getFASTAChar() == jb.getActionCommand().charAt(0) &&
                                generics[index] != cr){
                            residueMatches.add(generics[index]);
                        }
                    }
                    
                    Collections.sort(residueMatches, new ToStringComparator());
                    
                    //add the unmodified version back into the mix as the first item
                    ArrayList forDisplay = new ArrayList();
                    forDisplay.add(cr);
                    forDisplay.addAll(residueMatches);
                    
                    //prompt for the base residue that they want
                    Residue userChoice = (Residue)JOptionPane.showInputDialog(jb,
                            "Select the residue to add:",
                            "Modified residue entry",
                            JOptionPane.OK_OPTION,
                            null,
                            (Object[])forDisplay.toArray(),
                            (Object)GenericResidue.getResidueByFASTAChar(jb.getActionCommand().charAt(0)));
                    
                    //and add the user's modified choice
                    if(userChoice != null){
                        if(userChoice instanceof CommonResidue){
                            pepText.setText(pepText.getText() + userChoice.getFASTAChar());
                        } else {
                            pepText.setText(pepText.getText() + userChoice);
                        }
                    }
                }
            }
            
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        }
        public AminoAcidButtons(){
            setLayout(new GridLayout(5, 5));
            
            CommonResidue[] cr = GenericResidue.getCommonResidues();
            Arrays.sort(cr, new CharComparator());
            
            //loop to create buttons for each CommonResidue
            for(int i = 0; i < cr.length; i++){
                String residueAbbrev = new String(new char[]{cr[i].getOneLetter()});
                JButton jb = new JButton(residueAbbrev);
                jb.setActionCommand(residueAbbrev);
                jb.addActionListener(this);
                jb.addMouseListener(new RightClickListener(jb));
                
                add(jb);
            }
            
            //add the nonstandard buttons
            JButton special;
            /**
             * special = new JButton("?");
             * special.setActionCommand("?");
             * special.addActionListener(this);
             * add(special);
             */
            
            //some spacing panels for the bottom row so + and = are right-aligned.
            add(new JPanel());
            add(new JPanel());
            add(new JPanel());
            
            
            special = new JButton("+");
            special.setActionCommand("+");
            special.addActionListener(this);
            add(special);
            
            
            special = new JButton("=");
            special.setActionCommand("=");
            special.addActionListener(this);
            add(special);
        }
        
        public void actionPerformed(ActionEvent ae) {
            //Handle special cases of + and =
            if(ae.getActionCommand().equals("+")){
                //prompt for the base residue that will be modified
                CommonResidue modifyMe = (CommonResidue)JOptionPane.showInputDialog(this,
                        "Choose the base residue where this modification occurs:",
                        "Choose base residue",
                        JOptionPane.OK_OPTION,
                        null,
                        (Object[])GenericResidue.getCommonResidues(),
                        (Object)GenericResidue.Tryptophan);
                //check for base residue being cancelled
                if(modifyMe==null){return;}
                
                //prompt for name
                String modName = JOptionPane.showInputDialog(this,
                        "Enter the name of the new modification:",
                        "Name the modification",
                        JOptionPane.OK_OPTION);
                if(modName==null || modName.equals("")){return;}
                
                //prompt for added/removed atoms
                String atoms = JOptionPane.showInputDialog(this,
                        "Enter the amount of atoms added and removed by this modification.\n" +
                        "For example: 2C -3S",
                        "Added and Removed Atoms",
                        JOptionPane.OK_OPTION);
                if(atoms==null || atoms.equals("")){return;};
                
                //parse their input
                ArrayList atomsAdded = new ArrayList();
                ArrayList atomsLost = new ArrayList();
                
                String[] atomDiffs = atoms.split(" ");
                for(int diffIndex = 0; diffIndex < atomDiffs.length; diffIndex++){
                    String curDiff = atomDiffs[diffIndex];
                    if(curDiff.length() > 0){
                        char atom = curDiff.substring(curDiff.length()-1, curDiff.length()).toCharArray()[0];
                        int howMany = Integer.parseInt(curDiff.substring(0, curDiff.length()-1));
                        DefaultAtomSet das = new DefaultAtomSet();
                        if(howMany >0 ){
                            while(howMany >0){
                                atomsAdded.add(das.getAtom(atom));
                                howMany--;
                            }
                        } else {
                            while(howMany < 0){
                                atomsLost.add(das.getAtom(atom));
                                howMany++;
                            }
                        }
                    }
                }
                
                //final user confirmation to add the residue they've built
                int confirm = JOptionPane.showConfirmDialog(this,
                        "About to add this modification, is it correct?\n" +
                        "Residue: \n" +
                        modifyMe + "\n\n" +
                        "Atoms added:\n" +
                        atomsAdded + "\n\n" +
                        "Atoms lost:\n" +
                        atomsLost,
                        "Add this modification?", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    GenericResidue.addResidue(new GenericModifiedResidue(modName, modifyMe, (Atom[])atomsAdded.toArray(new Atom[0]), (Atom[])atomsLost.toArray(new Atom[0])));
                }
                //System.out.println(ae);
                
            } else if(ae.getActionCommand().equals("=")){
                JFrame frame = new JFrame();
                frame.getContentPane().add(new PeptideCalcTextViewer(model));
                frame.pack();
                frame.setVisible(true);
                
                
            } else { // + and = are handled, so append the button's text to the peptide sequence
                String oldText = pepText.getText();
                pepText.setText(oldText + ae.getActionCommand());
            }
            
        }
    }
    
    //instance variables for the controller
    private PeptideCalcModel model;
    private ButtonGroup digests;
    private ButtonGroup pICalculators;
    private JTextField pepText;
    
    public PeptideCalcController(PeptideCalcModel model){
        this.model = model;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //add the top two simple text boxes
        pepText = new JTextField("PEPTIDE", 40);
        try{
            model.setPeptide("PEPTIDE");
        } catch (Exception e){
            //noop
        }
        pepText.getDocument().addDocumentListener(new PeptideCalcControllerSequenceListener());
        JPanel pepTextPanel = new JPanel();
        pepTextPanel.add(pepText);
        
        add(pepTextPanel);
        MassAndPITextViewer maptv = new MassAndPITextViewer(model);
        model.addViewer(maptv);
        add(maptv);
        
        //add the buttons on the right and the radio buttons on the left
        JPanel holdsRadioButtonsAndCalcButtons = new JPanel();
        
        JPanel radioPanel = new JPanel(); //for the digest radio buttons
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        
        //the charge specification panel goes on top of the radio buttons
        radioPanel.add(new ChargeSpecificationPanel(model));
        
        
        // add digest options
        radioPanel.add(new JLabel("Digest Options"));
        digests = new ButtonGroup();
        JRadioButton jrb = new JRadioButton("Tryptic Digest");
        jrb.setActionCommand("Tryptic Digest");
        jrb.addActionListener(this);
        jrb.setSelected(true);
        jrb.setMnemonic(KeyEvent.VK_T);
        radioPanel.add(jrb);
        digests.add(jrb);
        
        jrb = new JRadioButton("SNP Digest");
        jrb.setActionCommand("SNP Digest");
        jrb.addActionListener(this);
        jrb.setMnemonic(KeyEvent.VK_S);
        radioPanel.add(jrb);
        digests.add(jrb);
        
        jrb = new JRadioButton("Non-specific Digest");
        jrb.setActionCommand("Non-specific Digest");
        jrb.addActionListener(this);
        jrb.setMnemonic(KeyEvent.VK_N);
        radioPanel.add(jrb);
        digests.add(jrb);
        
        
        
//        // make a panel for the pI calculator options.
//        JPanel pIRadioPanel = new JPanel();
//        pIRadioPanel.setLayout(new BoxLayout(pIRadioPanel, BoxLayout.Y_AXIS));
        
        radioPanel.add(new JLabel("pKa Set"));
        pICalculators = new ButtonGroup();
        jrb = new JRadioButton("Tabb");
        jrb.setActionCommand("Tabb");
        jrb.addActionListener(this);
        jrb.setSelected(true);
//		jrb.setMnemonic(KeyEvent.VK_T);
        radioPanel.add(jrb);
        pICalculators.add(jrb);
        
        jrb = new JRadioButton("Solomon");
        jrb.setActionCommand("Solomon");
        jrb.addActionListener(this);
//		jrb.setMnemonic(KeyEvent.VK_S);
        radioPanel.add(jrb);
        pICalculators.add(jrb);
        
        //finally add the left content of the GUI
        holdsRadioButtonsAndCalcButtons.add(radioPanel);
//        //finally add the left content of the GUI
//        holdsRadioButtonsAndCalcButtons.add(pIRadioPanel);
        
        
        JPanel holdsButtons = new JPanel(); //right buttons
        holdsButtons.setLayout(new BoxLayout(holdsButtons, BoxLayout.Y_AXIS)); //top row: backspace and clear, buttom row: amino acids
        holdsButtons.add(new BackSpaceAndClearButtons());
        holdsButtons.add(new AminoAcidButtons());
        holdsRadioButtonsAndCalcButtons.add(holdsButtons);
        add(holdsRadioButtonsAndCalcButtons);
    }
    
    /**
     * Action handlers for the radio buttons
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Tryptic Digest")){
            model.setDigestMode("Tryptic");
        } else if (e.getActionCommand().equals("Non-specific Digest")){
            model.setDigestMode("Non-specific");
        } else if (e.getActionCommand().equals("SNP Digest")){
            model.setDigestMode("SNP");
        }
        
        // handle pI sets
        if (e.getActionCommand().equals("Tabb")) {
            model.setPICalculator(new TabbPICalculator());
            
        } else if (e.getActionCommand().equals("Solomon")) {
            model.setPICalculator(new SolomonPICalculator());
            
        }
        
        
        else {
            System.out.println("Unknown command: " + e.getActionCommand());
        }
    }
}
