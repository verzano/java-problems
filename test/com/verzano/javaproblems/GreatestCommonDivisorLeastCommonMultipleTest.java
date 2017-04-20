package com.verzano.javaproblems;

import com.verzano.javaproblems.common.Pair;
import com.verzano.javaproblems.common.RunStatistics;
import com.verzano.javaproblems.common.RunStatisticsBuilder;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class GreatestCommonDivisorLeastCommonMultipleTest {
  private static final int NUM_RUNS = 50;
  private static final int MAX_FACTORS = 10;
  private static final long[] LOW_PRIMES = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L};
  private static final SecureRandom RNG = new SecureRandom();

  public static void test(GreatestCommonDivisorLeastCommonMultiple gcdLcm) {
    List<RunStatistics> runStatisticsList = new LinkedList<>();
    RunStatisticsBuilder builder = new RunStatisticsBuilder();

    for (int run = 0; run < NUM_RUNS; run++) {
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

      builder.run(run).startTimer();
      Pair<Long, Long> calculatedGcdLcmPair = gcdLcm.calculate(a, b);
      builder.stopTimer().pass(correctGcdLcmPair.equals(calculatedGcdLcmPair));

      RunStatistics runStatistics = builder.build();
      runStatisticsList.add(runStatistics);

      if (!runStatistics.pass) {
        System.out.println("FAILED -- "
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
    }

    RunStatistics.printRunStatisticsList(runStatisticsList);
  }

  public static void main(String[] args) {
    test(new GreatestCommonDivisorLeastCommonMultiple());
  }
}
