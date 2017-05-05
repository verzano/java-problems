package com.verzano.javaproblems.runner.problem;

import com.verzano.javaproblems.common.runstatistics.RunStatistics;
import com.verzano.javaproblems.common.runstatistics.RunStatisticsBuilder;
import com.verzano.javaproblems.problem.AutokeyCipher;
import com.verzano.javaproblems.runner.JavaProblemRunner;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class AutokeyCipherRunner extends JavaProblemRunner {
  private static final int MIN_KEY_PREFIX_LENGTH = 5;
  private static final int KEY_PREFIX_RANGE = 5;
  private static final int MIN_MESSAGE_LENGTH = 100;
  private static final int MESSAGE_LENGTH_RANGE = 400;
  private static final List<Character> ALPHABET = Arrays.asList(
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
      'U', 'V', 'W', 'X', 'Y', 'Z'
  );
  private static final SecureRandom RNG = new SecureRandom();
  
  @Override
  public RunStatistics grade(int runNumber) {
    AutokeyCipher autokey = new AutokeyCipher();
    RunStatisticsBuilder builder = new RunStatisticsBuilder();


    StringBuilder keyPrefixBuilder = new StringBuilder();
    StringBuilder plainTextBuilder = new StringBuilder();
    StringBuilder cipherTextBuilder = new StringBuilder();
    int keyPrefixLength = MIN_KEY_PREFIX_LENGTH + (int)(KEY_PREFIX_RANGE*RNG.nextDouble());
    int messageLength = MIN_MESSAGE_LENGTH + (int)(MESSAGE_LENGTH_RANGE*RNG.nextDouble());
    for (int i = 0; i < messageLength; i++) {
      int keyPrefixNumericalValue;
      if (i < keyPrefixLength) {
        keyPrefixNumericalValue = RNG.nextInt(ALPHABET.size());
        keyPrefixBuilder.append(ALPHABET.get(keyPrefixNumericalValue));
      } else {
        keyPrefixNumericalValue = ALPHABET.indexOf(plainTextBuilder.charAt(i - keyPrefixLength));
      }
      int plainTextNumericalValue = RNG.nextInt(ALPHABET.size());
      plainTextBuilder.append(ALPHABET.get(plainTextNumericalValue));
      cipherTextBuilder.append(
          ALPHABET.get((plainTextNumericalValue + keyPrefixNumericalValue) % ALPHABET.size()));
    }

    String keyPrefix = keyPrefixBuilder.toString();
    String plainText = plainTextBuilder.toString();
    String cipherText = cipherTextBuilder.toString();

    builder.run(runNumber).startTimer();
    String calculatedCipherText = autokey.encrypt(keyPrefix, plainText);
    String calculatedPlainText = autokey.decrypt(keyPrefix, cipherText);
    return builder.stopTimer()
        .pass(plainText.equals(calculatedPlainText) && cipherText.equals(calculatedCipherText))
        .message("{"
            + "'args': {"
            + "'keyPrefix': " + keyPrefix + ","
            + "'plainText': " + plainText + ","
            + "'cipherText': " + cipherText
            + "},"
            + "'calculated' {"
            + "'plainText': " + calculatedPlainText + ","
            + "'cipherText': " + calculatedCipherText
            + "}"
            + "}")
        .build();
  }
}
