package io.binroot.regression;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by binroot on 9/11/14.
 */
public class IJ {
    private static DecimalFormat[] df;
    private static DecimalFormat[] sf;
    private static DecimalFormatSymbols dfs;

    /** Converts a number to a rounded formatted string.
     The 'decimalPlaces' argument specifies the number of
     digits to the right of the decimal point (0-9). Uses
     scientific notation if 'decimalPlaces is negative. */
    public static String d2s(double n, int decimalPlaces) {
        if (Double.isNaN(n)||Double.isInfinite(n))
            return ""+n;
        if (n==Float.MAX_VALUE) // divide by 0 in FloatProcessor
            return "3.4e38";
        double np = n;
        if (n<0.0) np = -n;
        if (df==null) {
            dfs = new DecimalFormatSymbols(Locale.US);
            df = new DecimalFormat[10];
            df[0] = new DecimalFormat("0", dfs);
            df[1] = new DecimalFormat("0.0", dfs);
            df[2] = new DecimalFormat("0.00", dfs);
            df[3] = new DecimalFormat("0.000", dfs);
            df[4] = new DecimalFormat("0.0000", dfs);
            df[5] = new DecimalFormat("0.00000", dfs);
            df[6] = new DecimalFormat("0.000000", dfs);
            df[7] = new DecimalFormat("0.0000000", dfs);
            df[8] = new DecimalFormat("0.00000000", dfs);
            df[9] = new DecimalFormat("0.000000000", dfs);
        }
        if (decimalPlaces<0) {
            decimalPlaces = -decimalPlaces;
            if (decimalPlaces>9) decimalPlaces=9;
            if (sf==null) {
                sf = new DecimalFormat[10];
                sf[1] = new DecimalFormat("0.0E0",dfs);
                sf[2] = new DecimalFormat("0.00E0",dfs);
                sf[3] = new DecimalFormat("0.000E0",dfs);
                sf[4] = new DecimalFormat("0.0000E0",dfs);
                sf[5] = new DecimalFormat("0.00000E0",dfs);
                sf[6] = new DecimalFormat("0.000000E0",dfs);
                sf[7] = new DecimalFormat("0.0000000E0",dfs);
                sf[8] = new DecimalFormat("0.00000000E0",dfs);
                sf[9] = new DecimalFormat("0.000000000E0",dfs);
            }
            return sf[decimalPlaces].format(n); // use scientific notation
        }
        if (decimalPlaces<0) decimalPlaces = 0;
        if (decimalPlaces>9) decimalPlaces = 9;
        return df[decimalPlaces].format(n);
    }

    /** Converts a number to a rounded formatted string.
     * The 'significantDigits' argument specifies the minimum number
     * of significant digits, which is also the preferred number of
     * digits behind the decimal. Fewer decimals are shown if the
     * number would have more than 'maxDigits'.
     * Exponential notation is used if more than 'maxDigits' would be needed.
     */
    public static String d2s(double x, int significantDigits, int maxDigits) {
        double log10 = Math.log10(Math.abs(x));
        double roundErrorAtMax = 0.223*Math.pow(10, -maxDigits);
        int magnitude = (int)Math.ceil(log10+roundErrorAtMax);
        int decimals = x==0 ? 0 : maxDigits - magnitude;
        if (decimals<0 || magnitude<significantDigits+1-maxDigits)
            return IJ.d2s(x, -significantDigits); // exp notation for large and small numbers
        else {
            if (decimals>significantDigits)
                decimals = Math.max(significantDigits, decimals-maxDigits+significantDigits);
            return IJ.d2s(x, decimals);
        }
    }
}
