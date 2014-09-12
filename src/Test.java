import io.binroot.regression.CurveFitter;

/**
 * Created by binroot on 9/11/14.
 */
public class Test {
    public static void main(String [] args) {
        double[] xs = {1, 2, 3, 4, 5, 6};
        double[] ys = {-2, 0, 6, 16, 30, 48};
        CurveFitter curveFitter = new CurveFitter(xs, ys);
        curveFitter.doFit(CurveFitter.POLY2);
        double[] params = curveFitter.getParams();

        double a = params[0];
        double b = params[1];
        double c = params[2];
        System.out.format("y = %.3f + %.3f x + %.3f x^2", a, b, c);
    }
}
