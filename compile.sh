#!/bin/sh

# Clean and set up directories
rm -rf mlib mods third_party
mkdir -p mlib mods third_party

# Compile face
javac -d mods --module-source-path src \
  $(find src/info.modtest.face -name '*.java')
jar --create --file=mlib/info.modtest.face@1.0.jar \
  --module-version=1.0 -C mods/info.modtest.face .

# Compile main
javac -d mods --module-source-path src \
  $(find src/info.modtest -name '*.java')
jar --create --file=mlib/info.modtest@1.0.jar \
  --module-version=1.0 -C mods/info.modtest .

# Compile dog
javac -classpath mlib/info.modtest.face@1.0.jar \
  -d mods/dog $(find src/org.dog.sayer -name '*.java')

# Copy META-INF to target
cp -r src/org.dog.sayer/META-INF mods/dog

jar --create --file=third_party/org.dog.sayer.jar -C mods/dog/ .


# Compile person
javac -classpath mlib/info.modtest.face@1.0.jar \
   -d mods/person $(find src/com.person.sayer/ -name '*.java')

# Copy META-INF to target
cp -r src/com.person.sayer/META-INF mods/person

jar --create --file=third_party/com.person.sayer.jar -C mods/person/ .
