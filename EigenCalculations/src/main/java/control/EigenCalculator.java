package control;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import model.Matrix;

public class EigenCalculator {
	MatrixCalculator matrixCal = new MatrixCalculator();
	VectorCalculator vcal = new VectorCalculator();

	public static void main(String[] args) {
		// EigenCalculator cal = new EigenCalculator();
		// Matrix givenMatrix = new Matrix("Given Matrix", 2, 2);
		// double[][] given_data = new double[2][2];
		//
		// given_data[0][0] = 1;
		// given_data[0][1] = 6;
		// given_data[1][0] = 5;
		// given_data[1][1] = 2;
		// givenMatrix.setCurrentMatrix(given_data);
		//
		// givenMatrix.printMatrix();
		// System.out.println(" ");
		// String[][] lambda_data = cal.lambdaIdentity_UnknowLamda(2);
		// cal.subtraction_UnknownMatrices(givenMatrix, lambda_data);

		EigenCalculator cal = new EigenCalculator();
		Matrix A = new Matrix("A", 2, 2);
		double[][] a_data = new double[2][2];
		a_data[0][0] = 3;
		a_data[0][1] = 2;
		a_data[1][0] = 3;
		a_data[1][1] = 8;
		A.setCurrentMatrix(a_data);
		double lambda = 2;
		try {
			cal.isEigenValue(A, lambda);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[][] lambdaIdentity_UnknowLamda(int identitySize) {
		int i, j;
		String a[][] = new String[identitySize][identitySize];
		for (i = 0; i < identitySize; i++) {
			for (j = 0; j < identitySize; j++) {
				if (i == j) {
					a[i][j] = "λ";
					System.out.print(" " + a[i][j]);
				} else {
					a[i][j] = "0";
					System.out.print(" " + a[i][j]);
				}
			}
			System.out.print("\n");
		}
		return a;
	}

	public String[][] subtraction_UnknownMatrices(Matrix givenMatrix, String[][] lambdaIdentity) {
		String[][] newData = new String[givenMatrix.getRow()][givenMatrix.getColumn()];
		String[][] showWork = new String[givenMatrix.getRow()][givenMatrix.getColumn()];
		String[][] answer = new String[givenMatrix.getRow()][givenMatrix.getColumn()];

		Matrix subMatrix = new Matrix("" + givenMatrix.getName() + "-" + "Lamnbda Identity Matrix",
				givenMatrix.getRow(), givenMatrix.getColumn());
		for (int row = 0; row < givenMatrix.getCurrentMatrix().length; row++) {
			for (int col = 0; col < givenMatrix.getCurrentMatrix()[row].length; col++) {
				System.out.println(givenMatrix.getCurrentMatrix()[row][col]);
				System.out.println(lambdaIdentity[row][col]);
				showWork[row][col] = "(" + givenMatrix.getCurrentMatrix()[row][col] + ")" + " - " + "("
						+ lambdaIdentity[row][col] + ")";
				String sub = "" + givenMatrix.getCurrentMatrix()[row][col] + "-" + lambdaIdentity[row][col];
				answer[row][col] = "(" + sub + ")";
				newData[row][col] = sub;

			}
		}
		// subMatrix.setCurrentMatrix(newData);
		System.out.println("\nPrint Subtraction \n");
		for (int i = 0; i < newData.length; i++) {
			for (int j = 0; j < newData[i].length; j++) {
				System.out.print(" " + newData[i][j] + " ");
			}
			System.out.println(" ");
		}
		return null;
	}

	public void findEigenValues(Matrix givenMatrix) throws IOException {
		boolean folder = new File(System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork").mkdirs();
		PrintWriter writer = new PrintWriter(
				System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork" + "\\FindEigenValues.txt");
	}

	public void appendToFile(Writer out, double[][] data) throws IOException {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				out.append(" " + data[i][j] + " ");
			}
			out.append("\r\n");
		}

		out.append("\r\n");
	}

	public void isEigenValue(Matrix A, double lambda) throws IOException {
		boolean folder = new File(System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork").mkdirs();

		File fileDir = new File(
				System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork" + "\\IsEigenValue.txt");

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));

		out.append("Step 1: Use forumla (A-λI)v = 0").append("\r\n");
		out.append("Step 2: Use result from formula in matrix row operation with zeros(right of augment matrix)")
				.append("\r\n");
		out.append("Does the row operation result to zeros across?").append("\r\n");
		out.append("       If yes: " + lambda + " is valid for this matrix.").append("\r\n");

		out.append("-----------------------------------------------------------------------------------")
				.append("\r\n");

		out.append("------------------------------Is " + lambda + " a eigen value for Matrix A? ------------------")
				.append("\r\n");

		Matrix B = new Matrix("λI Matrix:", A.getRow(), A.getColumn());
		double[][] lambdaIdentity = lambaIdentity(A.getRow(), lambda);
		B.setCurrentMatrix(lambdaIdentity);
		out.append("(A-λI)V = 0: ").append("\r\n").append("\r\n");
		System.out.println("λI Matrix: ");
		out.append("λI Matrix: ").append("\r\n");
		out.append("\r\n");
		appendToFile(out, lambdaIdentity);

		Matrix subtractedMatrix = matrixCal.subtractMatrices(A, B);
		System.out.println("Subtracted Matrix");
		out.append("Subtracted Matrix").append("\r\n");

		appendToFile(out, subtractedMatrix.getCurrentMatrix());

		Matrix rowOperationMatrix = new Matrix("Operation", subtractedMatrix.getRow(),
				subtractedMatrix.getColumn() + 1);
		double[][] operationData = new double[subtractedMatrix.getRow()][subtractedMatrix.getColumn() + 1];

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
		System.out.println("Row Operation Matrix");
		out.append("Row Operation Matrix: ").append("\r\n");
		rowOperationMatrix.printMatrix();
		appendToFile(out, rowOperationMatrix.getCurrentMatrix());

		out.append("//////////////////////////////MATRIX ROW OPERATION/////////////////////////////////")
				.append("\r\n");

		double[][] rrefResult = matrixCal.rref(rowOperationMatrix.getCurrentMatrix());

		grabsContentFromFile("ShowWork.txt", "IsEigenValue.txt");

		appendToFile(out, rrefResult);
		out.append("///////////////////////////////////////////////////////////////////////////////////")
				.append("\r\n");

		out.append("Eigen Value is Valid: " + validEigenValue(rowOperationMatrix)).append("\r\n");
		out.append("-----------------------------------------------------------------------------------")
				.append("\r\n");

		out.flush();
		out.close();
	}

	public boolean isEigenVector(Matrix givenVector, Matrix A) throws FileNotFoundException {
		boolean folder = new File(System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork").mkdirs();
		PrintWriter writer = new PrintWriter(
				System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork" + "\\IsEigenVector.txt");
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

	public void grabsContentFromFile(String file1, String file2) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork" + "\\" + file1);
			fw = new FileWriter(System.getProperty("user.home") + "/Desktop" + "\\MatrixShowWork" + "\\" + file2);
			int c = fr.read();
			while (c != -1) {
				fw.write(c);
				c = fr.read();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fr);
			close(fw);
		}
	}

	public static void close(Closeable stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (IOException e) {
			// ...
		}
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
