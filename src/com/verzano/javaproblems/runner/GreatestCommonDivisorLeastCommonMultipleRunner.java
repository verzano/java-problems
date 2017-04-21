package com.verzano.javaproblems.runner;

import com.verzano.javaproblems.problem.GreatestCommonDivisorLeastCommonMultiple;
import com.verzano.javaproblems.common.Pair;
import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsBuilder;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class GreatestCommonDivisorLeastCommonMultipleRunner extends JavaProblemRunner {
    private static final int MAX_FACTORS = 10;
    private static final long[] LOW_PRIMES = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L};
    private static final SecureRandom RNG = new SecureRandom();

    @Override
    public RunStatistics grade(int runNumber) {
        GreatestCommonDivisorLeastCommonMultiple gcdLcm = new GreatestCommonDivisorLeastCommonMultiple();
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

        builder.run(runNumber).startTimer();
        Pair<Long, Long> calculatedGcdLcmPair = gcdLcm.calculate(a, b);
        boolean pass = correctGcdLcmPair.equals(calculatedGcdLcmPair);
        builder.stopTimer().pass(pass);
        builder.message("{"
            + "'args': {"
            + "'a': " + a + ","
            + "'b': " + b
            + "},"
            + "'correct' {"
            + "gcd: " + gcd + ","
            + "lcm: " + lcm
            + "},"
            + "'calculated' {"
            + "'gcd': " + calculatedGcdLcmPair.first + ","
            + "'lcm': " + calculatedGcdLcmPair.second
            + "}"
            + "}");

        return builder.build();
    }
}
