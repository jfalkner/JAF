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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.proteomecommons.io.PeptideReader;
import org.proteomecommons.jaf.Peptide;

/**
 * This is a complete text dump of the model suitable for copy/paste or other user interaction.
 *
 * @author Jarret Falkner
 *
 */
public class PeptideCalcTextViewer extends JPanel implements PeptideCalcModelViewer {
	private PeptideCalcModel model;
	private JTextArea displayedText;

	public PeptideCalcTextViewer(PeptideCalcModel model){
		this.model = model;
		displayedText = new JTextArea(20, 60);
		add(new JScrollPane(displayedText));
		//this.setPreferredSize(displayedText.getPreferredSize());
		notifyViewer();
	}

	public void notifyViewer() {
		displayedText.selectAll();
		if(model.hasPeptide()){
			Peptide p = model.getPeptide();
			displayedText.replaceSelection(
					"Sequence:\n" +
					p + "\n\n" +
					"Charge:\n" +
					model.getCurrentCharge() + "\n\n" +
					"Theoretical Mass:\n" +
					model.getPeptideMassWithCharge() + "\n\n" +
					"In-Silico Digest Results:\n" +
					digestText());
		} else {
			displayedText.replaceSelection("Specify an amino acid sequence.");
		}
	}
	
	/**
	 * Generate whatever text should be pasted in as a result of the digest
	 * @return the desired digest text
	 */
	private String digestText(){
		String returnMe = "";
		PeptideReader pr = model.getDigest();
		Peptide p = pr.next();
		while(p != null){
			returnMe = returnMe + p + "\n"; 
			p = pr.next();
		}
		return returnMe;
	}
}
