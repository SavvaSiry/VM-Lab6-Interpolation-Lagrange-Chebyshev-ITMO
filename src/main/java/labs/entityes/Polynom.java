package labs.entityes;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Polynom {

	private Vector<Integer> coefficients;

	public Polynom() {
		coefficients = new Vector<Integer>();
	}
	
	public Polynom(Vector<Integer> coefficients) {
		this.coefficients = coefficients;
	}

	public Vector<Integer> getCoefficients() {
		return coefficients;
	}
	
	public Polynom multiplyByCoefficient(int coefficient) {
		Vector<Integer> multipliedVector = new Vector<Integer>();
		for (int i = 0; i < coefficients.size(); i++) {
			multipliedVector.add(coefficients.elementAt(i) * coefficient);
		}
		Polynom result = new Polynom(multipliedVector);
		return result;
	}

	public Polynom multiplyByX(int coefficientOfX) {
		Vector<Integer> multipliedVector = new Vector<Integer>();
		multipliedVector.add(0);
		for (int i = 0; i < coefficients.size(); i++) {
			multipliedVector.add(coefficients.elementAt(i) * coefficientOfX);
		}
		Polynom result = new Polynom(multipliedVector);
		return result;
	}
	
	public Polynom multiplyByPolynom(Polynom p1) {
		Polynom result = new Polynom();
		for (int i = 0; i <= (coefficients.size() - 1) + (p1.getCoefficients().size() - 1); i++) {
			result.getCoefficients().add(0);
		}
		for (int i = 0; i < coefficients.size(); i++) {
			for (int j = 0; j < p1.getCoefficients().size(); j++) {
				int sum = result.getCoefficients().elementAt(i + j);
				sum  = sum + (coefficients.elementAt(i) * p1.getCoefficients().elementAt(j));
				result.getCoefficients().set(i + j, sum);
			}
		}
		return result;
	}

	public Polynom computeNextPolynom(Polynom p1) {
		Polynom multipliedPolynom = multiplyByX(2);
		Polynom result = multipliedPolynom.minus(p1);
		return result;
	}

	public Polynom add(Polynom t0) {
		Vector<Integer> sumVector = new Vector<Integer>();
		for (int i = 0; i < t0.getCoefficients().size(); i++) {
			sumVector.add(coefficients.elementAt(i) + t0.getCoefficients().elementAt(i));
		}
		sumVector.add(coefficients.elementAt(coefficients.size() - 1));
		Polynom result = new Polynom(sumVector);
		return result;
	}

	public Polynom minus(Polynom p2) {
		Vector<Integer> minusVector = new Vector<Integer>();
		for (int i = 0; i < p2.getCoefficients().size(); i++) {
			minusVector.add(coefficients.elementAt(i) - p2.getCoefficients().elementAt(i));
		}
		if (coefficients.size() >= 2)
			minusVector.add(coefficients.elementAt(coefficients.size() - 2));
		minusVector.add(coefficients.elementAt(coefficients.size() - 1));
		Polynom result = new Polynom(minusVector);
		return result;
	}

	public void printPolynom() {
		System.out.print(coefficients.elementAt(coefficients.size() - 1) + "x^" + (coefficients.size() - 1));
		for (int i = coefficients.size() - 2; i >= 2; i--) {
			if (coefficients.elementAt(i) != 0) {
				if (coefficients.elementAt(i) > 0)
					System.out.print("+" + coefficients.elementAt(i) + "x^" + i);
				else
					System.out.print(coefficients.elementAt(i) + "x^" + i);
			}
		}
		if ((coefficients.size() >= 2) && (coefficients.elementAt(1) != 0)) {
			if (coefficients.elementAt(1) > 0)
				System.out.print("+" + coefficients.elementAt(1) + "x");
			else
				System.out.print(coefficients.elementAt(1) + "x");
		}
		if ((coefficients.size() >= 1) && (coefficients.elementAt(0) != 0)) {
			if (coefficients.elementAt(0) > 0)
				System.out.print("+" + coefficients.elementAt(0));
			else
				System.out.print(coefficients.elementAt(0));
		}
		System.out.println("");
	}

	public Polynom initializet0() {
		Vector<Integer> v0 = new Vector<Integer>();
		v0.add(1);
		Polynom t0 = new Polynom(v0);
		return t0;
	}

	public Polynom initializet1() {
		Vector<Integer> v1 = new Vector<Integer>();
		v1.add(0);
		v1.add(1);
		Polynom t1 = new Polynom(v1);
		return t1;
	}

	public Polynom initializeu1() {
		Vector<Integer> v1 = new Vector<Integer>();
		v1.add(0);
		v1.add(2);
		Polynom u1 = new Polynom(v1);
		return u1;
	}

	public double computeValue(double value) {
		double result = 0;
		for (int i = 0; i < coefficients.size(); i++) {
			result += (coefficients.elementAt(i) * Math.pow(value, i)); 
		}
		return result;
	}

	public Polynom derivate() {
		Vector<Integer> derivVector = new Vector<Integer>();
		for (int i = 1; i < coefficients.size(); i++) {
			derivVector.add(i * coefficients.elementAt(i));
		}
		Polynom result = new Polynom(derivVector);
		return result;
	}
	
	public List<Double> calculateRootsFirstKind(double a, double b) {
		List<Double> result = new ArrayList<>();
		for (int i = 1; i < coefficients.size(); i++) {
			double numerator = (2 * i) - 1;
			double denominator = 2 * (coefficients.size() - 1);
			double xi = Math.cos((numerator / denominator) * Math.PI);
			xi = (b + a) / 2 + (b - a)/2 * xi;
			result.add(xi);
		}
		return result;
	}
	
	public void calculateRootsSecondKind() {
		for (int i = 1; i < coefficients.size(); i++) {
			double numerator = i;
			double denominator = coefficients.size() + 1;
			double xi = Math.cos((numerator / denominator) * Math.PI);
			System.out.println("Root x" + i + " is " + xi);
		}
		System.out.println("");
	}
	
	public void calculateExtremasFirstKind() {
		for (int i = 0; i < coefficients.size(); i++) {
			double numerator = i;
			double denominator = coefficients.size() - 1;
			double xi = Math.cos((numerator / denominator) * Math.PI);
			System.out.println("Extrema x" + i + " is " + xi);
		}
		System.out.println("");
	}
	
}
