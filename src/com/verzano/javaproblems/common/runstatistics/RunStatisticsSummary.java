package com.verzano.javaproblems.common.runstatistics;

import java.util.List;

public class RunStatisticsSummary {
  public final long totalRuns;
  public final long passedRuns;
  public final long failedRuns;
  public final long slowestTime;
  public final long fastestTime;
  public final double averageTime;

  public RunStatisticsSummary(List<RunStatistics> runStatisticsList) {
    totalRuns = runStatisticsList.size();
    passedRuns = runStatisticsList.stream().filter(rs -> rs.pass).count();
    failedRuns = totalRuns - passedRuns;
    slowestTime = runStatisticsList.stream().mapToLong(rs111 -> rs111.elapsedTime).max().orElse(-1L);
    fastestTime = runStatisticsList.stream().mapToLong(rs1 -> rs1.elapsedTime).min().orElse(-1L);
    averageTime = runStatisticsList.stream().mapToLong(rs11 -> rs11.elapsedTime).average().orElse(-1D);
  }

  public void prettyPrint() {
    System.out.println("\n"
        + (failedRuns == 0 ? "SUCCESS" : "FAILURE")
        + " totalRuns: " + totalRuns
        + " passedRuns: " + passedRuns
        + " failedRuns: " + failedRuns
        + " slowestTime: " + slowestTime + " nanos"
        + " fastestTime: " + fastestTime + " nanos"
        + " averageTime: " + averageTime + " nanos");
  }
}
