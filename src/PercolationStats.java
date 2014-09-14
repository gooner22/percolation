/**
 * Maksym Hontar (c) 2014
 * Created by mhontar on 13.09.14.
 */
public class PercolationStats {

    public static final double centerX = .5;
    public static final double centerY = .5;
    private double [] statistics;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T, boolean wantsGraphics) {
        if (N<=0 || T<=0)
            throw new IllegalArgumentException();

        statistics = new double[T];
        if(wantsGraphics) {
            prepareDesk();
        }

        final double blockWidth = 1.0/N;
        final double blockR = blockWidth/2;

        System.out.println("N=" + N + ";T="+T);
        for (int i = 0; i < T; i++) {
            final Percolation percolation = new Percolation(N);

            int openSites = 0;
            while (!percolation.percolates()){
                final int randI = StdRandom.uniform(N);
                final int randJ = StdRandom.uniform(N);
                if(!percolation.isOpen(randI + 1, randJ + 1)){
                    percolation.open(randI + 1, randJ + 1);
                    openSites++;
                    if(wantsGraphics) {
                        drawSiteAtPosition(randI, randJ, blockWidth, blockR);
                    }
                }

            }
            final float probability = (float) openSites / (N * N);
            statistics[i]=probability;

        }
    }

    public double mean()                     // sample mean of percolation threshold
    {
        return StdStats.mean(statistics);
    }
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(statistics);
    }
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        return StdStats.min(statistics);
    }
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return StdStats.max(statistics);
    }
    public void printConfidenceInt()             // returns upper bound of the 95% confidence interval
    {
        final double confidenceLo = confidenceLo();
        final double confidenceHi = confidenceHi();
        for (int i = 0; i < statistics.length; i++) {
            final double statistic = statistics[i];
            if(confidenceLo<=statistic && statistic<=confidenceHi) {
                if (i == 0) System.out.print("[");
                else System.out.print(", ");
                System.out.print(statistic);
                if(i+1==statistics.length)
                System.out.println("]");
            }
        }
    }


    private void prepareDesk() {
        StdDraw.setPenColor(Draw.BLACK);
        StdDraw.filledSquare(centerX, centerY, centerX);
    }

    private void drawSiteAtPosition(int randI, int randJ, double blockWidth, double blockR) {
        StdDraw.setPenColor(Draw.WHITE);
        StdDraw.filledSquare(
                blockR + (blockWidth * randJ),
                1.0 - (blockR + (blockWidth * randI)),
                blockR);
    }

    public static void main(String[] args){
        boolean wantsGraphics = false;
        int n = 10;
        int t = 10;
        if (args.length>0)
            n = Integer.parseInt(args[0]);
        if (args.length>1)
            t = Integer.parseInt(args[1]);
        if (args.length>2)
            wantsGraphics=Boolean.parseBoolean(args[2]);

        runExperiment(wantsGraphics, n, t);
    }

    static void runExperiment(boolean wantsGraphics, int n, int t) {
        final Stopwatch stopwatch = new Stopwatch();
        final PercolationStats percolationStats = new PercolationStats(n, t, wantsGraphics);
        System.out.println("mean="+percolationStats.mean());
        System.out.println("stddev="+percolationStats.stddev());
        System.out.println("invocation time="+stopwatch.elapsedTime());
//        percolationStats.printConfidenceInt();
    }

}
