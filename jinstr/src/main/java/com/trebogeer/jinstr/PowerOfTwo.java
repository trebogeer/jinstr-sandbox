package com.trebogeer.jinstr;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;

/**
 * @author dimav
 *         Date: 12/4/14
 *         Time: 1:10 PM
 */
public class PowerOfTwo {
    @Param({"1", "2", "4", "5", "16", "73", "67108864", "67108863", "8192", "8191"})
    int number;

    @Benchmark
    boolean loop(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.loop(n);
        }
        return r;
    }

    @Benchmark
    boolean binarySearch(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.binarySearch(n);
        }
        return r;
    }

    @Benchmark
    boolean checkAll(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.checkAll(n);
        }
        return r;
    }

    @Benchmark
    boolean checkNextPower(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.checkNextPower(n);
        }
        return r;
    }

    @Benchmark
    boolean complementAndCompare(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.complementAndCompare(n);
        }
        return r;
    }

    @Benchmark
    boolean countOnes(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.countOnes(n);
        }
        return r;
    }

    @Benchmark
    boolean decrementAndCompare(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.decrementAndCOmpare(n);
        }
        return r;
    }

    @Benchmark
    boolean linearSearch(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.linearSearch(n);
        }
        return r;
    }

    @Benchmark
    boolean logSearch(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.logSearch(n);
        }
        return r;
    }

    @Benchmark
    boolean shiftRight(long rep) {
        int n = this.number;
        boolean r = false;
        for (int i = 0; i < rep; i++) {
            r |= IsPowerOf2.shiftRight(n);
        }
        return r;
    }
}
