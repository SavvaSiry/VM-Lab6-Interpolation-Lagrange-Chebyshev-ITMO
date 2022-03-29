package labs.entityes;

import java.util.Vector;

public class PolynomUtil {
    public static Vector<Polynom> computeChebyshevSeries(Polynom t1, Polynom t0, int size) {
		Vector<Polynom> result = new Vector<Polynom>();
		result.add(t0);
		result.add(t1);
		Polynom tn = t1;
		Polynom tnMinus1 = t0;
		for (int i = 2; i <= size; i++) {
			Polynom next = tn.computeNextPolynom(tnMinus1);
			result.add(next);
			next.printPolynom();
			tnMinus1 = tn;
			tn = next;
		}
		return result;
    }
}
