package com.verzano.javaproblems;

import com.verzano.javaproblems.runner.GreatestCommonDivisorLeastCommonMultipleRunner;
import com.verzano.javaproblems.runner.JavaProblemRunner;
import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsSummary;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class JavaProblems {
  private static final int THREAD_COUNT = 10;
  private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

  private static final int NUM_RUNS = 100;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Please provide a problem to be run");
      return;
    }

    List<RunStatistics> runStatisticsList = new CopyOnWriteArrayList<>();
    JavaProblemRunner problem = getProblem(args[0]);
    for (AtomicInteger run = new AtomicInteger(0); run.get() < NUM_RUNS; run.getAndIncrement()) {
      executor.execute(() -> runStatisticsList.add(problem.grade(run.get())));
    }

    try {
      executor.shutdown();
      executor.awaitTermination(1L, TimeUnit.MINUTES);
    } catch (InterruptedException ignored) {}

    new RunStatisticsSummary(runStatisticsList).prettyPrint();
  }

  private static JavaProblemRunner getProblem(String problemName) {
    switch (problemName) {
      case "GreatestCommonDivisorLeastCommonMultiple":
        return new GreatestCommonDivisorLeastCommonMultipleRunner();
      default:
        throw new IllegalArgumentException(problemName + " is not a valid problem name");
    }
  }
}
