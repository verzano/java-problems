package com.verzano.javaproblems.common;

public class RunStatisticsBuilder {
  private long runNumber = -1;
  private long startTimerNanos = 0;
  private long endTimerNanos = 0;
  private boolean pass = false;
  private String message = "";

  public RunStatisticsBuilder() {}

  public RunStatistics build() {
    return new RunStatistics(runNumber, endTimerNanos - startTimerNanos, pass, message);
  }

  public RunStatisticsBuilder run(long runNumber) {
    this.runNumber = runNumber;
    return this;
  }

  public RunStatisticsBuilder startTimer() {
    startTimerNanos = System.nanoTime();
    return this;
  }

  public RunStatisticsBuilder stopTimer() {
    endTimerNanos = System.nanoTime();
    return this;
  }

  public RunStatisticsBuilder pass(boolean pass) {
    this.pass = pass;
    return this;
  }

  public RunStatisticsBuilder message(String message) {
    this.message = message;
    return this;
  }
}
