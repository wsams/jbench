package io.zoopaz.jbench;

/**
 * @author wsams
 */
public final class Timer {

    private double start;
    private double laps;

    public Timer() {
        this.reset();
    }

    /**
     * Time is calculated in nanoseconds then divided by the given
     * time unit.
     * @param timeUnit {@link io.zoopaz.jbench.TimeUnit}
     * @return Returns elapsed time in the given time unit.
     */
    public double getElapsed(double timeUnit) {
        double now = System.nanoTime();
        double elapsed = now - this.start;
        return elapsed / timeUnit;
    }

    /**
     * Time is calculated in nanoseconds then divided by the given
     * time unit.
     * @param timeUnit {@link io.zoopaz.jbench.TimeUnit}
     * @return Returns the average time per lap in the given time unit.
     */
    public double getLapAverage(double timeUnit) {
        double now = System.nanoTime();
        double elapsed = now - this.start;
        double averageElapsed = elapsed / this.laps;
        return averageElapsed / timeUnit;
    }

    /**
     * Signals one iteration or lap of code execution. Generally executed inside a loop for each iteration.
     */
    public void lap() {
        this.laps = this.laps + 1;
    }

    /**
     * @return The current number of laps.
     */
    public double getLaps() {
        return this.laps;
    }

    /**
     * @return The start time in nanoseconds.
     */
    public double getStart() {
        return this.start;
    }

    /**
     * Resets the timer.
     */
    public void reset() {
        this.start = System.nanoTime();
        this.laps = 1;
    }

}
