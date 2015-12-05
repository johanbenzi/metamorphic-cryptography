/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meta.encr;

/**
 *
 * @author AK
 */
public class Rotate {

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 2;

    private static int calcmask(int bitsToRotate, int direction) {
        int mask = 0;
        int c;

        if (bitsToRotate == 0) {
            return 0;
        }

        c = 0x80000000;
        mask = (c >> bitsToRotate);
        if (direction == ROTATE_RIGHT) {
            mask = (c >> (32 - bitsToRotate));
            mask = ~mask;
        } else {
            mask = (c >> bitsToRotate);
        }

        return mask;
    }

    private static int rotateRight(int value, int bitsToRotate, int sizet) {
        int tmprslt = 0;
        int mask = 0;;
        int target = 0;

        bitsToRotate %= sizet;
        target = value;

        // determine which bits will be impacted by the rotate
        mask = calcmask(bitsToRotate, ROTATE_RIGHT);

        // save off the bits which will be impacted
        tmprslt = value & mask;

        // perform the actual rotate right
        target = (value >>> bitsToRotate);

        // now rotate the saved off bits so they are in the proper place
        tmprslt <<= (sizet - bitsToRotate);

        // now add the saved off bits
        target |= tmprslt;

        // and return the result
        return target;
    }

    private static int rotateLeft(int value, int bitsToRotate, int sizet) {
        int tmprslt = 0;
        int mask = 0;;
        int target = 0;

        bitsToRotate %= sizet;

        // determine which bits will be impacted by the rotate
        mask = calcmask(bitsToRotate, ROTATE_LEFT);
        // shift the mask into the correct place (i.e. if we are delaying with a byte rotate, we
        // need to ensure we have the mask setup for a byte or 8 bits)
        mask >>>= (32 - sizet);

        // save off the affected bits
        tmprslt = value & mask;

        // perform the actual rotate
        target = (value << bitsToRotate);

        // now shift the saved off bits
        tmprslt >>>= (sizet - bitsToRotate);

        // add the rotated bits back in (in the proper location)
        target |= tmprslt;

        // now return the result
        return target;
    }

    public static int rotateRight(int value, int bitsToRotate) {
        return (rotateRight(value, bitsToRotate, 32));
    }

    public static short rotateRight(short value, int bitsToRotate) {
        short result;

        result = (short) rotateRight((0x0000ffff & value), bitsToRotate, 16);

        return result;
    }

    public static byte rotateRight(byte value, int bitsToRotate) {
        byte result;

        result = (byte) rotateRight((0x000000ff & value), bitsToRotate, 8);

        return result;
    }

    public static int rotateLeft(int value, int bitsToRotate) {
        return (rotateLeft(value, bitsToRotate, 32));
    }

    public static short rotateLeft(short value, int bitsToRotate) {
        short result;

        result = (short) rotateLeft((0x0000ffff & value), bitsToRotate, 16);

        return result;
    }

    public static byte rotateLeft(byte value, int bitsToRotate) {
        byte result;

        result = (byte) rotateLeft((0x000000ff & value), bitsToRotate, 8);

        return result;
    }
}