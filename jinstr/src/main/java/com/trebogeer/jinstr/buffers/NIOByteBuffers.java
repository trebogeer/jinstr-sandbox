package com.trebogeer.jinstr.buffers;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;

import java.nio.ByteBuffer;

/**
 * @author dimav
 *         Date: 12/4/14
 *         Time: 1:59 PM
 */
public class NIOByteBuffers {
    final byte[] bytes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};
    final ByteBuffer direct = ByteBuffer.allocateDirect(4 * 1024);
    final ByteBuffer heap = ByteBuffer.allocate(4 * 1024);

    @Param({"4", "8", "16", "32", "64", "128"})
    int size;

    @Benchmark
    long directAllocate(long reps) {
        int s = this.size * 1024;
        long r = 0;
        for (int i = 0; i < reps; i++) {
            ByteBuffer bb = ByteBuffer.allocateDirect(s);
            r |= bb.capacity();
        }
        return r;
    }

    @Benchmark
    long heapAllocate(long reps) {
        int s = this.size * 1024;
        long r = 0;
        for (int i = 0; i < reps; i++) {
            ByteBuffer bb = ByteBuffer.allocate(s);
            r |= bb.capacity();
        }
        return r;
    }

    @Benchmark
    long heapWrite(long reps) {
        ByteBuffer b = heap;
        long r = 0;
        for (int i = 0; i < reps; i++) {
            r |= b.put(bytes).put((byte) 0).capacity();
            b.position(0);

        }
        return r;
    }

    @Benchmark
    long directWrite(long reps) {
        ByteBuffer b = direct;
        long r = 0;
        for (int i = 0; i < reps; i++) {
            r |= b.put(bytes).put((byte) 0).capacity();
            b.position(0);

        }
        return r;
    }
}
