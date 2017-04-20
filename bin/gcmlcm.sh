#!/bin/bash

CLASS_NAME="GreatestCommonDivisorLeastCommonMultiple"

PACKAGE_NAME="com/verzano/javaproblems"
COMMON_IN="src/"${PACKAGE_NAME}"/common/*.java"

SOURCE_IN="src/"${PACKAGE_NAME}"/"${CLASS_NAME}".java"
SOURCE_OUT="out/src"

rm -rf ${SOURCE_OUT}
mkdir -p ${SOURCE_OUT}

javac ${COMMON_IN} ${SOURCE_IN} -d ${SOURCE_OUT}

TEST_IN="test/"${PACKAGE_NAME}"/"${CLASS_NAME}"Test.java"
TEST_OUT="out/test"

rm -rf ${TEST_OUT}
mkdir -p ${TEST_OUT}

javac -cp ${SOURCE_OUT} ${TEST_IN} -d ${TEST_OUT}

CLASSPATH=${SOURCE_OUT}":"${TEST_OUT}

java -cp ${CLASSPATH} ${PACKAGE_NAME}"/"${CLASS_NAME}"Test"