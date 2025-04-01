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
        if (row >= rows || column >= columns) {
            throw new MatrixIndexOutBoundException(row, column);
        }

        data[row][column] = cellData;
    }

    public T get(int row, int column) {
        if (row >= rows || column >= columns) {
            throw new MatrixIndexOutBoundException(row, column);
        }

        return (T) data[row][column];
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            builder.append("[ ");
            for (int j = 0; j < columns; j++) {
                builder.append(data[i][j]);
                builder.append(" ");
            }
            builder.append("]\n");
        }

        return builder.toString();
    }
}
