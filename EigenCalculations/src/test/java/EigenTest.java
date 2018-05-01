import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import control.EigenCalculator;
import model.Matrix;

public class EigenTest {

	@Test
	public void testEigenValue() throws IOException {
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
	public void testEigenValue3() throws IOException {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 2, 2);
		double[][] a_data = new double[2][2];
		a_data[0][0] = 3;
		a_data[0][1] = 2;
		a_data[1][0] = 3;
		a_data[1][1] = 8;
		A.setCurrentMatrix(a_data);
		double lambda = 2;
		cal.isEigenValue(A, lambda);
	}

	@Test
	public void testEigenValue2() throws IOException {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 3, 3);
		double[][] a_data = new double[3][3];
		a_data[0][0] = 6;
		a_data[0][1] = -3;
		a_data[0][2] = 1;

		a_data[1][0] = 3;
		a_data[1][1] = 0;
		a_data[1][2] = 5;

		a_data[2][0] = 2;
		a_data[2][1] = 2;
		a_data[2][2] = 6;
		A.setCurrentMatrix(a_data);
		double lambda = 5;
		cal.isEigenValue(A, lambda);
	}

	@Test
	public void testEigenVector() throws IOException {
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

	@Test
	public void testEigenVector2() throws IOException {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 2, 2);
		double[][] a_data = new double[2][2];
		a_data[0][0] = -3;
		a_data[0][1] = 1;
		a_data[1][0] = -3;
		a_data[1][1] = 8;
		A.setCurrentMatrix(a_data);
		Matrix givenVector = new Matrix("givenVector", 2, 1);
		double[][] vectorData = new double[2][1];
		vectorData[0][0] = 1;
		vectorData[1][0] = 4;
		givenVector.setCurrentMatrix(vectorData);
		cal.isEigenVector(givenVector, A);
	}

	@Test
	public void testEigenVector3() throws IOException {
		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 3, 3);
		double[][] a_data = new double[3][3];
		a_data[0][0] = 3;
		a_data[0][1] = 7;
		a_data[0][2] = 9;

		a_data[1][0] = -4;
		a_data[1][1] = -5;
		a_data[1][2] = 1;

		a_data[2][0] = 2;
		a_data[2][1] = 4;
		a_data[2][2] = 4;

		A.setCurrentMatrix(a_data);
		Matrix givenVector = new Matrix("givenVector", 3, 1);
		double[][] vectorData = new double[3][1];
		vectorData[0][0] = 4;
		vectorData[1][0] = -3;
		vectorData[2][0] = 1;
		givenVector.setCurrentMatrix(vectorData);
		cal.isEigenVector(givenVector, A);
	}

}
