package com.scott;

import java.util.ArrayList;

public class AdvancedMath {
    public static Matrix identityMatrix(int dim) {
        if (dim < 1) {
            throw new IllegalArgumentException("The dimension of the matrix must be at least 1");
        }
        double[] identity = new double[dim*dim];
        for(int i = 0; i < dim; i++) {
            identity[i*dim+i] = 1;
        }
        return new Matrix(dim,dim,identity);
    }
    //Hermite interpolation
    public static double herp(double rangeStart, double rangeEnd, double amount) {
        double curve =  amount * amount * (3.0 - 2.0 * amount );
        return lerp(rangeStart,rangeEnd,curve);
    }
    public static double lerp(double rangeStart, double rangeEnd, double amount) {
        return rangeStart * (1 - amount) + rangeEnd * amount;
    }
    public static double clamp(double input, double minimumValue, double maximumValue) {
        return  Math.min(Math.max(input, minimumValue), maximumValue);
    }
    public static double fract(double input) {
        double sign = Math.signum(input);
        return sign*(Math.abs(input) - Math.floor(Math.abs(input)));
    }
    public static double dotProduct(ArrayList<Double> A, ArrayList<Double> B) {
        if(A.size() != B.size()) {
            throw new IllegalArgumentException("Inputs A and B must be the same size to compute dot product.");
        }
        double sum = 0;
        for(int i = 0; i < A.size(); i++) {
            sum+=A.get(i)*B.get(i);
        }
        return sum;
    }
    public static double dotProduct(double[]A, double[]B){
        if(A.length != B.length) {
            throw new IllegalArgumentException("Inputs A and B must be the same size to compute dot product.");
        }
        double sum = 0;
        for(int i = 0; i <A.length;i++){
            sum+=A[i]*B[i];
        }
        return sum;
    }
    public static double magnitude(ArrayList<Double> inputs) {
        double sum = 0;
        for(Double d:inputs) {
            sum+=d*d;
        }
        return Math.sqrt(sum);
    }
    public static double magnitude(double[] inputs) {
        double sum = 0;
        for(double d:inputs) {
            sum+=d*d;
        }
        return Math.sqrt(sum);
    }
    public static ArrayList<Double> scalarMultiply(double scale, ArrayList<Double> inputs) {
        ArrayList<Double>  scaled = new ArrayList<>();
        for(Double d : inputs) {
            scaled.add(scale*d);
        }
        return scaled;
    }
    public static double[] scalarMultiply(double scale, double[] inputs) {
        double [] scaled = new double[inputs.length];
        for(int i = 0; i < inputs.length; i++) {
            scaled[i] = scale*inputs[i];
        }
        return scaled;
    }
    public static Matrix scalarMultiply(double scale, Matrix mat) {
        double[] matArr = mat.getMatrixArray();
        double [] scaled = new double[matArr.length];
        for(int i = 0; i < matArr.length; i++) {
            scaled[i] = scale*matArr[i];
        }
        return new Matrix(mat.getRows(),mat.getColumns(),scaled);
    }
    public static ArrayList<Double> crossProduct(ArrayList<Double> A, ArrayList<Double> B) {
        if(A.size()!= 3 || B.size() != 3) {
            throw new IllegalArgumentException("Inputs A and B need to be of size 3 to compute cross product.");
        }
        ArrayList<Double>  cross = new ArrayList<>();
        double a1 = A.get(0), a2 = A.get(1), a3 = A.get(2);
        double b1 = B.get(0), b2 = B.get(1), b3 = B.get(2);
        cross.add((a2*b3)-(a3*b2));
        cross.add((a3*b1)-(a1*b3));
        cross.add((a1*b2)-(a2*b1));
        return cross;
    }
    public static ArrayList<Double> normalize(ArrayList<Double> inputs) {
        ArrayList<Double> normalized = new ArrayList<>();
        double mag = magnitude(inputs);
        for(Double d: inputs) {
            normalized.add(d/mag);
        }
        return normalized;
    }
    public static double[] normalize(double[] inputs) {
        double[] normalized = new double[inputs.length];
        double mag = magnitude(inputs);
        for(int i = 0; i < inputs.length; i++){
            normalized[i] = inputs[i]/mag;
        }
        return normalized;
    }
    public static Matrix matrixMultiply(Matrix A, Matrix B) {
        if(A.getColumns() !=  B.getRows()){
            throw new IllegalArgumentException("The number of columns in matrix A must match the number of " +
                    "rows in matrix B.");
        }
        Matrix result = new Matrix(A.getRows(),B.getColumns());
        for(int i = 0; i<result.getMatrixArray().length;i++){
            int row = i/result.getColumns();
            int col = i%result.getColumns();
            result.setCell(row,col ,AdvancedMath.dotProduct(A.getRow(row),B.getColumn(col)));
        }
        return result;
    }
}
