package com.verzano.javaproblems.runner;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;

public abstract class JavaProblemRunner {
  public abstract RunStatistics grade(int runNumber);

  public static class Factory {
    private Factory() { }

    public static JavaProblemRunner get(String[] args) {
      if (args.length < 1) {
        throw new IllegalArgumentException("No problem name supplied");
      }

      switch (args[0].toUpperCase()) {
        case "GREATESTCOMMONDIVISORLEASTCOMMONMULTIPLE":
          return new GreatestCommonDivisorLeastCommonMultipleRunner();
        default:
          throw new IllegalArgumentException(args[0] + " is not a valid problem name");
      }
    }
  }
}
