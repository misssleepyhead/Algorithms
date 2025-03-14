#!/bin/bash

# Exit immediately if a command fails
set -e

echo "Setting up libraries..."
mkdir -p lib
wget -q -O lib/algs4.jar https://algs4.cs.princeton.edu/code/algs4.jar
wget -q -O lib/junit-jupiter-api.jar https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.8.1/junit-jupiter-api-5.8.1.jar
wget -q -O lib/junit-jupiter-engine.jar https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.8.1/junit-jupiter-engine-5.8.1.jar
wget -q -O lib/junit-platform-console-standalone.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.1/junit-platform-console-standalone-1.8.1.jar

echo "Compiling Java files..."
javac -cp ".:lib/algs4.jar:lib/junit-jupiter-api.jar" -d out $(find . -name "*.java" ! -path "./.idea/*" ! -path "./.github/*")

echo "Checking compiled files..."
find out/ -name "*.class"

echo "Running JUnit tests..."
java -jar lib/junit-platform-console-standalone.jar --classpath out --scan-class-path
