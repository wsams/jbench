package io.zoopaz.jbench;

import static org.junit.Assert.*;
import io.zoopaz.jbench.Timer;
import java.math.BigDecimal; 
import org.junit.Test;
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

    @Test
    public void test_get_elapsed() throws InterruptedException {
        Timer t = new Timer();
        Thread.sleep(2000);
        assertTrue(t.getElapsed().compareTo(new BigDecimal(1)) == 1);
    }

    @Test
    public void test_get_average_elapsed() throws InterruptedException {
        Timer t = new Timer();
        t.lap();
        t.lap();
        t.lap();
        Thread.sleep(4000);
        assertTrue(t.getElapsed().compareTo(new BigDecimal(1)) == 1);
    }

}
