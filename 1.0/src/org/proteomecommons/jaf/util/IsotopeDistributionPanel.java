/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.proteomecommons.jaf.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author root
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
