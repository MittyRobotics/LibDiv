# lin-alg-calc-lib
A library to perform linear algebra and calculus operations, plus some other math stuff!
Check out https://jitpack.io/#DivAgarwal1/lin-alg-lib to use this libary in your own projects

# Docs
## Calculus and Algebra
### Create polynomial
```
//can use a double array or an int array
double[] coefficients = new double[] {1, 2, 7};

//don't use the Polynomial contructer - use library create method
Polynomial myPolynomial = LinAlgLib.createPolynomial(coefficients);
```
### Create trig
```
double amplitude = 2;
double frequency = 3;

Polynomial mySineFunction = LinAlgLib.sine(amplitude, frequency);

//for default values 1 and 1
Polynomial myOtherSineFunction = LinAlgLib.sine();

//if you know taylor polynomials - decide number of terms (in this case 100)
Polynomial myThirdSineFunction = LinAlgLib.sine(100);
Polynomial myLastSineFunction = LinAlgLib.sine(amplitude, frequency, 100);
```
Cosine is the same, just replace `sine` calls with `cosine`

Also, `myPolynomial.print()` can be used to print the polynomial to the console

### Polynomial, sine, and cosine solving
```
//can be any polynomial, sine, or cosine
Polynomial function = LinAlgLib.createPolynomial(new double[] {
        1, 4, 5, 8
});

//guess the root of the function or put in something random
double guess = 2;

// maxRepetitions - how many times you want to run a Newton's method iteration (even 1 will usually suffice)
int maxRepetitions = 1;

double oneRootOfFunction = function.solve(guess, maxRepetitions);
```

### Polynomial, sine, and cosine derivatives/integrals
```
//can be any polynomial, sine, or cosine
Polynomial function = LinAlgLib.createPolynomial(new double[] {
        1, 4, 5, 8
});

//quite simple method call to get a new Polynomial with the derivative
Polynomial derivative = LinAlgLib.takeDerivative(function);

//integrating works similarly but allows you to choose the constant "+c" in one of three ways
Polynomial integralWithConstantAsZero = LinAlgLib.takeIntegral(function);
Polynomial integralWhereYouInputConstant = LinAlgLib.takeIntegral(function, 2);
Polynomial integralWhereYouInputAnXValueAndWhatTheIntegralShouldEqual = LinAlgLib.takeIntegral(function, 3, 7);
```

### Create non-polynomial function
```
//override compute to return what the function is
//this one is x * lnx + e^(2x)
Function function = new Function() {
    @Override
    public double compute(double x) {
        return x * Math.log(x) + Math.exp(2 * x);
    }
};

//if you know the derivative or integral you can input it, but this is not necessary
Function derivative = new Function() {
    @Override
    public double compute(double x) {
        return Math.log(x) + 1 + 2 * Math.exp(2 * x);
    }
};
function.manuallySetDerivative(derivative);

//same for integral but replace manuallySetDerivative with manuallySetIntegral
```

### Solve non-polynomial functions
```
//override compute to return what the function is
//this one is x * lnx + e^(2x)
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
```

### Compute derivatives and integrals of non-polynomial functions
```
//override compute to return what the function is
//this one is x * lnx + e^(2x)
Function function = new Function() {
    @Override
    public double compute(double x) {
        return x * Math.log(x) + Math.exp(2 * x);
    }
};

function.calculateDerivativeAtPoint(0.2);

function.definiteIntegral(2, 4);
        
//only use this if you know Gaussian Quadrature
function.definiteIntegralCustomGaussianQuadrature(2, 4, 17);

//note: these are very good estimations and manually inputting derivatives and integrals might be more accurate
```

## Linear Algebra
Will write soon