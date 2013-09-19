#!/bin/sh

# remove the JAR
rm -f dist/ProteomeCommons.org-JAF.jar

# remove the compiled code
rm -fr dist/org

# remove snapshots of source-code
rm -fr dist/src

# remove snapshots of api docs
rm -fr dist/docs/api

# clean up the generated reference docs
rm -f dist/docs/residue-table.html

# remove the archive
rm -f Archive.zip
