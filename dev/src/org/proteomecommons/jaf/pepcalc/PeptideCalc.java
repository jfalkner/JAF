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

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * A graphical utility for viewing info about a user specified peptide.
 *
 * @author Jarret
 *
 */
public class PeptideCalc {

	/**
	 * Creates a new PeptideCalcModel along with the associated GUI and controls.
	 * @param args
	 */
	public static void main(String[] args) {
		PeptideCalcModel model = new PeptideCalcModel();
				
		PeptideCalcController controls = new PeptideCalcController(model); 
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Peptide Calculator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(controls);
		//frame.setJMenuBar(controls.createMenuBar());
		frame.pack();
		frame.setVisible(true);
	}
}