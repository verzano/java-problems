package com.verzano.javaproblems;

import com.verzano.javaproblems.common.Pair;
import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsBuilder;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsSummary;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GreatestCommonDivisorLeastCommonMultipleTest {
  private static final int NUM_RUNS = 100;
  private static final int MAX_FACTORS = 10;
  private static final long[] LOW_PRIMES = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L};
  private static final SecureRandom RNG = new SecureRandom();

  private static final int THREAD_COUNT = 10;
  private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

  public static void test(GreatestCommonDivisorLeastCommonMultiple gcdLcm) {
    List<RunStatistics> runStatisticsList = new CopyOnWriteArrayList<>();
    for (AtomicInteger run = new AtomicInteger(0); run.get() < NUM_RUNS; run.getAndIncrement()) {
      executor.execute(() -> {
        RunStatisticsBuilder builder = new RunStatisticsBuilder();
        int nPrimes = RNG.nextInt(MAX_FACTORS) + 1;

        long a = 1L;
        List<Long> aFactors = new LinkedList<>();

        long b = 1L;
        List<Long> bFactors = new LinkedList<>();

        long gcd = 1L;
        long lcm = 1L;
        for (int i = 0; i < nPrimes; i++) {
          long aFactor = LOW_PRIMES[RNG.nextInt(LOW_PRIMES.length)];
          a *= aFactor;

          long bFactor = LOW_PRIMES[RNG.nextInt(LOW_PRIMES.length)];
          b *= bFactor;

          if (aFactor == bFactor) {
            gcd *= aFactor;
            lcm *= aFactor;
          } else {
            aFactors.add(aFactor);
            bFactors.add(bFactor);

            boolean inB = bFactors.remove(aFactor);
            if (inB) {
              gcd *= aFactor;
              aFactors.remove(aFactor);
            }

            boolean inA = aFactors.remove(bFactor);
            if (inA) {
              gcd *= bFactor;
              bFactors.remove(bFactor);
            }

            lcm *= (inB ? 1 : aFactor) * (inA ? 1 : bFactor);
          }
        }
        Pair<Long, Long> correctGcdLcmPair = new Pair<>(gcd, lcm);

        builder.run(run.get()).startTimer();
        Pair<Long, Long> calculatedGcdLcmPair = gcdLcm.calculate(a, b);
        boolean pass = correctGcdLcmPair.equals(calculatedGcdLcmPair);
        builder.stopTimer().pass(pass);
        if (pass) {
          builder.message("FAILED -- "
              + "Values: {"
              + "a: " + a + ", "
              + "b: " + b
              + "} "
              + "Correct Pair {"
              + "gcd: " + gcd + ", "
              + "lcm: " + lcm
              + "} "
              + "Calculated Pair {"
              + "gcd: " + calculatedGcdLcmPair.first + ", "
              + "lcm: " + calculatedGcdLcmPair.second
              + "}");
        }

        RunStatistics runStatistics = builder.build();
        runStatisticsList.add(runStatistics);
      });
    }
    executor.shutdown();
    try {
      executor.awaitTermination(1L, TimeUnit.MINUTES);
    } catch (InterruptedException ignored) {}

    new RunStatisticsSummary(runStatisticsList).prettyPrint();
  }

  public static void main(String[] args) {
    test(new GreatestCommonDivisorLeastCommonMultiple());
  }
}
