package com.verzano.javaproblems.runner.problem;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsBuilder;
import com.verzano.javaproblems.problem.LeastSignificantBitSteganography;
import com.verzano.javaproblems.runner.JavaProblemRunner;
import java.security.SecureRandom;
import java.util.Arrays;

public class LeastSignificantBitSteganographyRunner extends JavaProblemRunner {
  private static final int MIN_DATA_LENGTH = 8;
  private static final int DATA_LENGTH_RANGE = 8;
  private static final SecureRandom RNG = new SecureRandom();

  private static final byte LSB = 0B0000001;
  private static final byte ALL_1 = 0B1111111;
  private static final byte ALL_1_BUT_0_LSB = 0B1111110;

  @Override
  public RunStatistics grade(int runNumber) {
    LeastSignificantBitSteganography lsbsteg = new LeastSignificantBitSteganography();
    RunStatisticsBuilder builder = new RunStatisticsBuilder().run(runNumber);

    int dataLength = MIN_DATA_LENGTH + (int)(RNG.nextDouble() * DATA_LENGTH_RANGE);

    byte[] data = new byte[dataLength];
    byte[] encodedMedium = new byte[dataLength * 8];
    RNG.nextBytes(encodedMedium);
    byte[] medium = Arrays.copyOf(encodedMedium, encodedMedium.length);

    for (int i = 0; i < encodedMedium.length; i++) {
      byte encodedByte = encodedMedium[i];
      medium[i] = (byte)(medium[i] & (RNG.nextBoolean() ? ALL_1 : ALL_1_BUT_0_LSB));
      data[i/8] = (byte)(data[i/8] | ((encodedByte & LSB) << (8 - i%8)));
    }

    builder.startTimer();
    byte[] calculatedEncodedMedium = lsbsteg.encode(medium, data);
    byte[] calculatedMedium = lsbsteg.decode(encodedMedium);
    return builder.stopTimer()
        .pass(Arrays.equals(calculatedMedium, medium)
            && Arrays.equals(calculatedEncodedMedium, encodedMedium))
        .message("{"
            + "'args': {"
            + "'medium': " + Arrays.toString(medium) + ","
            + "'data': " + Arrays.toString(data)
            + "},"
            + "'correct' {"
            + "'encodedMedium': " + Arrays.toString(encodedMedium) + ","
            + "'decodedMedium': " + Arrays.toString(medium)
            + "},"
            + "'calculated' {"
            + "'encodedMedium': " + Arrays.toString(calculatedEncodedMedium) + ","
            + "'decodedMedium': " + Arrays.toString(calculatedMedium)
            + "}"
            + "}")
        .build();
  }
}
