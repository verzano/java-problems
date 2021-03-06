package com.verzano.javaproblems.common.runstatistics;

import java.util.Collections;
import java.util.List;

public class RunStatisticsSummary {
  private static final int COLUMN_WIDTH = 20;
  private static final int N_COLS = 6;
  private static final int TOTAL_WIDTH = COLUMN_WIDTH * N_COLS + N_COLS + 1;
  private static final int INNER_WIDTH = TOTAL_WIDTH - 2;

  private RunStatisticsSummary() { }

  public static void prettyPrint(String problemName, List<RunStatistics> runStatisticsList) {
    long totalRuns = runStatisticsList.size();
    long passedRuns = runStatisticsList.stream().filter(rs -> rs.pass).count();
    long failedRuns = totalRuns - passedRuns;
    long slowestTime = runStatisticsList.stream().mapToLong(rs111 -> rs111.elapsedTime).max().orElse(-1L);
    long fastestTime = runStatisticsList.stream().mapToLong(rs1 -> rs1.elapsedTime).min().orElse(-1L);
    double averageTime = runStatisticsList.stream().mapToLong(rs11 -> rs11.elapsedTime).average().orElse(-1D);

    System.out.println("╔" + repeatChar(INNER_WIDTH, "═") + "╗");
    System.out.println("║" + padString(problemName, INNER_WIDTH) + "║");
    System.out.println("╠" + repeatChar(COLUMN_WIDTH, "═") + "╦"
        + repeatChar(COLUMN_WIDTH, "═") + "╦"
        + repeatChar(COLUMN_WIDTH, "═") + "╦"
        + repeatChar(COLUMN_WIDTH, "═") + "╦"
        + repeatChar(COLUMN_WIDTH, "═") + "╦"
        + repeatChar(COLUMN_WIDTH, "═") + "╣");
    System.out.println("║" + padString("totalRuns", COLUMN_WIDTH)
        + "║" + padString("passedRuns", COLUMN_WIDTH)
        + "║" + padString("failedRuns", COLUMN_WIDTH)
        + "║" + padString("slowestTime (nanos)", COLUMN_WIDTH)
        + "║" + padString("fastestTime (nanos)", COLUMN_WIDTH)
        + "║" + padString("averageTime (nanos)", COLUMN_WIDTH) + "║");
    System.out.println("╠" + repeatChar(COLUMN_WIDTH, "═") + "╬"
        + repeatChar(COLUMN_WIDTH, "═") + "╬"
        + repeatChar(COLUMN_WIDTH, "═") + "╬"
        + repeatChar(COLUMN_WIDTH, "═") + "╬"
        + repeatChar(COLUMN_WIDTH, "═") + "╬"
        + repeatChar(COLUMN_WIDTH, "═") + "╣");
    System.out.println("║" + padString(String.valueOf(totalRuns), COLUMN_WIDTH)
        + "║" + padString(String.valueOf(passedRuns), COLUMN_WIDTH)
        + "║" + padString(String.valueOf(failedRuns), COLUMN_WIDTH)
        + "║" + padString(String.valueOf(slowestTime), COLUMN_WIDTH)
        + "║" + padString(String.valueOf(fastestTime), COLUMN_WIDTH)
        + "║" + padString(String.valueOf(averageTime), COLUMN_WIDTH) + "║");
    System.out.println("╚" + repeatChar(COLUMN_WIDTH, "═") + "╩"
        + repeatChar(COLUMN_WIDTH, "═") + "╩"
        + repeatChar(COLUMN_WIDTH, "═") + "╩"
        + repeatChar(COLUMN_WIDTH, "═") + "╩"
        + repeatChar(COLUMN_WIDTH, "═") + "╩"
        + repeatChar(COLUMN_WIDTH, "═") + "╝");
  }

  private static String padString(String s, int len) {
    return s + repeatChar(len - s.length(), " ");
  }

  private static String repeatChar(int n, String c) {
    return String.join("", Collections.nCopies(n, c));
  }
}
