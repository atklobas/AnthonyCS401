import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.ThreeSum;

/******************************************************************************
 *  Compilation:  javac DoublingTest.java
 *  Execution:    java DoublingTest
 *  Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 *
 *  % java DoublingTest 
 *      250     0.0
 *      500     0.0
 *     1000     0.1
 *     2000     0.6
 *     4000     4.5
 *     8000    35.7
 *  ...
 *
 ******************************************************************************/


/**
 *  The {@code DoublingTest} class provides a client for measuring
 *  the running time of a method using a doubling test.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Exercise143 {
    private static final int MAXIMUM_INTEGER = 1000000;

    
    public static final boolean logPlot=true;
    
    
    
    
    
    
    // This class should not be instantiated.
    private Exercise143() { }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) { 
    	StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        ArrayList<double[]> records=new ArrayList<double[]>(25);
        int start=200;
        double maxtime=-Double.MIN_VALUE,mintime=.001;
        for (int n = start; n<8000; n *=2) {
            double time = timeTrial(n);
            if(time==0)continue;
            records.add(new double[]{n,time});
            if(mintime>time) {
            	mintime=time;
            }
            if(time>maxtime) {
            	maxtime=time;
            }
            if(logPlot) {
            	drawLogLog(records,mintime,maxtime,start,n);
            }else {
            	drawRegular(records,mintime,maxtime,start,n);
            }
        } 
    } 
    public static void drawLogLog(List<double[]> records, double mintime, double maxtime,int start,int current) {
		StdDraw.clear();
		double timescale=.8/ (Math.log(maxtime)-Math.log(mintime));
		double nscale=.8/(Math.log(current)-Math.log(start));
		for(double[] record : records) {
			StdDraw.point((Math.log(record[0])-Math.log(start))*nscale+.1, (Math.log(record[1])-Math.log(mintime))*timescale+.1);
		}
		for(int i=0;i<=4;i++) {
			StdDraw.text(.2*i+.1, 0.025, String.format("%2.1f", Math.exp(.2*i/nscale +Math.log(start))));
			StdDraw.text(0.025,.2*i+.1, String.format("%2.1f", Math.exp(.2*i/timescale +Math.log(mintime))));
		}
    }
    public static void drawRegular(List<double[]> records, double mintime, double maxtime,int start,int current) {
    	StdDraw.clear();
		double timescale=.8/ (maxtime-mintime);
		double nscale=.8/(current-start);
		for(double[] record : records) {
			
			StdDraw.point(((record[0])-(start))*nscale+.1, ((record[1])-(mintime))*timescale+.1);
		}
		for(int i=0;i<=4;i++) {
			StdDraw.text(.2*i+.1, 0.025, String.format("%2.1f", (.2*i/nscale +(start))));
			StdDraw.text(0.025,.2*i+.1, String.format("%2.1f", (.2*i/timescale +(mintime))));
		}
    }
    
    
    
    
    
    
    
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
