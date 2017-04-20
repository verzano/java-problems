package com.verzano.javaproblems;

import com.verzano.javaproblems.common.Pair;

/**
 * The goal of this problem is to implement a method that will return a {@link Pair} containing the
 * Greatest Common Divisor (GCD) and Least Common Multiple (LCM) (in that order) for any given two
 * {@code long}s a and b.
 *
 * The GCD for any two numbers is defined as the largest possible integer that divides both of those
 * two numbers without a remainder.
 *
 * The LCM for any two numbers is defined as the smallest number that is divisible by both of those
 * numbers.
 *
 * A correct solution to this problem should be done without using the standard Java {@link Math}
 * library, any standard Java library that would be capable of calculating either of the values, or
 * any other third party library.  A good solution to this problem should run in better than O(n)
 * time.
 */
public class GreatestCommonDivisorLeastCommonMultiple {
  public Pair<Long, Long> calculate(long a, long b) {
    return new Pair<>(-1L, -1L);
  }
}
