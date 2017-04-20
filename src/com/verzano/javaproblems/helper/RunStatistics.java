package com.verzano.javaproblems.helper;

import java.util.List;

public class RunStatistics {
  public final long runNumber;
  public final long elapsedTime;
  public final boolean pass;

  public RunStatistics(long runNumber, long elapsedTime, boolean pass) {
    this.runNumber = runNumber;
    this.elapsedTime = elapsedTime;
    this.pass = pass;
  }

  public static void printRunStatisticsList(List<RunStatistics> runStatisticsList) {
    System.out.println("\n"
        + (runStatisticsList.stream().allMatch(rs -> rs.pass) ? "SUCCESS" : "FAILURE")
        + " passes: " + runStatisticsList.stream().filter(rs -> rs.pass).count()
        + " failures: " + runStatisticsList.stream().filter(rs -> !rs.pass).count()
        + " slowestRun: " + runStatisticsList.stream().mapToLong(rs111 -> rs111.elapsedTime).max().orElse(-1L) + " nanos"
        + " fastestRun: " + runStatisticsList.stream().mapToLong(rs1 -> rs1.elapsedTime).min().orElse(-1L) + " nanos"
        + " averageRun: " + runStatisticsList.stream().mapToLong(rs11 -> rs11.elapsedTime).average().orElse(-1L) + " nanos");
  }
}
