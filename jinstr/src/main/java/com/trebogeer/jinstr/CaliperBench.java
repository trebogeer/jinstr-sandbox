package com.trebogeer.jinstr;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;

/**
 * @author dimav
 *         Date: 12/4/14
 *         Time: 11:36 AM
 */
public class CaliperBench {

    @Param({"16", "32", "64", "128"})
    long number;

    @Benchmark
    long bitshift(long reps) {
        long n = this.number;
        long r = 0;
        for (int i = 0; i < reps;i++) {
           r |= n >> 3;
        }
        return r;
    }

    @Benchmark
    long divide(long reps) {
        long n = this.number;
        long r = 0;
        for (int i = 0; i <reps;i++) {
            r |= n/8;
        }
        return r;
    }
}
