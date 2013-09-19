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
#zip -9r ProteomeCommons.org-JAF.jar META-INF org
rm -fr META-INF

# make the archive
zip -9r ../Archive.zip *
