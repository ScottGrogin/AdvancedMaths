package com.scott;

import java.util.ArrayList;

public class AdvancedMath {
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
    public static double dotProduct(ArrayList<Double> A, ArrayList<Double> B) throws IllegalArgumentException {
        if(A.size() != B.size()) {
            throw new IllegalArgumentException("Inputs A and B must be the same size to compute dot product.");
        }
        double sum = 0;
        for(int i = 0; i < A.size(); i++) {
            sum+=A.get(i)*B.get(i);
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
    public static ArrayList<Double> scalarMultiply(double scale, ArrayList<Double> inputs) {
        ArrayList<Double>  scaled = new ArrayList<>();
        for(Double d : inputs) {
            scaled.add(scale*d);
        }
        return scaled;
    }
    public static ArrayList<Double> crossProduct(ArrayList<Double> A, ArrayList<Double> B)
            throws IllegalArgumentException {
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
}
