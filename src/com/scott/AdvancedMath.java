package com.scott;

import java.util.ArrayList;

/**
 * Advanced Math is a library of methods to help with vector and matrix operations along with some other useful
 * mathematical functions like linear interpolation.
 */
public class AdvancedMath {
    /**
     * Returns a the identity matrix of size dim.
     * @param dim The size of the matrix.
     * @return Matrix with 1's down it's diagonal.
     */
    public static Matrix identityMatrix(int dim) {
        if (dim < 1) {
            throw new IllegalArgumentException("The dimension of the matrix must be at least 1");
        }
        double[] identity = new double[dim * dim];
        for (int i = 0; i < dim; i++) {
            identity[i * dim + i] = 1;
        }
        return new Matrix(dim, dim, identity);
    }

    /**
     * herp is short for Hermite interpolation.
     * Hermite interpolation uses a smooth curve to interpolate
     * values rather than a line like in linear interpolation.
     * @param rangeStart Start of range.
     * @param rangeEnd End of range.
     * @param amount Percent to interpolate between start and end range.
     * @return Interpolated value
     */
    public static double herp(double rangeStart, double rangeEnd, double amount) {
        double curve = amount * amount * (3.0 - 2.0 * amount);
        return lerp(rangeStart, rangeEnd, curve);
    }

    /**
     * lerp is short for linear interpolation.
     * Linear interpolation uses a line to get a value with a start and end range.
     * @param rangeStart Start of range.
     * @param rangeEnd End of range.
     * @param amount Percent to interpolate between start and end range.
     * @return Interpolated value
     */
    public static double lerp(double rangeStart, double rangeEnd, double amount) {
        return rangeStart * (1 - amount) + rangeEnd * amount;
    }

    /**
     * clamp takes a number and if it is greater or less than a specified range then it will return the upper or lower
     * bound of the range.
     * @param input Number to be bounded.
     * @param minimumValue Smallest value in range.
     * @param maximumValue Largest value in range.
     * @return clamped value
     */
    public static double clamp(double input, double minimumValue, double maximumValue) {
        return Math.min(Math.max(input, minimumValue), maximumValue);
    }

    /**
     * fract returns the fractional portion of a number.
     * 4.123 returns 0.123
     * -3.14 returns -0.14
     * @param input Value to get the fractional portion of.
     * @return Fractional portion of input.
     */
    public static double fract(double input) {
        double sign = Math.signum(input);
        return sign * (Math.abs(input) - Math.floor(Math.abs(input)));
    }

