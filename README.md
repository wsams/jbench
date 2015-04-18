jbench
======
A simple Java benchmarking utility.

Usage
-----
To use jbench just include the Timer class.

If you want to just time a segment of code you may do the following.

    Timer t = new Timer();

    String val = searchForStuff();

    System.out.println("It took " + t.getElapsed(TimeUnit.SECONDS) + " seconds to search for stuff.");

The above example times a single method call, but it's better to get an average time.

Let's see how long it takes to search for stuff on average for 1000 calls.

    Timer t = new Timer();

    for (int i = 0; i < 1000; i++) {
        String val = searchForStuff();
        t.lap();
    }

    System.out.println("It took " + t.getLapAverage(TimeUnit.SECONDS) + " seconds on average to search for stuff.");
