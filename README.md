Curve Fitter
============

A fork of [ImageJ CurveFitter](https://github.com/imagej/imagej1) without any `java.awt` or GUI dependencies.

Import this library into an Android (i.e. Java) project to use various regression algorithms, such as:

- **Straight Line:** y = a + bx
- **2nd Degree Polynomial:** y = a + bx + cx^2
- **3rd Degree Polynomial:** y = a + bx + cx^2 + dx^3
- **4th Degree Polynomial:** y = a + bx + cx^2 + dx^3 + ex^4
- **Exponential:** y = a * exp(bx)
- **Power:** y = a * x^b
- **Log:** y = a * ln(bx)
- **Rodbard:** y = d + (a - d)/(1 + (x/c)^b)
- **Gamma Variate:** y = b * (x - a)^c * exp(-(x - a)/d)
- **Log with offset:** y = a + b * ln(x - c)
- **Rodbard (NIH Image):** x = d + (a - d)/(1 + (y/c)^b), or y = c * ((x - a)/(d - x))^(1/b)]
- **Exponential with Offset:** y = a * exp(-bx) + c
- **Gaussian:** y = a + (b - a) * exp(-(x - c)*(x - c)/(2 * d * d))
- **Exponential Recovery:** y = a * (1 - exp(-b * x)) + c
- **Inverse Rodbard:** y = c * ((x - a)/(d - x))^(1/b)
- **Exponential (linear regression):** y = a * exp(bx)
- **Power (linear regression):** y = a * x^b
- **5th Degree Polynomial:** y = a + bx + cx^2 + dx^3 + ex^4 + fx^5
- **6th Degree Polynomial:** y = a + bx + cx^2 + dx^3 + ex^4 + fx^5 + gx^6
- **7th Degree Polynomial:** y = a + bx + cx^2 + dx^3 + ex^4 + fx^5 + gx^6 + hx^7
- **8th Degree Polynomial:** y = a + bx + cx^2 + dx^3 + ex^4 + fx^5 + gx^6 + hx^7 + ix^8
- **Gaussian (no offset):** y = a * exp(-(x - b)*(x - b)/(2 * c * c)))
- **Exponential Recovery (no offset):** y = a * (1 - exp(-b * x))
- **Chapman-Richards:** y = a * (1 - exp(-b * x))^c


Usage
=====

```java
double[] xs = {1, 2, 3, 4, 5, 6};
double[] ys = {-2, 0, 6, 16, 30, 48};
CurveFitter curveFitter = new CurveFitter(xs, ys);
curveFitter.doFit(CurveFitter.POLY2);
double[] params = curveFitter.getParams();

double a = params[0];
double b = params[1];
double c = params[2];
// y = a + bx + cx^2
```