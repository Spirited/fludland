package org.fludland.utils.math;

public class Matrix<T extends Number> {
    private final int rows;
    private final int columns;
    private final Object[][] data;

    private static final int DEFAULT_ROWS_SIZE = 5;
    private static final int DEFAULT_COLUMN_SIZE = 5;

    public Matrix() {
        this.rows = DEFAULT_ROWS_SIZE;
        this.columns = DEFAULT_COLUMN_SIZE;

        data = new Object[DEFAULT_ROWS_SIZE][DEFAULT_COLUMN_SIZE];
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        data = new Object[rows][columns];
    }

    public void put(int row, int column, T cellData) {
        data[row][column] = cellData;
    }

    public T get(int row, int column) {
        return (T)data[row][column];
    }

}
