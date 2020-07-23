package com.scott;

import java.util.ArrayList;


public class AdvancedMath {
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

    //Hermite interpolation
    public static double herp(double rangeStart, double rangeEnd, double amount) {
        double curve = amount * amount * (3.0 - 2.0 * amount);
        return lerp(rangeStart, rangeEnd, curve);
    }

    public static double lerp(double rangeStart, double rangeEnd, double amount) {
        return rangeStart * (1 - amount) + rangeEnd * amount;
    }

    public static double clamp(double input, double minimumValue, double maximumValue) {
        return Math.min(Math.max(input, minimumValue), maximumValue);
    }

    public static double fract(double input) {
        double sign = Math.signum(input);
        return sign * (Math.abs(input) - Math.floor(Math.abs(input)));
    }

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

    public static double magnitude(ArrayList<Double> inputs) {
        return Math.sqrt(dotProduct(inputs,inputs));
    }

    public static double magnitude(double[] inputs) {
        return Math.sqrt(dotProduct(inputs,inputs));
    }

    public static ArrayList<Double> scalarMultiply(double scale, ArrayList<Double> inputs) {
        ArrayList<Double> scaled = new ArrayList<>();
        for (Double d : inputs) {
            scaled.add(scale * d);
        }
        return scaled;
    }

    public static double[] scalarMultiply(double scale, double[] inputs) {
        double[] scaled = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            scaled[i] = scale * inputs[i];
        }
        return scaled;
    }

    public static Matrix scalarMultiply(double scale, Matrix mat) {
        double[] matArr = mat.getMatrixArray();
        double[] scaled = new double[matArr.length];
        for (int i = 0; i < matArr.length; i++) {
            scaled[i] = scale * matArr[i];
        }
        return new Matrix(mat.getRows(), mat.getColumns(), scaled);
    }

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

    public static ArrayList<Double> normalize(ArrayList<Double> inputs) {
        ArrayList<Double> normalized = new ArrayList<>();
        double mag = magnitude(inputs);
        for (Double d : inputs) {
            normalized.add(d / mag);
        }
        return normalized;
    }

    public static double[] normalize(double[] inputs) {
        double[] normalized = new double[inputs.length];
        double mag = magnitude(inputs);
        for (int i = 0; i < inputs.length; i++) {
            normalized[i] = inputs[i] / mag;
        }
        return normalized;
    }

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

    public static Matrix subtract(Matrix A, Matrix B) {
        return add(A, scalarMultiply(-1, B));
    }

    public static double[] subtract(double[] A, double[] B) {
        return add(A, scalarMultiply(-1, B));
    }

    public static ArrayList<Double> subtract(ArrayList<Double> A, ArrayList<Double> B) {
        return add(A, scalarMultiply(-1, B));
    }
    //Proj B onto A
    public static double[] proj(double[] A,double[]B){
        double denom = dotProduct(A,A);
        if(denom == 0 ){
            throw new IllegalArgumentException("A dot A can't be 0 for projection.");
        }
        return scalarMultiply( dotProduct(A,B)/denom,A);
    }
    public static ArrayList<Double> proj(ArrayList<Double> A, ArrayList<Double> B){
        double denom = dotProduct(A,A);
        if(denom == 0 ){
            throw new IllegalArgumentException("A dot A can't be 0 for projection.");
        }
        return scalarMultiply( dotProduct(A,B)/dotProduct(A,A),A);
    }
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

    public static Matrix transform(Matrix m) {
        Matrix result = new Matrix(m.getColumns(), m.getRows());
        for (int i = 0; i < m.getMatrixArray().length; i++) {
            int row = i / m.getColumns();
            int col = i % m.getColumns();
            result.setCell(col, row, m.getCell(row, col));
        }
        return result;
    }

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
}
