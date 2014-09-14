import static java.lang.Math.pow;

/**
 * Social Glutton (c) 2014
 * Created by mhontar on 14.09.14.
 */
public class PercolationStatsLauncher {
    public static void main(String[] args){
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j <=10 ; j++) {
                PercolationStats.runExperiment(false, (int) pow(2, j), (int) pow(2, i));
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("general invocation time took "+stopwatch.elapsedTime());
        System.out.println("-------------------------------------------");
    }

}
