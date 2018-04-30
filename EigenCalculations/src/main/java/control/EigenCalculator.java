package control;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Inflater;

import model.Matrix;
import model.Vector;
import model.VectorOrientation;

public class EigenCalculator {
	MatrixCalculator matrixCal = new MatrixCalculator();
	VectorCalculator vcal = new VectorCalculator();

	public static void main(String[] args) {
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

	public void isEigenValue(Matrix A, double lambda) throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println(
				"------------------------------Is " + lambda + " a eigen value for Matrix A? ------------------");
		Matrix B = new Matrix("B", A.getRow(), A.getColumn());
		double[][] lambdaIdentity = lambaIdentity(2, lambda);
		B.setCurrentMatrix(lambdaIdentity);

		Matrix subtractedMatrix = matrixCal.subtractMatrices(A, B);
		subtractedMatrix.printMatrix();

		Matrix rowOperationMatrix = new Matrix("Operation", subtractedMatrix.getRow(),
				subtractedMatrix.getColumn() + 1);
		double[][] operationData = new double[subtractedMatrix.getRow()][subtractedMatrix.getColumn() + 1];
		System.out.println("\n(A-LambdaIdentiy)V = 0: ");
		for (int i = 0; i < operationData.length; i++) {
			for (int j = 0; j < operationData[i].length; j++) {
				try {
					operationData[i][j] = subtractedMatrix.getCurrentMatrix()[i][j];
				} catch (Exception e) {
					operationData[i][j] = 0;
				}
			}
		}

		rowOperationMatrix.setCurrentMatrix(operationData);
		rowOperationMatrix.printMatrix();

		System.out.println("Eigen Value is Valid: " + validEigenValue(rowOperationMatrix));
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
	}

	public boolean isEigenVector(Matrix givenVector, Matrix A) {
		Matrix multiplicationMatrix = matrixCal.multipyMatrices(A, givenVector);
		A.printMatrix();
		givenVector.printMatrix();
		multiplicationMatrix.printMatrix();

		System.out.println("--------");
		double scalarValue = multiplicationMatrix.getCurrentMatrix()[0][0] / givenVector.getCurrentMatrix()[0][0];
		for (int i = 0; i < multiplicationMatrix.getCurrentMatrix().length; i++) {
			for (int j = 0; j < multiplicationMatrix.getCurrentMatrix()[0].length; j++) {
				double division = multiplicationMatrix.getCurrentMatrix()[i][j] / givenVector.getCurrentMatrix()[i][j];
				if (division != scalarValue) {
					System.out.println("Given vector is not an eigen vector of the Matrix A.");
					return false;
				}
			}
		}
		System.out.println("Given vector is an eigen vector of the Matrix A.");
		return true;
	}

	public boolean validEigenValue(Matrix rowOperationMatrix)
			throws FileNotFoundException, UnsupportedEncodingException {
		boolean valid = true;
		double[][] rref = matrixCal.rref(rowOperationMatrix.getCurrentMatrix());
		System.out.println("RREF: Last Row");
		for (int i = rref.length - 1; i > 0; i--) {
			for (int j = 0; j < rref[i].length; j++) {
				System.out.print(" " + rref[i][j] + " ");
				if (rref[i][j] != 0.0) {
					return false;
				}
			}
			System.out.println(" ");
		}
		return valid;
	}

	public double[][] lambaIdentity(int identitySize, double lambaValue) {
		int i, j;
		double a[][] = new double[identitySize][identitySize];
		for (i = 0; i < identitySize; i++) {
			for (j = 0; j < identitySize; j++) {
				if (i == j) {
					a[i][j] = lambaValue;
					System.out.print(" " + a[i][j]);
				} else {
					a[i][j] = 0;
					System.out.print(" " + a[i][j]);
				}
			}
			System.out.print("\n");
		}
		return a;
	}
}
