package io.zoopaz.jbench;

/**
 * @author wsams
 */
public final class Timer {

    private double start;
    private int laps;
    private double paused;
    private double stopped;

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
        double now = this.stopped > 0 ? this.stopped : System.nanoTime();
        double elapsed = now - this.start;
        return elapsed / timeUnit;
    }

    /**
     * @return Returns elapsed time in seconds.
     */
    public double getElapsed() {
        return getElapsed(TimeUnit.SECONDS);
    }

    /**
     * Time is calculated in nanoseconds then divided by the given
     * time unit.
     * @param timeUnit {@link io.zoopaz.jbench.TimeUnit}
     * @return Returns the average time per lap in the given time unit.
     */
    public double getLapAverage(double timeUnit) {
        double now = this.stopped > 0 ? this.stopped : System.nanoTime();
        double elapsed = now - this.start;
        double averageElapsed = elapsed / this.laps;
        return averageElapsed / timeUnit;
    }

    /**
     * @return Returns the average time per lap in seconds.
     */
    public double getLapAverage() {
        return getLapAverage(TimeUnit.SECONDS);
    }

    /**
     * Signals one iteration or lap of code execution. Generally executed inside a loop for each iteration.
     */
    public void lap() {
        this.laps = this.laps + 1;
    }

    /**
     * Signals that timing should stop until resumed.
     */
    public void pause() {
        if (this.paused == 0) {
            this.paused = System.nanoTime();
        }
    }

    /**
     * Resets the start time to offset for a pause.
     */
    public void resume() {
        if (this.paused > 0) {
            this.start = this.start + (System.nanoTime() - this.paused);
            this.paused = 0.0;
        }
    }

    /**
     * Starts the timer. Same thing as instantiating the Timer class.
     */
    public void start() {
        this.reset();
    }

    /**
     * Sets the stop time of the timer. If not set getElapsed() will use
     * the current time.
     */
    public void stop() {
        this.stopped = System.nanoTime();
    }

    /**
     * @return The current number of laps.
     */
    public int getLaps() {
        return this.laps;
    }

    /**
     * @return The start time in nanoseconds.
     */
    public double getStart() {
        return this.start;
    }

    /**
     * @return The pause time in nanoseconds.
     */
    public double getPaused() {
        return this.paused;
    }

    /**
     * @return The stop time in nanoseconds.
     */
    public double getStopped() {
        return this.stopped;
    }

    /**
     * Resets the timer.
     */
    public void reset() {
        this.start = System.nanoTime();
        this.laps = 1;
        this.stopped = 0.0;
    }

}
