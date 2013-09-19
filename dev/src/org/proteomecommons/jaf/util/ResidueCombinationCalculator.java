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

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import org.proteomecommons.jaf.*;

/**
 * A simple calculator tool for finding
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class ResidueCombinationCalculator extends JFrame implements KeyListener {
	JLabel mass = new JLabel("Mass (Da):") {
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			super.paint(g);
		}
	};

	JTextField massValue = new JTextField() {
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			super.paint(g);
		}
	};

	JLabel tolerance = new JLabel("Mass Accuracy Error (ppm):") {
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			super.paint(g);
		}
	};

	JTextField toleranceValue = new JTextField() {
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			super.paint(g);
		}
	};

	JScrollPane scroll = new JScrollPane();

	// make a cache for up to 3 residues
	ResidueCombinationCache cache = new ResidueCombinationCache(3,
			GenericResidue.getAllResidues());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		// noop
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		// noop
	}

	/**
	 * Try to autoupdate the hits based on input.
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
		try {
			// get the strings
			String m = massValue.getText();
			String t = toleranceValue.getText();

			// update text
			if (e.getComponent().equals(massValue)) {
				m += e.getKeyChar();
			}
			// update text
			if (e.getComponent().equals(toleranceValue)) {
				t += e.getKeyChar();
			}

			// try to parse the values
			double mass = Double.parseDouble(m);
			double tolerance = Double.parseDouble(t);

			// clear all the hits
			getContentPane().remove(scroll);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.insets = new Insets(1, 1, 1, 1);

			// update the display
			ResidueCombinationCacheEntry[] matches = cache.getCache(mass,
					tolerance);

			// make the data
			String[] names = new String[] { "Sequence", "Mass (Da)" };
			String[][] data = new String[matches.length][2];
			for (int i = 0; i < matches.length; i++) {
				data[i][0] = matches[i].toString();
				data[i][1] = Double.toString(matches[i].massInDaltons);
			}

			// add a table
			JTable table = new JTable(data, names);
			scroll = new JScrollPane(table) {
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
							RenderingHints.VALUE_FRACTIONALMETRICS_ON);
					super.paint(g);
				}
			};
			getContentPane().add(scroll, gbc);

			// update the components
			this.paintComponents(getGraphics());
		} catch (Exception ee) {
			// noop
		}
	}

	public ResidueCombinationCalculator() {
		// set the title
		super("Reside Combination Calculator");

		// exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// use gridbag layout
		GridBagLayout gbl = new GridBagLayout();
		getContentPane().setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		// label for mass
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		getContentPane().add(mass, gbc);
		// button for value
		massValue.addKeyListener(this);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(massValue, gbc);

		// label for tolerance
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE;
		getContentPane().add(tolerance, gbc);
		// button for value
		toleranceValue.addKeyListener(this);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(toleranceValue, gbc);

		// pane for results
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		scroll.setAlignmentY(JScrollPane.TOP_ALIGNMENT);

		getContentPane().add(scroll, gbc);

	}

	// test the gui method
	public static void main(String[] args) {
		ResidueCombinationCalculator calc = new ResidueCombinationCalculator();
		calc.setSize(300, 300);
		calc.setVisible(true);
	}
}