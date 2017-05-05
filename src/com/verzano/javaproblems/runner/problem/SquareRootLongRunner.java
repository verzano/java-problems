package com.verzano.javaproblems.runner.problem;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsBuilder;
import com.verzano.javaproblems.problem.SquareRootLong;
import com.verzano.javaproblems.runner.JavaProblemRunner;
import java.security.SecureRandom;

public class SquareRootLongRunner extends JavaProblemRunner {
  private static final double NO_SQRT_PERCENTAGE = .1D;
  private static final long MAX_SQRT = 500000;
  private static final SecureRandom RNG = new SecureRandom();

  @Override
  public RunStatistics grade(int runNumber) {
    SquareRootLong sqrtl = new SquareRootLong();
    RunStatisticsBuilder builder = new RunStatisticsBuilder();

    long a;
    long sqrt;

    if (RNG.nextDouble() <= NO_SQRT_PERCENTAGE) {
      sqrt = -1;
      a = (long)(RNG.nextDouble() * MAX_SQRT);
      a *= a;
      a += 1;
    } else {
      sqrt = (long)(RNG.nextDouble() * MAX_SQRT);
      a = sqrt*sqrt;
    }

    builder.startTimer();
    long calculatedSqrt = sqrtl.calculate(a);
    return builder.stopTimer()
        .pass(sqrt == calculatedSqrt)
        .message("{"
            + "'args': {"
            + "'a': " + a
            + "},"
            + "'correct' {"
            + "'sqrt': " + sqrt
            + "},"
            + "'calculated' {"
            + "'sqrt': " + calculatedSqrt
            + "}"
            + "}")
        .build();
  }
}
