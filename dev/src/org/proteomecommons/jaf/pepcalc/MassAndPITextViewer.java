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

import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.proteomecommons.jaf.util.PICalculator;

/**
 * A viewer with a text field to show mass and pI info about a peptide.
 *
 * @author Jarret Falkner
 * @author Jayson Falkner - jfalkner@umich.edu
 *
 */
public class MassAndPITextViewer extends JPanel implements PeptideCalcModelViewer {
    private JTextField text;
    private PeptideCalcModel model;
    // custom formatter for sig digits
    private DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance();
    
    public MassAndPITextViewer(PeptideCalcModel model){
        this.model = model;
        text = new JTextField("Mass and pI information", 40);
        add(text);
        
        // set the digits
        df.setMaximumFractionDigits(4);
    }
    
    public void notifyViewer() {
        if(model.hasPeptide()){
            // calc the pI
            PICalculator calc = model.getPICalculator();
//			text.setText("m/z(+1): " + model.getPeptideMassWithCharge(1) + ", m/z(+2): "+ model.getPeptideMassWithCharge(2) +", m/z(+3): "+ model.getPeptideMassWithCharge(3) +", pI: " + "unimplemented");
            text.setText("m/z: " + df.format(model.getPeptideMassWithCharge())+", pI: " + df.format(calc.calculatePI(model.getPeptide())));
        }
    }
    
}
