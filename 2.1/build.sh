#!/bin/sh

# build the code
javac -sourcepath src -classpath .:lib/ProteomeCommons.org-IO.jar -d dist src/org/proteomecommons/*/*.java src/org/proteomecommons/*/*/*.java

# copy needed files in to the distribution
cp -r src dist

# make the Java docs
rm -fr dist/docs/api/*
cd src
javadoc -d ../dist/docs/api org/proteomecommons/jaf/*.java org/proteomecommons/jaf/*/*.java
cd ..


# make a JAR
cd dist
jar -cfm ProteomeCommons.org-JAF.jar src/META-INF/MANIFEST.MF org
jarsigner -keystore ../../../ProteomeCommons/keystore -storepass password ProteomeCommons.org-JAF.jar jfalkner
rm -fr META-INF

# make the HTML residue docs
/opt/jdk1.5.0/bin/java -cp ProteomeCommons.org-JAF.jar org.proteomecommons.jaf.util.HTMLResidueCombinationTable 3 > docs/residue-table.html
# make the HTML common residue reference
/opt/jdk1.5.0/bin/java -cp ProteomeCommons.org-JAF.jar org.proteomecommons.jaf.util.HTMLResidueReference > docs/common-amino-acid-table.html
# make the HTML all residue reference
/opt/jdk1.5.0/bin/java -cp ProteomeCommons.org-JAF.jar org.proteomecommons.jaf.util.HTMLAllResidueReference > docs/all-residue-table.html
# make the HTML all atom reference
/opt/jdk1.5.0/bin/java -cp ProteomeCommons.org-JAF.jar org.proteomecommons.jaf.util.HTMLAtomReference > docs/atom-table.html

# make the archive
zip -9r ../Archive.zip *
