package com.scott;

/**
 * Matrix is meant to be used with the AdvancedMath methods when Matrix operations need to be preformed.
 * Matrices are created by taking a 1D array and indexing it like a matrix with rows and columns. Using arrays like this
 * makes it cleaner to loop through values for operations like addition and scalar multiplication as we don't have to
 * write anything nested. Note that matrices start their indices at 0,0.
 */
public class Matrix {
    private int rows, columns;
    private double[] matrixArray;

    /**
     * Constructor for an empty matrix.
     * @param rows number of rows
     * @param columns number of columns
     */
    public Matrix(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException("The minimum dimension for rows and columns is 1.");
        }
        this.rows = rows;
        this.columns = columns;
        matrixArray = new double[rows * columns];
    }

    /**
     * Constructor for a matrix initialized with an array.
     * For easy visualization it is recommended to place array elements on a new line when you get to the width of the
     * matrix.
     * {1,2,3,
     *  4,5,6,
     *  7,8,9}
     * @param rows number of rows
     * @param columns number of columns
     * @param matrixArray array of doubles to initialize the matrix. Note that the array must be of size rows*columns.
     */
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

    /**
     * Returns the value at specified row and column.
     * @param row row number
     * @param col column number
     * @return value at the row and column.
     */
    public double getCell(int row, int col) {
        if (row < 0 || row > rows - 1 || col < 0 || col > columns - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        return matrixArray[row * columns + col];
    }

    /**
     * Sets the value at the specified row and column.
     * @param row row number
     * @param col column number
     * @param value value to set at the row and column.
     */
    public void setCell(int row, int col, double value) {
        if (row < 0 || row > rows - 1 || col < 0 || col > columns - 1) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        matrixArray[row * columns + col] = value;
    }

    /**
     * Returns the specified row as a vector.
     * @param rowNum row number to get.
     * @return row vector
     */
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

    /**
     * Returns the specified column as a vector.
     * @param colNum column number to get.
     * @return column vector
     */
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

    /**
     *
     * @return Number of rows in the matrix.
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return Number of columns in the matrix.
     */
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return Returns the matrix as a 1D array of doubles.
     */
    public double[] getMatrixArray() {
        return matrixArray;
    }
}
