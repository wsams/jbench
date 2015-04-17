package io.zoopaz.jbench;

import static org.junit.Assert.*;
import io.zoopaz.jbench.Timer;
import java.math.BigDecimal; 
import java.math.RoundingMode;
import org.junit.Test;
import org.junit.Ignore;
import java.lang.Thread;

public class TimerTest {

    @Test
    public void test_default_lap_count() {
        Timer t = new Timer();
        assertEquals(new BigDecimal(1), t.getLaps());
    }

    @Test
    public void test_increment_laps_count() {
        Timer t = new Timer();
        assertEquals(new BigDecimal(1), t.getLaps());
        t.lap();
        assertEquals(new BigDecimal(2), t.getLaps());
        t.lap();
        t.lap();
        assertEquals(new BigDecimal(4), t.getLaps());
    }

    @Test
    public void test_reset_laps() {
        Timer t = new Timer();
        t.lap();
        t.lap();
        assertEquals(new BigDecimal(3), t.getLaps());
        t.reset();
        assertEquals(new BigDecimal(1), t.getLaps());
    }

    @Test
    public void test_reset_start() throws InterruptedException {
        Timer t = new Timer();
        BigDecimal start = t.getStart();
        Thread.sleep(1000);
        assertEquals(start, t.getStart());
        t.reset();
        assertNotEquals(start, t.getStart());
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
        assertTrue(t.getElapsed().compareTo(new BigDecimal(1)) == 1);
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
        assertTrue(t.getElapsed().compareTo(new BigDecimal(5)) == 1);

        // The average elapsed time should be greater than 1 as each lap
        // takes 2 seconds.
        assertTrue(t.getLapAverage().compareTo(new BigDecimal(1)) == 1);
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
        BigDecimal exactElapsed = t.getElapsed().setScale(0, RoundingMode.FLOOR);
        assertTrue(exactElapsed.compareTo(new BigDecimal(3)) == 0);
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
        BigDecimal exactLapAverage = t.getLapAverage().setScale(0, RoundingMode.FLOOR);
        System.out.println("exact lap average: " + exactLapAverage);
        assertTrue(exactLapAverage.compareTo(new BigDecimal(1)) == 0 || exactLapAverage.compareTo(new BigDecimal(0)) == 0);
    }

}
