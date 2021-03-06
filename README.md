jbench
======
A simple Java benchmarking timer utility that functions more or less like a stopwatch.

Maven dependency
----------------

    <dependency>
        <groupId>io.zoopaz</groupId>
        <artifactId>jbench</artifactId>
        <version>1.0.0</version>
    </dependency>

Usage
-----
If you want to time a segment of code you may do the following. Instantiating `Timer`
is equivalent to `Timer.start()`.

    Timer t = new Timer();

    String val = searchForStuff();

    System.out.println("It took " + t.getElapsed(TimeUnit.SECONDS) 
            + " seconds to search for stuff.");

The above example times a single method call, but it may be useful to execute this method
several times and get the average time per "lap".

For example, let's see how long it takes to search for stuff on average for 1000 calls.

    Timer t = new Timer();

    for (int i = 0; i < 1000; i++) {
        String val = searchForStuff();
        t.lap();
    }

    System.out.println("It took " + t.getLapAverage(TimeUnit.SECONDS) 
            + " seconds on average to search for stuff.");

In some cases one may want to momentarily pause the timer.

    Timer t = new Timer();

    String val = searchForStuff();

    t.pause();

    storeValInDatabase(val);

    t.resume();

    doMoreStuffThatNeedsToBeTimed();

    System.out.println("It took " + t.getElapsed(TimeUnit.SECONDS) 
            + " seconds to search and more.");

With the introduction of `start()` and `stop()` one no longer needs to immediately get
the elapsed time.

    Timer t = new Timer();

    t.start();

    String val = searchForStuff();

    t.pause();

    storeValInDatabase(val);

    t.resume();

    doMoreStuffThatNeedsToBeTimed();

    t.stop();

    doStuff();
    doMoreStuff();

    System.out.println("It took " + t.getElapsed(TimeUnit.SECONDS) 
            + " seconds to search and more. This time we stopped the clock, did"
            + " some stuff, and then got the elapsed time.");
