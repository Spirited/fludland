package org.fludland.utils.math;

public class MatrixIndexOutBoundException extends RuntimeException {
    public MatrixIndexOutBoundException() {
        super();
    }

    public MatrixIndexOutBoundException(String message) {
        super(message);
    }

    public MatrixIndexOutBoundException(int row, int column) {
        super(String.format("Row or column index out of range: row=%d, column=%d", row, column));
    }
}
