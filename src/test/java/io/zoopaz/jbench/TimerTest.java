package io.zoopaz.jbench;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

/**
 * @author wsams
 */
public class TimerTest {

    @Test
    public void test_default_lap_count() {
        Timer t = new Timer();
        assertTrue(1 == t.getLaps());
    }

    @Test
    public void test_increment_laps_count() {
        Timer t = new Timer();
        assertTrue(1 == t.getLaps());
        t.lap();
        assertTrue(2 == t.getLaps());
        t.lap();
        t.lap();
        assertTrue(4 == t.getLaps());
    }

    @Test
    public void test_reset_laps() {
        Timer t = new Timer();
        t.lap();
        t.lap();
        assertTrue(3 == t.getLaps());
        t.reset();
        assertTrue(1 == t.getLaps());
    }

    @Test
    public void test_reset_start() throws InterruptedException {
        Timer t = new Timer();
        double start = t.getStart();
        Thread.sleep(1000);
        assertTrue(start == t.getStart());
        t.reset();
        assertFalse(start == t.getStart());
    }

    /**
     * This is difficult to test as it computes time differences. The
     * best I can come up with right now is to create a timer, sleep
     * for 2 seconds and ensure that the elapsed amount of time is
     * greater than 1 second.
     */
    @Test
    public void test_get_elapsed() throws InterruptedException {
        Timer t = new Timer();
        Thread.sleep(2000);
        assertTrue(t.getElapsed(TimeUnit.SECONDS) > 1);
    }

    /**
     * This is difficult to test as it computes average time differences. The
     * best I can come up with right now is to create a timer, sleep for 2 
     * seconds and ensure that the elapsed amount of time is greater than 1 second.
     */
    @Test
    public void test_get_average_elapsed() throws InterruptedException {
        Timer t = new Timer();
        Thread.sleep(2000);
        t.lap();
        Thread.sleep(2000);
        t.lap();
        Thread.sleep(2000);
        t.lap();
        // Testing that the total elapsed time is greater than 5 because
        // we know it's at least 6 seconds. With rounding this test should
        // be exactly 6 seconds, but I don't want to assume that.
        assertTrue(t.getElapsed(TimeUnit.SECONDS) > 5);

        // The average elapsed time should be greater than 1 as each lap
        // takes 2 seconds.
        assertTrue(t.getLapAverage(TimeUnit.SECONDS) > 1);
    }

    /**
     * This test tries to get the exact elapsed time by rounding but is not
     * perfect. It's ignored because it adds wait time to the test and is
     * not perfect.
     */
    @Ignore
    @Test
    public void test_get_elapsed_exact() throws InterruptedException {
        Timer t = new Timer();
        Thread.sleep(1000);
        t.lap();
        Thread.sleep(1000);
        t.lap();
        Thread.sleep(1000);
        t.lap();
        double exactElapsed = Math.floor(t.getElapsed(TimeUnit.SECONDS));
        assertTrue(exactElapsed == 3);
    }

    /**
     * This test tries to get the exact average elapsed time by rounding but is not
     * perfect. It's ignored because it adds wait time to the test and is not perfect.
     */
    @Ignore
    @Test
    public void test_get_average_elapsed_exact() throws InterruptedException {
        Timer t = new Timer();
        Thread.sleep(1000);
        t.lap();
        Thread.sleep(1000);
        t.lap();
        Thread.sleep(1000);
        t.lap();
        double exactLapAverage = Math.floor(t.getLapAverage(TimeUnit.SECONDS));
        assertTrue(exactLapAverage == 1 || exactLapAverage == 0);
    }

}
