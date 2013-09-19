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
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A panel for viewing and specifying the charge in a PeptideCalcModel.
 *
 * @author Jarret Falkner
 * @author Jayson Falkner - jfalkner@umich.edu
 *
 */
public class ChargeSpecificationPanel extends JPanel implements DocumentListener {
	private PeptideCalcModel model;
	private JTextField charge;

	public ChargeSpecificationPanel(PeptideCalcModel model){
		this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		charge = new JTextField("0", 2);
		charge.setColumns(3);
		charge.getDocument().addDocumentListener(this);
		add(new JLabel("Charge"));
		
		add(charge);
	}

	public void insertUpdate(DocumentEvent arg0) {
		changedUpdate(arg0);
		
	}

	public void removeUpdate(DocumentEvent arg0) {
		changedUpdate(arg0);
		
	}

	public void changedUpdate(DocumentEvent arg0) {
		try{
			model.setPeptideCharge(charge.getText());
			charge.setBackground(Color.WHITE);
		} catch (NumberFormatException nfe){
			charge.setBackground(Color.RED);
		}
	}
}
