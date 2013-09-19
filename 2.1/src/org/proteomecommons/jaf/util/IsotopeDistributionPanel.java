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
import java.awt.*;

/**
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class IsotopeDistributionPanel extends JPanel{
  private CalculateIsotopeDistribution cid = null;
  private double xMax = 1;
  private double xMin = 0;
  private double yMax = 1;
  private double yMin = 1;
  public void setIsotopeDistribution(CalculateIsotopeDistribution cid){
  	this.cid = cid;
  	
  	// calc max
  	for (int i=0;i<cid.dist.length;i++){
  		if (cid.dist[i] == 0){
  			continue;
  		}
  		// check x max
  		if (cid.increment*i>xMax){
  			xMax = cid.increment*i;
  		}
  		// check yMax
  		if (cid.dist[i]>yMax){
  			yMax = cid.dist[i];
  		}
  	}
  }
  
  public void paint(Graphics g) {
  	for (int i=0;i<cid.dist.length;i++){
  		if (cid.dist[i]==0){
  			continue;
  		}
  		
  		// calc the points
  		int x =(int)(i*cid.increment/xMax*getWidth())+5;
  		int y1 = getHeight();
  		int y2 = (int)(getHeight()-cid.dist[i]/yMax*getHeight());
  		
  		// draw the line
  		g.drawLine(x, y1,x,y2);
  	}
  }
}
