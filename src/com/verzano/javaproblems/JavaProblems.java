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

// TODO make the problems capable of generating, or reading data
// TODO add a thread timeout feature
// TODO use opts in javaproblems.sh for options
// TODO add more statistical data to the RunStatistics
public abstract class JavaProblems {
  private static final int DEFAULT_NUM_RUNS = 500;
  private static final int DEFAULT_NUM_THREADS = 8;

  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("No problem name provided");
    }
    JavaProblemRunner problem = JavaProblemRunner.Factory.get(args[0]);

    List<RunStatistics> runStatisticsList = new CopyOnWriteArrayList<>();

    int numThreads = args.length > 2 ? Integer.valueOf(args[2]) : DEFAULT_NUM_THREADS;
    ExecutorService problemRunExecutor = Executors.newFixedThreadPool(numThreads);

    int numRuns = args.length > 1 ? Integer.valueOf(args[1]) : DEFAULT_NUM_RUNS;
    for (AtomicInteger runNumber = new AtomicInteger(0); runNumber.get() < numRuns;) {
      final int run = runNumber.getAndIncrement();
      problemRunExecutor.execute(() -> runStatisticsList.add(problem.grade(run)));
    }

    try {
      problemRunExecutor.shutdown();
      problemRunExecutor.awaitTermination(1L, TimeUnit.MINUTES);
    } catch (InterruptedException ignored) {}

    RunStatisticsSummary.prettyPrint(args[0], runStatisticsList);
  }
}
