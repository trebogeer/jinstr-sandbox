package com.trebogeer.jinstr;


import org.github.jamm.MemoryMeter;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 * -Djava.library.path=lib/sigar -javaagent:/home/dimav/.m2/repository/com/github/jbellis/jamm/0.3.0/jamm-0.3.0.jar
 */
public class App {

    public static void main(String[] args) {

        long id = Thread.currentThread().getId();

        com.sun.management.ThreadMXBean b = (com.sun.management.ThreadMXBean) ManagementFactory.getThreadMXBean();
        System.out.println("Current thread cpu time supported            : " + b.isCurrentThreadCpuTimeSupported());
        System.out.println("Current thread object monitor usage supported: " + b.isObjectMonitorUsageSupported());
        System.out.println("Synchronizer usage supported                 : " + b.isSynchronizerUsageSupported());
        System.out.println("Thread contention monitoring supported       : " + b.isThreadContentionMonitoringSupported());


        System.out.println("=======================================================================================");

        System.out.println("Current thread CPU time                      : " + b.getCurrentThreadCpuTime());
        System.out.println("Current thread info                          : " + b.getThreadInfo(id));
        System.out.println("=======================================================================================");
        /*
        * //Platform independent solution.
        * ObjectName threadMxBean = new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME);
          MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
          long[] memUsedPerThread = (long[]) mbeanServer.getAttribute(threadMxBean, "ThreadAllocatedBytes");
        * */
        System.out.println("Current thread allocated bytes               : " + b.getThreadAllocatedBytes(id));

        System.out.println("=======================================================================================");

        MemoryMXBean m = ManagementFactory.getMemoryMXBean();
        m.setVerbose(true);
        System.out.println("Current heap usage                           : " + m.getHeapMemoryUsage());
        System.out.println("Current non-heap usage                       : " + m.getNonHeapMemoryUsage());
        System.out.println("=======================================================================================");

        MemoryMeter mm = new MemoryMeter();
        mm.enableDebug();
        System.out.println("Current thread child obj count               : " + mm.countChildren(Thread.currentThread()));
        System.out.println("Current thread measure deep                  : " + mm.measureDeep(Thread.currentThread()));


        System.out.println("=======================================================================================");


        try {

            Sigar s = new Sigar();
            //File f = s.getNativeLibrary();
            //System.out.println(f.getAbsoluteFile());
            //logging requires log4j, don't want it for now
            //s.enableLogging(true);
            System.out.println("Current proc memory                          : " + s.getProcMem(s.getPid()));
            System.out.println("Current proc cpu                             : " + s.getProcCpu(s.getPid()));
            System.out.println("Current proc fd                              : " + s.getProcFd(s.getPid()));
            System.out.println("Current proc cred                            : " + s.getProcCred(s.getPid()));
            System.out.println("Current res limit                            : " + s.getResourceLimit());
            System.out.println("Thread cpu                                   : " + s.getThreadCpu());
            System.out.println("Current proc load avg                        : "
                    + s.getLoadAverage()[0] + " " + s.getLoadAverage()[1] + " " + s.getLoadAverage()[2]);

            s.close();
        } catch (SigarException e) {
            e.printStackTrace();
        }

        System.out.println("=======================================================================================");
        final ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        ThreadGroup g = new ThreadGroup(Thread.currentThread().getName());
        int tCount = 400;
        for (int i = 0; i < tCount; i++) {
            Thread t = new Thread(g, new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1);
                            long t = System.nanoTime();
                            map.put(t, t);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            t.start();

        }
        long start = System.nanoTime();
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Map<Long, String> threads = new HashMap<>();
        for (Thread t : threadSet) {
            threads.put(t.getId(), t.getName());
            //System.out.println(t.getId() + " " + t.getName());
        }
        System.out.println("Elapsed time nanos all                          : " + (System.nanoTime() - start));
        System.out.println("=======================================================================================");
        start = System.nanoTime();
        Thread[] tt = new Thread[tCount];
        threads = new HashMap<>();
        int a = g.enumerate(tt);
        for (int i = 0; i < a; i++) {
            threads.put(tt[i].getId(), tt[i].getName());
            //System.out.println(tt[i].getId() + " " + tt[i].getName());
        }
        System.out.println("Elapsed time nanos group                       : " + (System.nanoTime() - start));
        System.out.println("=======================================================================================");

        start = System.nanoTime();
        long[] tIds = b.getAllThreadIds();
        long[] memAllocated = b.getThreadAllocatedBytes(tIds);

        System.out.println("Elapsed time nanos all ids                     : " + (System.nanoTime() - start));

        for (int i = 0; i < tIds.length; i++)
            System.out.println(tIds[i] + " " + memAllocated[i]);

        System.out.println("=======================================================================================");
        System.out.println("Current thread measure deep                  :" + mm.measureDeep(Thread.currentThread()));
        System.exit(0);
        // Instrumentation i =
    }
}
