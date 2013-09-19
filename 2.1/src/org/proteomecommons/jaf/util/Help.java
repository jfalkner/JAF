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

/**
 * This class is the default class for the ProteomeCommons.org-JAF.jar library.
 * Its sole purpose is to provide help information to people who execute "java
 * -jar ProteomeCommons.org-JAF.jar" thinking something magical will happen.
 * Instead of an error, this class displays information about what the user
 * should have done.
 * 
 * @author Jayson Falkner - jfalkner@umich.edu
 */
public class Help {
	public static int majorVersion = 1;

	public static int minorVersion = 1;

	public static String primaryContactName = "Jayson Falkner";

	public static String primaryContact = "jfalkner@umich.edu";

	public static void main(String[] args) {
		System.out.println("*** ProteomeCommons.org-JAF.jar " + majorVersion
				+ "." + minorVersion + " ***");
		System.out
				.println("\nYou are seeing this message because you executed \"java -jar ProteomeCommons.org-JAF.jar\", which doesn't actually do anything. This help message is to inform you that this JAR file is primarily intended to be included in the classpath and used by other code. However, there are a few utility classes and example code that accompany this JAF. Please see the docs/index.html file for more information about how to use the example code. If you do not have the documentation that came with this JAR you may freely get a copy from http://www.ProteomeCommons.org");
		System.out.println("\nFor questions or comments please contact "
				+ primaryContactName + " - " + primaryContact);
	}
}