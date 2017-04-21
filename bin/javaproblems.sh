#!/usr/bin/env bash

PACKAGE="com/verzano/javaproblems"

SOURCE_DIR="src/"${PACKAGE}
SOURCE_IN=${SOURCE_DIR}"/*.java "${SOURCE_DIR}"/runner/*.java "${SOURCE_DIR}"/common/*.java "${SOURCE_DIR}"/common/runstatistics/*.java"

PROBLEM=$1
PROBLEM_IN=${SOURCE_DIR}"/problem/"${PROBLEM}".java"

SOURCE_OUT="out/src"

rm -rf ${SOURCE_OUT}
mkdir -p ${SOURCE_OUT}

javac ${SOURCE_IN} ${PROBLEM_IN} -d ${SOURCE_OUT}

java -cp ${SOURCE_OUT} ${PACKAGE}"/JavaProblems" ${PROBLEM}