    /**
     * Calculates the dot product of 2 arraylists.
     * @param A Vector 1
     * @param B Vector 2
     * @return A dot B
     */
    public static double dotProduct(ArrayList<Double> A, ArrayList<Double> B) {
        if (A.size() != B.size()) {
            throw new IllegalArgumentException("Inputs A and B must be the same size to compute dot product.");
        }
        double sum = 0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i) * B.get(i);
        }
        return sum;
    }

    /**
     * Calculates the dot product of 2 arrays.
     * @param A Vector 1
     * @param B Vector 2
     * @return A dot B
     */
    public static double dotProduct(double[] A, double[] B) {
        if (A.length != B.length) {
            throw new IllegalArgumentException("Inputs A and B must be the same size to compute dot product.");
        }
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] * B[i];
        }
        return sum;
    }

    /**
     * Takes the magnitude of an arraylist.
     * calculated as sqrt(dot(input,input))
     * @param input vector
     * @return magnitude of the vector
     */
    public static double magnitude(ArrayList<Double> input) {
        return Math.sqrt(dotProduct(input,input));
    }

    /**
     * Takes the magnitude of an array.
     * calculated as sqrt(dot(input,input))
     * @param input vector
     * @return magnitude of the vector
     */
    public static double magnitude(double[] input) {
        return Math.sqrt(dotProduct(input,input));
    }

    /**
     * Multiplies scale by each element of the arraylist
     * @param scale scalar number
     * @param inputs vector
     * @return scaled vector
     */
    public static ArrayList<Double> scalarMultiply(double scale, ArrayList<Double> inputs) {
        ArrayList<Double> scaled = new ArrayList<>();
        for (Double d : inputs) {
            scaled.add(scale * d);
        }
        return scaled;
    }
    /**
     * Multiplies scale by each element of the array
     * @param scale scalar number
     * @param inputs vector
     * @return scaled vector
     */
    public static double[] scalarMultiply(double scale, double[] inputs) {
        double[] scaled = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            scaled[i] = scale * inputs[i];
        }
        return scaled;
    }

    /**
     * Multiplies scale by each element of the matrix
     * @param scale scalar number
     * @param mat matrix
     * @return scaled matrix
     */
    public static Matrix scalarMultiply(double scale, Matrix mat) {
        double[] matArr = mat.getMatrixArray();
        double[] scaled = new double[matArr.length];
        for (int i = 0; i < matArr.length; i++) {
            scaled[i] = scale * matArr[i];
        }
        return new Matrix(mat.getRows(), mat.getColumns(), scaled);
    }

    /**
     * Calculates the cross product of 2 arraylists.
     * @param A vector 1
     * @param B vector 2
     * @return a vector orthogonal to both A and B, or the zero vector.
     */
    public static ArrayList<Double> crossProduct(ArrayList<Double> A, ArrayList<Double> B) {
        if (A.size() != 3 || B.size() != 3) {
            throw new IllegalArgumentException("Inputs A and B need to be of size 3 to compute cross product.");
        }
        ArrayList<Double> cross = new ArrayList<>();
        double a1 = A.get(0), a2 = A.get(1), a3 = A.get(2);
        double b1 = B.get(0), b2 = B.get(1), b3 = B.get(2);
        cross.add((a2 * b3) - (a3 * b2));
        cross.add((a3 * b1) - (a1 * b3));
        cross.add((a1 * b2) - (a2 * b1));
        return cross;
    }

    /**
     * Calculates the cross product of 2 arrays.
     * @param A vector 1
     * @param B vector 2
     * @return a vector orthogonal to both A and B, or the zero vector.
     */
    public static double[] crossProduct(double[] A, double[] B) {
        if (A.length != 3 || B.length != 3) {
            throw new IllegalArgumentException("Inputs A and B need to be of size 3 to compute cross product.");
        }
        ArrayList<Double> cross = new ArrayList<>();
        double a1 = A[0], a2 = A[1], a3 = A[2];
        double b1 = B[0], b2 = B[1], b3 = B[2];
        return new double[]{((a2 * b3) - (a3 * b2)), ((a3 * b1) - (a1 * b3)), ((a1 * b2) - (a2 * b1))};
    }

    /**
     * Divides each element of the arraylist by it's magnitude
     * @param inputs vector
     * @return normalized vector
     */
    public static ArrayList<Double> normalize(ArrayList<Double> inputs) {
        ArrayList<Double> normalized = new ArrayList<>();
        double mag = magnitude(inputs);
        for (Double d : inputs) {
            normalized.add(d / mag);
        }
        return normalized;
    }
    /**
     * Divides each element of the array by it's magnitude
     * @param inputs vector
     * @return normalized vector
     */
    public static double[] normalize(double[] inputs) {
        double[] normalized = new double[inputs.length];
        double mag = magnitude(inputs);
        for (int i = 0; i < inputs.length; i++) {
            normalized[i] = inputs[i] / mag;
        }
        return normalized;
    }

    /**
     * Multiplies 2 matrices together.
     * @param A Matrix 1
     * @param B Matrix 2
     * @return A*B
     */
    public static Matrix matrixMultiply(Matrix A, Matrix B) {
        if (A.getColumns() != B.getRows()) {
            throw new IllegalArgumentException("The number of columns in matrix A must match the number of " +
                    "rows in matrix B.");
        }
        Matrix result = new Matrix(A.getRows(), B.getColumns());
        for (int i = 0; i < result.getMatrixArray().length; i++) {
            int row = i / result.getColumns();
            int col = i % result.getColumns();
            result.setCell(row, col, AdvancedMath.dotProduct(A.getRow(row), B.getColumn(col)));
        }
        return result;
    }
    /**
     * Multiplies a matrix and an array like a matrix and a vector.
     * @param A Matrix 1
     * @param B vector
     * @return A*B
     */
    public static double[] matrixMultiply(Matrix A, double[] B) {
        if (A.getColumns() != B.length) {
            throw new IllegalArgumentException("The number of columns in matrix A must match the number of " +
                    "elements in vector B.");
        }
        double[] result = new double[B.length];
        for (int i = 0; i < A.getRows(); i++) {
            result[i] = AdvancedMath.dotProduct(A.getRow(i), B);
        }
        return result;
    }
    /**
     * Multiplies a matrix and an arraylist like a matrix and a vector.
     * @param A Matrix 1
     * @param B vector
     * @return A*B
     */
    public static ArrayList<Double> matrixMultiply(Matrix A, ArrayList<Double> B) {
        if (A.getColumns() != B.size()) {
            throw new IllegalArgumentException("The number of columns in matrix A must match the number of " +
                    "elements in vector B.");
        }
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < A.getRows(); i++) {
            ArrayList<Double> tmp = new ArrayList<>();
            for (double d : A.getRow(i)) {
                tmp.add(d);
            }
            result.add(AdvancedMath.dotProduct(tmp, B));
        }
        return result;
    }

    /**
     * Adds 2 matrices together.
     * @param A Matrix 1
     * @param B Matrix 2
     * @return A+B
     */
    public static Matrix add(Matrix A, Matrix B) {
        if (A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) {
            throw new IllegalArgumentException("Matrices A and B must have the same dimensions to add.");
        }
        double[] result = new double[A.getMatrixArray().length];
        for (int i = 0; i < A.getMatrixArray().length; i++) {
            result[i] = A.getMatrixArray()[i] + B.getMatrixArray()[i];
        }
        return new Matrix(A.getRows(), A.getColumns(), result);
    }

    /**
     * Adds 2 arrays together like vectors
     * @param A Vector 1
     * @param B Vector 2
     * @return A+B
     */
    public static double[] add(double[] A, double[] B) {
        if (A.length != B.length) {
            throw new IllegalArgumentException("Vectors A and B must be the same size to add.");
        }
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] + B[i];
        }
        return result;
    }
    /**
     * Adds 2 arraylists together like vectors
     * @param A Vector 1
     * @param B Vector 2
     * @return A+B
     */
    public static ArrayList<Double> add(ArrayList<Double> A, ArrayList<Double> B) {
        if (A.size() != B.size()) {
            throw new IllegalArgumentException("Vectors A and B must be the same size to add.");
        }
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            result.add(A.get(i) + B.get(i));
        }
        return result;
    }

    /**
     * Subtracts 2 matrices.
     * @param A Matrix 1
     * @param B Matrix 2
     * @return A-B
     */
    public static Matrix subtract(Matrix A, Matrix B) {
        return add(A, scalarMultiply(-1, B));
    }

    /**
     * Subtracts 2 arrays like vectors
     * @param A Vector 1
     * @param B Vector 2
     * @return A-B
     */
    public static double[] subtract(double[] A, double[] B) {
        return add(A, scalarMultiply(-1, B));
    }
    /**
     * Subtracts 2 arraylists like vectors
     * @param A Vector 1
     * @param B Vector 2
     * @return A-B
     */
    public static ArrayList<Double> subtract(ArrayList<Double> A, ArrayList<Double> B) {
        return add(A, scalarMultiply(-1, B));
    }

    /**
     * The projection of B onto A;
     * @param A Vector 1
     * @param B Vector 2
     * @return B projected on A
     */
    public static double[] proj(double[] A,double[]B){
        double denom = dotProduct(A,A);
        if(denom == 0 ){
            throw new IllegalArgumentException("A dot A can't be 0 for projection.");
        }
        return scalarMultiply( dotProduct(A,B)/denom,A);
    }
    /**
     * The projection of B onto A;
     * @param A Vector 1
     * @param B Vector 2
     * @return B projected on A
     */
    public static ArrayList<Double> proj(ArrayList<Double> A, ArrayList<Double> B){
        double denom = dotProduct(A,A);
        if(denom == 0 ){
            throw new IllegalArgumentException("A dot A can't be 0 for projection.");
        }
        return scalarMultiply( dotProduct(A,B)/dotProduct(A,A),A);
    }

    /**
     * Gets the minor of a matrix at excluded row and column.
     * @param m Matrix
     * @param rowExclude Row to ignore
     * @param colExclude Column to ignore
     * @return The minor of m at row and col exclude.
     */
    public static Matrix getSubMatrix(Matrix m, int rowExclude, int colExclude) {
        m.getCell(rowExclude, colExclude);
        double[] result = new double[(m.getRows() - 1) * (m.getColumns() - 1)];
        int resIndx = 0;
        for (int i = 0; i < m.getMatrixArray().length; i++) {
            int row = i / m.getColumns();
            int col = i % m.getColumns();

            if (row != rowExclude && col != colExclude) {
                result[resIndx] = m.getCell(row, col);
                resIndx++;
            }
        }
        return new Matrix((m.getRows() - 1), (m.getColumns() - 1), result);
    }

    /**
     * Calculates the determinant of a Matrix.
     * @param m Matrix
     * @return det A
     */
    public static double det(Matrix m) {
        if (m.getRows() != m.getColumns()) {
            throw new IllegalArgumentException("Matrix must have same number of " +
                    "rows and columns to calculate determinant.");
        }
        if (m.getRows() == 1) {
            return m.getMatrixArray()[0];
        }
        if (m.getRows() == 2) {
            return ((m.getCell(0, 0) * m.getCell(1, 1))
                    - (m.getCell(0, 1) * m.getCell(1, 0)));
        }
        double sum = 0;
        double flip = -1;
        for (int i = 0; i < m.getColumns(); i++) {
            flip *= -1;
            double a = m.getCell(0, i);
            sum += flip * a * det(getSubMatrix(m, 0, i));
        }
        return sum;
    }

    /**
     * Swaps the rows and columns of a matrix.
     * @param m Matrix
     * @return Matrix with swapped rows and columns.
     */
    public static Matrix transform(Matrix m) {
        Matrix result = new Matrix(m.getColumns(), m.getRows());
        for (int i = 0; i < m.getMatrixArray().length; i++) {
            int row = i / m.getColumns();
            int col = i % m.getColumns();
            result.setCell(col, row, m.getCell(row, col));
        }
        return result;
    }

    /**
     * Calculates the adjoint, of a matrix.
     * @param m Matrix
     * @return Transpose of m's cofactor matrix
     */
    public static Matrix adj(Matrix m) {
        if (m.getRows() != m.getColumns()) {
            throw new IllegalArgumentException("Matrix must have same number of " +
                    "rows and columns to calculate adjoint.");
        }
        Matrix result = new Matrix(m.getRows(), m.getColumns());

        for (int i = 0; i < m.getMatrixArray().length; i++) {

            int row = i / m.getColumns();
            int col = i % m.getColumns();
            result.setCell(row, col, Math.pow(-1, row + col) * det(getSubMatrix(m, row, col)));
        }
        return transform(result);
    }

    /**
     * Calculates the inverse of a matrix m such that m*inv(m) = identity matrix of size m.
     * @param m matrix
     * @return Inverse matrix calculated as (1/det(m)) * adj(m)
     */
    public static Matrix inv(Matrix m) {
        if (m.getRows() != m.getColumns()) {
            throw new IllegalArgumentException("Matrix must have same number of " +
                    "rows and columns to calculate inverse.");
        }
        double det = det(m);
        if (det == 0) {
            throw new IllegalArgumentException("A matrix with a zero determinant has no inverse.");
        }
        return scalarMultiply(1.0 / det, adj(m));
    }

    /**
     * Reflects incident vector A over B.
     * @param A Vector 1
     * @param B Vector
     * @return A reflected over B
     */
    public static double[] reflect(double[] A, double[] B){
        return add(A,scalarMultiply(-2*dotProduct(B,A),B));
    }
    /**
     * Reflects incident vector A over B.
     * @param A Vector 1
     * @param B Vector
     * @return A reflected over B
     */
    public static ArrayList<Double> reflect( ArrayList<Double>A,  ArrayList<Double>B){
        return add(A,scalarMultiply(-2*dotProduct(B,A),B));
    }
}
