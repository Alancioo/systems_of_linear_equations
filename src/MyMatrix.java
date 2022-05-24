import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import Variables.*;

public class MyMatrix<T extends Operations<T>> {
    private final List<List<T>> matrix;

    public MyMatrix(MyMatrix<T> matrix) {
        this.matrix = matrix.matrix;
    }

    public MyMatrix(int n, List<T> matrixElements) {
        this.matrix = new ArrayList<>();
        for (int row = 0; row < matrixElements.size(); row = row + n) {
            this.matrix.add(new ArrayList<>());
            for (int column = row; column < row + n; column++) {
                this.matrix.get(this.matrix.size() - 1).add(matrixElements.get(column));
            }
        }
    }

    MyMatrix<T> addMatrix(MyMatrix<T> matrix) {
        List<T> newMatrixElements = new ArrayList<>();
        for (int row = 0; row < matrix.matrix.size(); row++) {
            for (int column = 0; column < matrix.matrix.size(); column++) {
                T temp = this.matrix.get(row).get(column);
                temp.add(matrix.matrix.get(row).get(column));
                newMatrixElements.add(temp);
            }
        }
        return new MyMatrix<>(this.matrix.size(), newMatrixElements);
    }

    MyMatrix<T> subtractMatrix(MyMatrix<T> matrix) {
        List<T> newMatrixElements = new ArrayList<>();
        for (int row = 0; row < matrix.matrix.size(); row++) {
            for (int column = 0; column < matrix.matrix.get(0).size(); column++) {
                T temp = this.matrix.get(row).get(column);
                temp.subtract(matrix.matrix.get(row).get(column));
                newMatrixElements.add(temp);
            }
        }
        return new MyMatrix<>(this.matrix.size(), newMatrixElements);
    }

    MyMatrix<T> multiplyMatrix(MyMatrix<T> matrix) {
        int rows = this.matrix.size();
        int columns = matrix.countColumns();
        List<T> matrixElements = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                T sum = matrix.matrix.get(0).get(0).initializeWithZero();
                for (int j = 0; j < rows; j++) {
                    T firstNumber = matrix.matrix.get(0).get(0).initialize(this.matrix.get(i).get(j));
                    T secondNumber = matrix.matrix.get(0).get(0).initialize(matrix.matrix.get(j).get(k));
                    firstNumber.multiply(secondNumber);
                    sum.add(firstNumber);
                }
                matrixElements.add(sum);

            }
        }
        return new MyMatrix<>(columns, matrixElements);
    }

    List<List<T>> getMatrix() {
        return this.matrix;
    }

    List<List<BigDecimal>> getMatrixWithNumbers() {
        return this.matrix.stream().map(el -> el.stream().map(T::returnValue).collect(Collectors.toList())).collect(Collectors.toList());
    }

    public Integer countRows() {
        return this.matrix.size();
    }

    public Integer countColumns() {
        return this.matrix.get(0).size();
    }

    public void reverse() {
        Collections.reverse(this.matrix);
    }
}
