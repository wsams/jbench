package io.zoopaz.jbench;

import java.math.BigDecimal; 
import java.math.RoundingMode;

/**
 * @author wsams
 */
public final class Timer { 
  
    private BigDecimal start; 
    private BigDecimal laps;
  
    public Timer() { 
        this.reset(); 
    } 
  
    /** 
     * @return Returns elapsed time in nanoseconds.
     */
    public BigDecimal getElapsed() { 
        BigDecimal now = new BigDecimal(System.nanoTime()); 
        BigDecimal elapsed = now.subtract(this.start); 
        return elapsed.divide(new BigDecimal(1000000000)); 
    } 

    public BigDecimal getLapAverage() {
        BigDecimal now = new BigDecimal(System.nanoTime()); 
        BigDecimal elapsed = now.subtract(this.start); 
        BigDecimal averageElapsed = elapsed.divide(this.laps, 0, RoundingMode.HALF_UP);
        return averageElapsed.divide(new BigDecimal(1000000000), 2, RoundingMode.HALF_UP);
    }

    public void lap() {
        this.laps = this.laps.add(new BigDecimal(1));
    }

    public BigDecimal getLaps() {
        return this.laps;
    }

    public BigDecimal getStart() {
        return this.start;
    }
  
    public void reset() { 
        this.start = new BigDecimal(System.nanoTime()); 
        this.laps = new BigDecimal(1);
    } 
  
}
