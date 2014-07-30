package com.sandeep.blackscholes;
import java.text.*;

/* This is a Java implementation of the bisection algorithm given in
   Chapter 0 of your Numerical Analysis textbook.
   Author: Martha Kosa
   Discussed in CSC 3020 on August 21, 2001 */

public abstract class Bisection {
/* The bisection algorithm is general; you need to provide code for
   the actual function for which you want to determine a root. */

   /* instance variables */
   private double tolerance;   /* indicates smallest possible interval width
                                  to search */
   private int maxIterations;  /* the maximum number of times to do
                                  the bisection */

   /* constructors */

   public Bisection() {       /* use when you want default values
                                 for both the tolerance and the maximum
                                 number of iterations */
      tolerance = 0.000001;
      maxIterations = 50;
   }

   public Bisection(double tol) {  /* you want to specify the tolerance */
      tolerance = tol;
      maxIterations = 50;
   }

   public Bisection(int maxIter) { /* you want to specify the max. number of
                                   iterations */
      tolerance = 0.000001;
      maxIterations = maxIter;
   }

   public Bisection(double tol, int maxIter) { /* you want to specify both the
                                                  tolerance and the max. # of
                                                  iterations */
      tolerance = tol;
      maxIterations = maxIter;
   }

   public double getTolerance() { /* to learn the tolerance */
      return tolerance;
   }

   public void setTolerance(double tol) { /* to change the tolerance */
      if (tol > 0)
         tolerance = tol;
   }

   public double getMaxIterations() {  /* to learn the max. # of iterations */
      return maxIterations;
   }

   public void setMaxIterations(int maxIter) { /* to set the max. # of iterations */
      if (maxIter > 0)
         maxIterations = maxIter;
   }

   public abstract double doFunction(double x); /* specifies a real-valued function of one
                                                   real-valued variable */

   /* This method does the general work of the bisection algorithm with the specified
      starting and ending points.  */
   public double doBisectionAlgorithm(double x1, double x2) {
      int iterNum = 1;    /* how many times bisection has been performed */
      double f1, f2, fmid;  /* function values */
      double mid = 0;          /* new point for function evaluation */
      /* to print numbers with 7 digits behind the decimal point */
      DecimalFormat df = new DecimalFormat("0.0000000");

      System.out.println("Iteration #\tX1\t\tX2\t\tX3\t\tF(X3)"); // \t is a tab

      do {
         f1 = doFunction(x1); // evaluate function at endpoints
         f2 = doFunction(x2);
         if (f1 * f2 > 0) {   // can't do bisection
            System.out.println("Values do not bracket a root");
            break;            // give up
         }
         mid = (x1 + x2) / 2;  // bisection gives us the "average" of the point values
         fmid = doFunction(mid); // evaluate function at midpoint
         System.out.println(iterNum + "\t\t" + df.format(x1) + "\t" + df.format(x2) + "\t" +
                            df.format(mid) + "\t" + df.format(fmid));
         if (fmid * f1 < 0)    // determine next interval bound
            x2 = mid;
         else
            x1 = mid;
         iterNum++;          // current iteration has been completed
      } while (Math.abs(x1 - x2) / 2 >= tolerance && Math.abs(fmid) > tolerance && iterNum <= maxIterations);
      // interval size minimum not reached yet and we haven't found a root and it's not time
      // to give up yet
		return mid;
   }         
}
