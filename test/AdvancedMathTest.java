import com.scott.AdvancedMath;
import com.scott.Matrix;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdvancedMathTest {
    private final double DELTA = 0.0001;

    @Test
    public void identityMatrix() {
        Matrix m = AdvancedMath.identityMatrix(3);
        assertEquals(1, m.getCell(0, 0), DELTA);
        assertEquals(0, m.getCell(0, 1), DELTA);
        assertEquals(0, m.getCell(0, 2), DELTA);
        assertEquals(0, m.getCell(1, 0), DELTA);
        assertEquals(1, m.getCell(1, 1), DELTA);
        assertEquals(0, m.getCell(1, 2), DELTA);
        assertEquals(0, m.getCell(2, 0), DELTA);
        assertEquals(0, m.getCell(2, 1), DELTA);
        assertEquals(1, m.getCell(2, 2), DELTA);
        try {
            AdvancedMath.identityMatrix(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("The dimension of the matrix must be at least 1", e.getMessage());
        }
    }

    @Test
    public void lerp() {
        assertEquals(0, AdvancedMath.lerp(0, 0, 0), DELTA);
        assertEquals(0, AdvancedMath.lerp(-1, 1, 0.5), DELTA);
        assertEquals(0, AdvancedMath.lerp(0, 1, 0), DELTA);
        assertEquals(1, AdvancedMath.lerp(0, 1, 1), DELTA);
        assertEquals(0.5, AdvancedMath.lerp(0, 1, 0.5), DELTA);
        assertEquals(25, AdvancedMath.lerp(0, 100, 0.25), DELTA);
        assertEquals(-50, AdvancedMath.lerp(-100, 100, 0.25), DELTA);
    }

    @Test
    public void clamp() {
        assertEquals(2, AdvancedMath.clamp(2, -10, 10), DELTA);
        assertEquals(9.9, AdvancedMath.clamp(9.9, -10, 10), DELTA);
        assertEquals(10, AdvancedMath.clamp(10, -10, 10), DELTA);
        assertEquals(10, AdvancedMath.clamp(10000, -10, 10), DELTA);
        assertEquals(-10, AdvancedMath.clamp(-10000, -10, 10), DELTA);
        assertEquals(10, AdvancedMath.clamp(10.9999, -10, 10), DELTA);
    }

    @Test
    public void fract() {
        assertEquals(0.14159, AdvancedMath.fract(3.14159), DELTA);
        assertEquals(-0.14159, AdvancedMath.fract(-3.14159), DELTA);
        assertEquals(0.1, AdvancedMath.fract(0.1), DELTA);
        assertEquals(0, AdvancedMath.fract(0), DELTA);
        assertEquals(0, AdvancedMath.fract(-1), DELTA);
        assertEquals(0, AdvancedMath.fract(3), DELTA);
    }

    @Test
    public void dotProduct() {
        ArrayList<Double> vec = new ArrayList<>();
        ArrayList<Double> vec2 = new ArrayList<>();
        try {
            vec.add(1.0);
            assertEquals(1, AdvancedMath.dotProduct(vec, vec), DELTA);
            assertEquals(0, AdvancedMath.dotProduct(vec2, vec2), DELTA);
            vec2.add(4.0);
            assertEquals(4, AdvancedMath.dotProduct(vec, vec2), DELTA);
            vec.add(3.0);
            vec2.add(0.0);
            assertEquals(4, AdvancedMath.dotProduct(vec, vec2), DELTA);
            vec.add(3.0);
            vec2.add(4.0);
            assertEquals(16, AdvancedMath.dotProduct(vec, vec2), DELTA);
            vec.add(-1.0);
            vec2.add(4.0);
            assertEquals(12, AdvancedMath.dotProduct(vec, vec2), DELTA);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        vec.clear();
        try {
            AdvancedMath.dotProduct(vec, vec2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inputs A and B must be the same size to compute dot product.", e.getMessage());
        }
        try {
            double d = AdvancedMath.dotProduct(new double[]{0, 1, 2}, new double[]{0});
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inputs A and B must be the same size to compute dot product.", e.getMessage());
        }
    }

    @Test
    public void magnitude() {
        ArrayList<Double> vec = new ArrayList<>();
        vec.add(-1.0); //[-1]
        assertEquals(1, AdvancedMath.magnitude(vec), DELTA);
        vec.add(0.0);//[-1,0]
        assertEquals(1, AdvancedMath.magnitude(vec), DELTA);
        vec.add(2.0);//[-1,0,2]
        vec.add(2.0);//[-1,0,2,2]
        assertEquals(3, AdvancedMath.magnitude(vec), DELTA);
        assertEquals(5, AdvancedMath.magnitude(new double[]{2, 2, 2, 2, 2, 2, 1}), DELTA);
        assertEquals(0, AdvancedMath.magnitude(new double[]{}), DELTA);
    }

    @Test
    public void scalarMultiply() {
        ArrayList<Double> vec = new ArrayList<>();
        ArrayList<Double> result = AdvancedMath.scalarMultiply(2.0, vec);
        assertTrue(result.isEmpty());
        vec.add(0.0);
        vec.add(1.0);
        vec.add(1.5);
        vec.add(-2.0);

        result = AdvancedMath.scalarMultiply(2.0, vec);
        //Make sure original list was not modified.
        assertEquals(0, vec.get(0), DELTA);
        assertEquals(1, vec.get(1), DELTA);
        assertEquals(1.5, vec.get(2), DELTA);
        assertEquals(-2, vec.get(3), DELTA);
        //Check that values are doubled.
        assertEquals(0, result.get(0), DELTA);
        assertEquals(2, result.get(1), DELTA);
        assertEquals(3, result.get(2), DELTA);
        assertEquals(-4, result.get(3), DELTA);

        double[] resultArr = {0, 2, 4, 6, 8};

        assertArrayEquals(resultArr, AdvancedMath.scalarMultiply(2, new double[]{0, 1, 2, 3, 4}), DELTA);

        assertArrayEquals(new double[]{0, 2, 4, 6},
                AdvancedMath.scalarMultiply(2,
                        new Matrix(2, 2, new double[]{0, 1, 2, 3})).getMatrixArray(), DELTA);
    }

    @Test
    public void crossProduct() {
        ArrayList<Double> vec = new ArrayList<>();
        vec.add(1.0);
        vec.add(0.0);
        vec.add(0.0);
        ArrayList<Double> vec2 = new ArrayList<>();
        vec2.add(0.0);
        vec2.add(1.0);
        vec2.add(0.0);
        try {
            ArrayList<Double> result = AdvancedMath.crossProduct(vec, vec);
            assertEquals(0, result.get(0), DELTA);
            assertEquals(0, result.get(1), DELTA);
            assertEquals(0, result.get(2), DELTA);
            result = AdvancedMath.crossProduct(vec, vec2);
            assertEquals(0, result.get(0), DELTA);
            assertEquals(0, result.get(1), DELTA);
            assertEquals(1, result.get(2), DELTA);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        vec.clear();
        try {
            AdvancedMath.crossProduct(vec, vec);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inputs A and B need to be of size 3 to compute cross product.", e.getMessage());
        }


    }

    @Test
    public void normalize() {
        ArrayList<Double> vec = new ArrayList<>();
        ArrayList<Double> result = AdvancedMath.normalize(vec);
        assertTrue(result.isEmpty());
        vec.add(1.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(1, result.get(0), DELTA);
        vec.clear();
        vec.add(-100.0);
        vec.add(0.0);
        vec.add(0.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(-1, result.get(0), DELTA);
        assertEquals(0, result.get(1), DELTA);
        assertEquals(0, result.get(2), DELTA);
        vec.clear();
        vec.add(0.0);
        vec.add(482910.0);
        vec.add(0.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(0, result.get(0), DELTA);
        assertEquals(1, result.get(1), DELTA);
        assertEquals(0, result.get(2), DELTA);

        assertArrayEquals(new double[]{0, 1, 0}, AdvancedMath.normalize(new double[]{0, 64328164, 0}), DELTA);
        assertArrayEquals(new double[]{-1, 0, 0}, AdvancedMath.normalize(new double[]{-10420, 0, 0}), DELTA);
    }

    @Test
    public void herp() {
        assertEquals(0, AdvancedMath.herp(0, 0, 0), DELTA);
        assertEquals(0, AdvancedMath.herp(-1, 1, 0.5), DELTA);
        assertEquals(0, AdvancedMath.herp(0, 1, 0), DELTA);
        assertEquals(1, AdvancedMath.herp(0, 1, 1), DELTA);
        assertEquals(0.5, AdvancedMath.herp(0, 1, 0.5), DELTA);
        assertEquals(15.625, AdvancedMath.herp(0, 100, 0.25), DELTA);
    }

    @Test
    public void matrixMultiply() {
        ArrayList<Double> input = new ArrayList<>();
        input.add(3.0);
        input.add(6.0);
        input.add(9.0);
        ArrayList<Double> result = new ArrayList<>();
        result.add(6.0);
        result.add(12.0);
        result.add(18.0);
        assertArrayEquals(AdvancedMath.identityMatrix(100).getMatrixArray(),
                AdvancedMath.matrixMultiply(AdvancedMath.identityMatrix(100),
                        AdvancedMath.identityMatrix(100)).getMatrixArray(), DELTA);
        assertArrayEquals(new double[]{0, 0, 0},
                AdvancedMath.matrixMultiply(AdvancedMath.identityMatrix(3), new double[]{0, 0, 0}), DELTA);
        assertArrayEquals(new double[]{3, 6, 9},
                AdvancedMath.matrixMultiply(AdvancedMath.identityMatrix(3), new double[]{3, 6, 9}), DELTA);
        assertArrayEquals(new double[]{3, 12, 0},
                AdvancedMath.matrixMultiply(new Matrix(3, 3, new double[]{1, 0, 0, 0, 2, 0, 0, 0, 0})
                        , new double[]{3, 6, 9}), DELTA);
        assertEquals(result, AdvancedMath.matrixMultiply(
                new Matrix(3, 3, new double[]{2, 0, 0, 0, 2, 0, 0, 0, 2}), input));
        try {
            input.add(9876.0);
            AdvancedMath.matrixMultiply(new Matrix(3, 3, new double[]{2, 0, 0, 0, 2, 0, 0, 0, 2}), input);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("The number of columns in matrix A must match the number of " +
                    "elements in vector B.", e.getMessage());
        }
        try {
            AdvancedMath.matrixMultiply(AdvancedMath.identityMatrix(3), new double[]{0, 0, 0, 0});
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("The number of columns in matrix A must match the number of " +
                    "elements in vector B.", e.getMessage());
        }
        try {
            AdvancedMath.matrixMultiply(AdvancedMath.identityMatrix(100),
                    AdvancedMath.identityMatrix(10)).getMatrixArray();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("The number of columns in matrix A must match the number of " +
                    "rows in matrix B.", e.getMessage());
        }
    }

    @Test
    public void getSubMatrix() {
        for (double d : AdvancedMath.identityMatrix(2).getMatrixArray()) {
            System.out.println(d);
        }
        assertArrayEquals(new double[]{1, 0, 0, 1}, AdvancedMath.getSubMatrix(AdvancedMath.identityMatrix(3),
                0, 0).getMatrixArray(), DELTA);
    }

    @Test
    public void det() {
        try {
            AdvancedMath.det(new Matrix(4, 5));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrix must have same number of " +
                    "rows and columns to calculate determinant.", e.getMessage());
        }
        assertEquals(2, AdvancedMath.det(new Matrix(1, 1,
                new double[]{2})), DELTA);
        assertEquals(-376, AdvancedMath.det(new Matrix(4, 4,
                new double[]{1, 3, 5, 9, 1, 3, 1, 7, 4, 3, 9, 7, 5, 2, 0, 9})), DELTA);
        assertEquals(-195064, AdvancedMath.det(new Matrix(5, 5,
                new double[]{5, 1, 3, 0, 1, 9, 4, 1, 3, 8, 5, 0, 8, 1, 3, 1, 8, 3, 99, 10, 43, 9, 84, 90, 1})), DELTA);
    }

    @Test
    public void subtract() {
        double[] A = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] B = new double[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        ArrayList<Double> aAL = new ArrayList<>();
        aAL.add(1.0);
        aAL.add(2.0);
        aAL.add(3.0);
        ArrayList<Double> bAL = new ArrayList<>();
        bAL.add(3.0);
        bAL.add(2.0);
        bAL.add(1.0);
        ArrayList<Double> result = new ArrayList<>();
        result.add(-2.0);
        result.add(0.0);
        result.add(2.0);
        assertArrayEquals(new double[]{-8, -6, -4, -2, 0, 2, 4, 6, 8}, AdvancedMath.subtract(A, B), DELTA);
        assertArrayEquals(new double[]{-8, -6, -4, -2, 0, 2, 4, 6, 8}, AdvancedMath.subtract(
                new Matrix(3, 3, A), new Matrix(3, 3, B)).getMatrixArray(), DELTA);
        assertEquals(result, AdvancedMath.subtract(aAL, bAL));
        B = new double[]{1, 2, 3, 4};
        bAL.add(1234567.0);
        try {
            AdvancedMath.subtract(new Matrix(3, 3, A), new Matrix(2, 2, B));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrices A and B must have the same dimensions to add.", e.getMessage());
        }
        try {
            AdvancedMath.subtract(A, B);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Vectors A and B must be the same size to add.", e.getMessage());
        }
        try {
            AdvancedMath.subtract(aAL, bAL);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Vectors A and B must be the same size to add.", e.getMessage());
        }
    }

    @Test
    public void transform() {
        Matrix m = new Matrix(3, 2, new double[]{1, 2,
                3, 4,
                4, 5});
        Matrix res = AdvancedMath.transform(m);
        assertEquals(res.getCell(0, 0), m.getCell(0, 0), DELTA);
        assertEquals(res.getCell(0, 1), m.getCell(1, 0), DELTA);
        assertEquals(res.getCell(0, 2), m.getCell(2, 0), DELTA);
        assertEquals(res.getCell(1, 0), m.getCell(0, 1), DELTA);
        assertEquals(res.getCell(1, 1), m.getCell(1, 1), DELTA);
        assertEquals(res.getCell(1, 2), m.getCell(2, 1), DELTA);

        m = new Matrix(4, 4, new double[]{1, 2, 3, 4,
                3, 4, 3, 2,
                4, 5, 9, 3,
                5, 2, 6, 3});
        res = AdvancedMath.transform(m);
        assertEquals(res.getCell(0, 0), m.getCell(0, 0), DELTA);
        assertEquals(res.getCell(0, 1), m.getCell(1, 0), DELTA);
        assertEquals(res.getCell(0, 2), m.getCell(2, 0), DELTA);
        assertEquals(res.getCell(0, 3), m.getCell(3, 0), DELTA);
        assertEquals(res.getCell(1, 0), m.getCell(0, 1), DELTA);
        assertEquals(res.getCell(1, 1), m.getCell(1, 1), DELTA);
        assertEquals(res.getCell(1, 2), m.getCell(2, 1), DELTA);
        assertEquals(res.getCell(1, 3), m.getCell(3, 1), DELTA);
        assertEquals(res.getCell(2, 0), m.getCell(0, 2), DELTA);
        assertEquals(res.getCell(2, 1), m.getCell(1, 2), DELTA);
        assertEquals(res.getCell(2, 2), m.getCell(2, 2), DELTA);
        assertEquals(res.getCell(2, 3), m.getCell(3, 2), DELTA);
        assertEquals(res.getCell(3, 0), m.getCell(0, 3), DELTA);
        assertEquals(res.getCell(3, 1), m.getCell(1, 3), DELTA);
        assertEquals(res.getCell(3, 2), m.getCell(2, 3), DELTA);
        assertEquals(res.getCell(3, 3), m.getCell(3, 3), DELTA);

    }

    @Test
    public void adj() {
        assertArrayEquals(new double[]{-3, 6, -3, 6, -12, 6, -3, 6, -3},
                AdvancedMath.adj(new Matrix(3, 3,
                        new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9})).getMatrixArray(), DELTA);
        assertArrayEquals(new double[]{0, 0, 0, 0, 9, 27, 0, -36, -18, -54, 0, 72, 9, 27, 0, -36},
                AdvancedMath.adj(new Matrix(4, 4,
                        new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7})).getMatrixArray(), DELTA);
        try {
            AdvancedMath.adj(new Matrix(4, 5));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrix must have same number of " +
                    "rows and columns to calculate adjoint.", e.getMessage());
        }
    }

    @Test
    public void inv() {
        try {
            AdvancedMath.inv(new Matrix(4, 5));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrix must have same number of " +
                    "rows and columns to calculate inverse.", e.getMessage());
        }
        try {
            AdvancedMath.inv(new Matrix(3, 3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("A matrix with a zero determinant has no inverse.", e.getMessage());
        }
        Matrix m = new Matrix(3, 3, new double[]{2, 2, 3,
                4, 5, 6,
                7, 8, 9});
        assertArrayEquals(AdvancedMath.identityMatrix(3).getMatrixArray(),
                AdvancedMath.matrixMultiply(m, AdvancedMath.inv(m)).getMatrixArray(), DELTA);

        m = new Matrix(2, 2, new double[]{2, 2,
                4, 5});
        assertArrayEquals(AdvancedMath.identityMatrix(2).getMatrixArray(),
                AdvancedMath.matrixMultiply(m, AdvancedMath.inv(m)).getMatrixArray(), DELTA);

        Matrix x = new Matrix(4, 4, new double[]{1, 2, 2, 3,
                2, 3, 3, 4,
                4, 52, 1, 3,
                1, 6, 4, 2,});
        System.out.println(AdvancedMath.det(x));
        double[] l = AdvancedMath.inv(x).getMatrixArray();

        assertArrayEquals(AdvancedMath.identityMatrix(4).getMatrixArray(),

                AdvancedMath.matrixMultiply(x, AdvancedMath.inv(x)).getMatrixArray(), DELTA);
    }
}