package com.verzano.javaproblems;

import com.verzano.javaproblems.helper.Pair;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class GreatestCommonDivisorLeastCommonMultipleTest {
  private static final int NUM_RUNS = 1000;
  private static final int MAX_FACTORS = 10;
  private static final long[] LOW_PRIMES = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L};
  private static final SecureRandom RNG = new SecureRandom();

  public static void test(GreatestCommonDivisorLeastCommonMultiple gcdLcm) {
    boolean allMatch = true;
    long slowestRun = Long.MIN_VALUE;
    long fastestRun = Long.MAX_VALUE;
    long averageAcu = 0L;
    int passes = 0;
    int failures = 0;


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

      final long startNanos = System.nanoTime();
      Pair<Long, Long> calculatedGcdLcmPair = gcdLcm.calculate(a, b);
      final long elapsedNanos = System.nanoTime() - startNanos;

      slowestRun = Math.max(slowestRun, elapsedNanos);
      fastestRun = Math.min(fastestRun, elapsedNanos);
      averageAcu += elapsedNanos;

      boolean match = correctGcdLcmPair.equals(calculatedGcdLcmPair);
      allMatch &= match;
      if (!match) {
        failures++;
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
      } else {
        passes++;
      }
    }

    System.out.println((allMatch ? "SUCCESS" : "FAILURE")
        + " passes: " + passes
        + " failures: " + failures
        + " slowestRun: " + slowestRun + " nanos"
        + " fastestRun: " + fastestRun + " nanos"
        + " averageRun: " + averageAcu/NUM_RUNS + " nanos");
  }

  public static void main(String[] args) {
    test(new GreatestCommonDivisorLeastCommonMultiple());
  }
}
