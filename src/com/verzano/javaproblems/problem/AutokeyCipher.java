package com.verzano.javaproblems.problem;

/**
 * The goal of this problem is to implement the encryption and decryption methods for an autokey
 * cipher.  An autokey cipher is a shift cipher encryption scheme that utilizes a known key prefix
 * as well as the plaintext message as the the key for the encryption and decryption.
 *
 * In a shift cipher (like an autokey cipher) each letter in the alphabet (in this case the english
 * alphabet) is assigned a numerical value based on its natural index in that alphabet (i.e. A=0,
 * B=1, C=2...).  To perform encryption with a shift cipher you must add the numerical value of the
 * key with that of the plaintext to 'shift' the plaintext letter to another one.  If the addition
 * of these two values were to exceed the number of characters - 1 in the alphabet then the value is
 * around back to 0, in a similar fashion to an integer overflow.  Here are several examples:
 *
 * 1)
 *    plainText = E (numerical value of 4)
 *    key = D (numerical value of 3)
 *    cipherText = H (numerical value of 7)
 *    E + D = H --> 4 + 3 = 7
 *
 * 2)
 *    plainText = X (numerical value of 23)
 *    key = G (numerical value of 6)
 *    cipherText = D (numerical value of 3)
 *    X + G = D --> 23 + 6 = 29 (over 25 so do 29%26 to make it 3)
 *
 * 3)
 *    plaintext = FOO (numerical values of [5 14 14])
 *    key = BAR (numerical values of [1 0 17])
 *    cipherText = HOG (numerical values of [6 14 5])
 *    F + B = H --> 5 + 1 = 6
 *    O + A = O --> 14 + 0 = 14
 *    O + R = G --> 14 + 17 = 31 (over 25 so do 31%26 to make it 5)
 *
 * Decryption with a shift cipher works the same way as encryption except that instead of adding the
 * numerical value of the key to the plain text you subtract it from the cipher text.  In much the
 * same way that the numerical values should 'roll over' when they exceed the alphabets length in
 * encryption the values should 'roll under' during decryption similar to an integer underflow.
 *
 * This is the general method for encryption/decryption in all shift ciphers, an autokey cipher is
 * a special case in that it's key is built during encryption and decryption.  During encryption you
 * begin encrypting with the key prefix as you would in a normal shift cipher but once you reach the
 * end of the key prefix you use the plain text to encrypt itself.  This is demonstrated in the
 * following example:
 *
 *    plainText = TEAMEDWARD
 *    keyPrefix = BELLA
 *    key = BELLATEAMEDWARD (just append the plainText to the keyPrefix)
 *    cipherText = UILXEWAADH
 *
 * To decrypt with an autokey cipher you perform normal shift cipher decryption using the keyPrefix
 * and every time a character is decrypted it is appended to the end of the key prefix and used for
 * later decryption until the entire cipher text has been decrypted.  To continue the previous
 * example:
 *
 *    cipherText = UILXEWAADH
 *    keyPrefix = BELLA
 *    key = BELLA
 *
 *    Step 1)
 *    plainText = T
 *    U - B = T
 *    key = BELLAT
 *
 *    Step 2)
 *    plainText = TE
 *    I - E = E
 *    key = BELLATE
 *
 *    Step 3)
 *    plainText = TEA
 *    L - L = A
 *    key = BELLATEA
 *
 *    Step 4)
 *    plainText = TEAM
 *    X - L = M
 *    key = BELLATEAM
 *
 *    Step 5)
 *    plainText = TEAME
 *    E - A = E
 *    key = BELLATEAME
 *
 *    Step 6)
 *    plainText = TEAMED
 *    W - T = D
 *    key = BELLATEAMED
 *
 *    ... process continues until cipherText is fully decrypted ...
 *
 * In this problem all supplied characters will be uppercase and the supplied keyPrefix will always
 * be shorter than the plainText or cipherText and will have a length greater than 1.
 */
public class AutokeyCipher {
  public String encrypt(String keyPrefix, String plainText) {
    return null;
  }

  public String decrypt(String keyPrefix, String cipherText) {
    return null;
  }
}
