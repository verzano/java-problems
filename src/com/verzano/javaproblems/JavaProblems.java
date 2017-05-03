package com.verzano.javaproblems;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsSummary;
import com.verzano.javaproblems.runner.JavaProblemRunner;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class JavaProblems {
  private static final int THREAD_COUNT = 10;
  private static final ExecutorService problemRunExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

  private static final int NUM_RUNS = 100;

  public static void main(String[] args) {
    JavaProblemRunner problem = JavaProblemRunner.Factory.get(args);

    List<RunStatistics> runStatisticsList = new CopyOnWriteArrayList<>();
    for (AtomicInteger runNumber = new AtomicInteger(0); runNumber.get() < NUM_RUNS;) {
      final int run = runNumber.getAndIncrement();
      problemRunExecutor.execute(() -> runStatisticsList.add(problem.grade(run)));
    }

    try {
      problemRunExecutor.shutdown();
      problemRunExecutor.awaitTermination(1L, TimeUnit.MINUTES);
    } catch (InterruptedException ignored) {}

    new RunStatisticsSummary(runStatisticsList).prettyPrint();
  }
}
