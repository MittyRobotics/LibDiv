import lin.alg.lib.LinAlgLib;
import lin.alg.lib.structures.Function;
import lin.alg.lib.structures.Polynomial;

public class Main {
    public static void main(String[] args) {
        // BEING USED FOR WRITING THE DOCS NOT RELEVANT TO LIBRARY

        //Override compute to return what the function is
        //This one is x * lnx + e^(2x)
        Function function = new Function() {
            @Override
            public double compute(double x) {
                return x * Math.log(x) + Math.exp(2 * x);
            }
        };

        //guess the root of the function or put in something random
        double guess = 2;

        // maxRepetitions - how many times you want to run a Newton's method iteration (even 1 will usually suffice)
        int maxRepetitions = 1;

        double oneRootOfFunction = function.solve(guess, maxRepetitions);
    }
}
