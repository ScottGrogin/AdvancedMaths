import com.scott.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    private final double DELTA = 0.0001;
    @Test
    public void construction() {
        try{
            Matrix m = new Matrix(0,-1);
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("The minimum dimension for rows and columns is 1.",e.getMessage());
        }
        try{
            Matrix m = new Matrix(0,-1,new double[2]);
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("The minimum dimension for rows and columns is 1.",e.getMessage());
        }
        try{
            Matrix m = new Matrix(1,1, new double[2]);
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Dimensions do not match provided matrix size.",e.getMessage());
        }
        Matrix m = new Matrix(3,3);
        assertEquals(9, m.getMatrixArray().length);
    }
    @Test
    public void getCell() {
        Matrix m = new Matrix(3,3,new double[]{1,2,3,
                                                             4,5,6,
                                                             7,8,9});
        assertEquals(1,m.getCell(0,0),DELTA);
        assertEquals(2,m.getCell(0,1),DELTA);
        assertEquals(3,m.getCell(0,2),DELTA);
        assertEquals(4,m.getCell(1,0),DELTA);
        assertEquals(5,m.getCell(1,1),DELTA);
        assertEquals(6,m.getCell(1,2),DELTA);
        assertEquals(7,m.getCell(2,0),DELTA);
        assertEquals(8,m.getCell(2,1),DELTA);
        assertEquals(9,m.getCell(2,2),DELTA);
        try {
            m.getCell(0,-1);
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Indices are out of bounds.", e.getMessage());
        }
    }

    @Test
    public void setCell() {
        Matrix m = new Matrix(3,3);
        m.setCell(0,0,1);
        m.setCell(0,1,2);
        m.setCell(0,2,3);
        m.setCell(1,0,4);
        m.setCell(1,1,5);
        m.setCell(1,2,6);
        m.setCell(2,0,7);
        m.setCell(2,1,8);
        m.setCell(2,2,9);

        assertEquals(1,m.getCell(0,0),DELTA);
        assertEquals(2,m.getCell(0,1),DELTA);
        assertEquals(3,m.getCell(0,2),DELTA);
        assertEquals(4,m.getCell(1,0),DELTA);
        assertEquals(5,m.getCell(1,1),DELTA);
        assertEquals(6,m.getCell(1,2),DELTA);
        assertEquals(7,m.getCell(2,0),DELTA);
        assertEquals(8,m.getCell(2,1),DELTA);
        assertEquals(9,m.getCell(2,2),DELTA);

        try {
            m.setCell(0,-1,8);
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Indices are out of bounds.", e.getMessage());
        }
    }

    @Test
    public void getMatrixArray() {
        assertEquals(1, new Matrix(1,1).getMatrixArray().length);
    }

    @Test
    public void getDimensions() {
        Matrix m = new Matrix(3,3,new double[]{1,2,3,
                                                             4,5,6,
                                                             7,8,9});
        assertEquals(3, m.getColumns());
        assertEquals(3, m.getRows());
    }
}