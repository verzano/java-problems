package com.verzano.javaproblems.problem;

/**
 * Steganography is a branch of data security concerned with the hiding of data within a medium such
 * that it's existence is unknown.  One of the simplest forms of this deals with the hiding of data
 * inside of bitmap images by manipulating the least significant bit of the RGB values, this method
 * is called, unsurprisingly, Least Significant Bit Steganography.
 *
 * The way in which this is achieved is that the data to be hidden is broken down into a series of
 * bits, and then each one is taken in order and used to replace the LSB of each byte in the medium.
 * Encoding using Least Significant Bit Steganography is demonstrated in the following example:
 *
 *    This example assumes the LSB is the rightmost bit
 *    data:
 *      01110101
 *    medium:
 *      11010101 00100110 11010011 11011010 00101100 10010010 11000001 00101001
 *    encodedMedium:
 *      11010100 00100111 11010011 11011011 00101100 10010011 11000000 00101001
 *
 * As you can see the original medium and the encodedMedium differ only slightly and some bytes even
 * remained unchanged.
 *
 * Decoding using this method is as simple as extracting the LSB of each byte and appending them
 * to each other to form the original data.
 *
 * This method works well because, to the naked eye, the image will appear unchanged after the data
 * has been encoded into it because only the LSB of each color's byte will have been changed which
 * will alter it only slightly, if at all.  This form of steganography is only suitable for smaller
 * amounts of data as the medium must be at least 8 times larger than the data for it to fit
 * completely into it.
 *
 * For this problem you should import no 3rd party libraries or any java libraries that would
 * perform this steganographic method on data.  It should be fairly trivial to perform the bitwise
 * operations on the data provided.  For the encoding portion the provided medium will be capable of
 * holding exactly the amount of bits in the given message.
 */
public class LeastSignificantBitSteganography {
  public byte[] encode(byte[] medium, byte[] message) {
    return null;
  }

  public byte[] decode(byte[] encodedMedium) {
    return null;
  }
}
