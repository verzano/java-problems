package com.verzano.javaproblems.runner;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;

public abstract class JavaProblemRunner {
  public abstract RunStatistics grade(int runNumber);

  public static class Factory {
    private static final String RUNNER_PACKAGE = "com.verzano.javaproblems.runner.problem.";

    private Factory() { }

    public static JavaProblemRunner get(String problemName) {
      try {
        return (JavaProblemRunner) Class.forName(RUNNER_PACKAGE + problemName + "Runner").newInstance();
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
        throw new IllegalArgumentException("Failed to create problem " + problemName, e);
      }
    }
  }
}
