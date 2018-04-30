import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import control.EigenCalculator;
import model.Matrix;

public class EigenTest {

	@Test
	public void testEigenValue() throws FileNotFoundException, UnsupportedEncodingException {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 2, 2);
		double[][] a_data = new double[2][2];
		a_data[0][0] = 1;
		a_data[0][1] = 6;
		a_data[1][0] = 5;
		a_data[1][1] = 2;
		A.setCurrentMatrix(a_data);
		double lambda = 7;
		cal.isEigenValue(A, lambda);
	}

	@Test
	public void testEigenVector() {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 2, 2);
		double[][] a_data = new double[2][2];
		a_data[0][0] = 1;
		a_data[0][1] = 6;
		a_data[1][0] = 5;
		a_data[1][1] = 2;
		A.setCurrentMatrix(a_data);
		Matrix givenVector = new Matrix("givenVector", 2, 1);
		double[][] vectorData = new double[2][1];
		vectorData[0][0] = -6;
		vectorData[1][0] = 5;
		givenVector.setCurrentMatrix(vectorData);
		cal.isEigenVector(givenVector, A);
	}

}
