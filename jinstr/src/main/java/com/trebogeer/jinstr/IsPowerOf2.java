package com.trebogeer.jinstr;

/**
 * @author dimav
 *         Date: 12/4/14
 *         Time: 12:51 PM
 */
public class IsPowerOf2 {

    public static boolean loop(int x) {
        while (((x % 2) == 0) && x > 1) /* While x is even and > 1 */
            x /= 2;
        return (x == 1);
    }

    public static boolean checkAll(int x) {
        return (
                x == 1 || x == 2 || x == 4 || x == 8 || x == 16 || x == 32 ||
                        x == 64 || x == 128 || x == 256 || x == 512 || x == 1024 ||
                        x == 2048 || x == 4096 || x == 8192 || x == 16384 ||
                        x == 32768 || x == 65536 || x == 131072 || x == 262144 ||
                        x == 524288 || x == 1048576 || x == 2097152 ||
                        x == 4194304 || x == 8388608 || x == 16777216 ||
                        x == 33554432 || x == 67108864 || x == 134217728 ||
                        x == 268435456 || x == 536870912 || x == 1073741824);
    }

    public static boolean checkNextPower(int x) {

        int powerOfTwo = 1;

        while (powerOfTwo < x && powerOfTwo < 1073741824)
            powerOfTwo *= 2;
        return (x == powerOfTwo);

    }

    public static boolean linearSearch(int x) {
        int powersOfTwo[] =
                {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768,
                        65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608,
                        16777216, 33554432, 67108864, 134217728, 268435456, 536870912,
                        1073741824};

        int exponent = 0;

        while (powersOfTwo[exponent] < x && exponent < 30)
            exponent++;
        return (x == powersOfTwo[exponent]);
    }

    public static boolean binarySearch(int x) {
        int powersOfTwo[] =
                {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768,
                        65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608,
                        16777216, 33554432, 67108864, 134217728, 268435456, 536870912,
                        1073741824};

        boolean isAPowerOfTwo;

        int interval = 16;
        int exponent = interval; /* Start out at midpoint */

        switch (x) {
            case 0:
                isAPowerOfTwo = false;
                break;
            case 1: /* Special case makes binary search easier */
                isAPowerOfTwo = true;
                break;
            default:
                while (x != powersOfTwo[exponent] && interval > 1) {
                    if (x < powersOfTwo[exponent])
                        exponent -= interval / 2;
                    else
                        exponent += interval / 2;
                    interval /= 2;
                }
                isAPowerOfTwo = x == powersOfTwo[exponent];
        }

        return (isAPowerOfTwo);
    }


    public static boolean logSearch(int x) {
        int exponent = 0;
        boolean isAPowerOfTwo;

        int powersOfTwo[] =
                {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768,
                        65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608,
                        16777216, 33554432, 67108864, 134217728, 268435456, 536870912,
                        1073741824};

        if (x == 0 || x > 1073741824)
            isAPowerOfTwo = false;
        else {
            exponent = (int) (Math.log((double) x) / Math.log(2.0)); /* Log base 2 */
            isAPowerOfTwo = (x == powersOfTwo[exponent] ||
                    x == powersOfTwo[exponent + 1]);
        }
        return (isAPowerOfTwo);
    }

    public static boolean countOnes(int x) {
        int numberOfOneBits = 0;

        while (x != 0 && numberOfOneBits <= 1) {
            if ((x & 1) == 1) /* Is the least significant bit a 1? */
                numberOfOneBits++;
            x >>= 1;          /* Shift number one bit to the right */
        }

        return (numberOfOneBits == 1); /* 'True' if only one 1 bit */
    }

    public static boolean shiftRight(int x) {
        while (((x & 1) == 0) && x > 1) /* While x is even and > 1 */
            x >>= 1;
        return (x == 1);
    }

    public static boolean decrementAndCOmpare(int x) {
        return ((x != 0) && (x & (x - 1)) == 0);
    }

    public static boolean complementAndCompare(int x) {
        return ((x != 0) && ((x & (~x + 1)) == x));
    }
}
