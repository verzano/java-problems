package com.verzano.javaproblems.common.runstatistics;

public class RunStatistics {
  public final long runNumber;
  public final long elapsedTime;
  public final boolean pass;
  public final String message;

  public RunStatistics(long runNumber, long elapsedTime, boolean pass, String message) {
    this.runNumber = runNumber;
    this.elapsedTime = elapsedTime;
    this.pass = pass;
    this.message = message;
  }
}
