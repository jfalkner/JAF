/*
 *    Copyright 2004 Jayson Falkner
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
 * @author Jayson Falkner - jfalkner@umich.edu
 *
 * An abstraction for pKa values. Includes if a residue is seen on a terminus or as an internal.
 */
public class DissociationConstant {
  double internal;
  double cTerminus;
  double nTerminus;
  /**
   * Public constructor.
   * @param internal pKa value if the residue is internal.
   * @param cTerminus pKa value if the residue is on the c-terminus.
   * @param nTerminus pKa valud if the residue is on the n-terminus.
   */
  public DissociationConstant(double internal, double cTerminus, double nTerminus) {
  	this.internal = internal;
  	this.cTerminus = cTerminus;
  	this.nTerminus = nTerminus;
  }
}
