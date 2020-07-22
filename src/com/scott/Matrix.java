package com.scott;

public class Matrix {
    private int rows, columns;
    private double[] matrixArray;

    public Matrix(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException("The minimum dimension for rows and columns is 1.");
        }
        this.rows = rows;
        this.columns = columns;
        matrixArray = new double[rows * columns];
    }

    public Matrix(int rows, int columns, double[] matrixArray) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException("The minimum dimension for rows and columns is 1.");
        }
        if (matrixArray.length != rows * columns) {
            throw new IllegalArgumentException("Dimensions do not match provided matrix size.");
        }
        this.rows = rows;
        this.columns = columns;
        this.matrixArray = matrixArray;
    }

    public double getCell(int row, int col) {
        if (row < 0 || row > rows - 1 || col < 0 || col > columns - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        return matrixArray[row * columns + col];
    }

    public void setCell(int row, int col, double value) {
        if (row < 0 || row > rows - 1 || col < 0 || col > columns - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        matrixArray[row * columns + col] = value;
    }

    public double[] getRow(int rowNum) {
        if (rowNum < 0 || rowNum > rows - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        double[] result = new double[columns];
        for (int i = 0; i < columns; i++) {
            result[i] = getCell(rowNum, i);
        }
        return result;
    }

    public double[] getColumn(int colNum) {
        if (colNum < 0 || colNum > columns - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++) {
            result[i] = getCell(i, colNum);
        }
        return result;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[] getMatrixArray() {
        return matrixArray;
    }
}
