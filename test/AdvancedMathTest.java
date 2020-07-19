import com.scott.AdvancedMath;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdvancedMathTest {
    private final double DELTA = 0.0001;
    @Test
    public void lerp() {
        assertEquals(0, AdvancedMath.lerp(0,0,0), DELTA);
        assertEquals(0,AdvancedMath.lerp(-1,1,0.5), DELTA);
        assertEquals(0,AdvancedMath.lerp(0,1,0), DELTA);
        assertEquals(1,AdvancedMath.lerp(0,1,1), DELTA);
        assertEquals(0.5,AdvancedMath.lerp(0,1,0.5), DELTA);
        assertEquals(25,AdvancedMath.lerp(0,100,0.25), DELTA);
        assertEquals(-50,AdvancedMath.lerp(-100,100,0.25), DELTA);
    }

    @Test
    public void clamp() {
        assertEquals(2,AdvancedMath.clamp(2,-10,10), DELTA);
        assertEquals(9.9,AdvancedMath.clamp(9.9,-10,10), DELTA);
        assertEquals(10,AdvancedMath.clamp(10,-10,10), DELTA);
        assertEquals(10,AdvancedMath.clamp(10000,-10,10), DELTA);
        assertEquals(-10,AdvancedMath.clamp(-10000,-10,10), DELTA);
        assertEquals(10,AdvancedMath.clamp(10.9999,-10,10), DELTA);
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
            assertEquals(1, AdvancedMath.dotProduct(vec,vec), DELTA);
            assertEquals(0, AdvancedMath.dotProduct(vec2,vec2), DELTA);
            vec2.add(4.0);
            assertEquals(4, AdvancedMath.dotProduct(vec,vec2), DELTA);
            vec.add(3.0);
            vec2.add(0.0);
            assertEquals(4, AdvancedMath.dotProduct(vec,vec2), DELTA);
            vec.add(3.0);
            vec2.add(4.0);
            assertEquals(16, AdvancedMath.dotProduct(vec,vec2), DELTA);
            vec.add(-1.0);
            vec2.add(4.0);
            assertEquals(12, AdvancedMath.dotProduct(vec,vec2), DELTA);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        vec.clear();
        try {
           AdvancedMath.dotProduct(vec,vec2);
           fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Inputs A and B must be the same size to compute dot product.",e.getMessage());
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
    }

    @Test
    public void scalarMultiply() {
        ArrayList<Double> vec = new ArrayList<>();
        ArrayList<Double> result = AdvancedMath.scalarMultiply(2.0,vec);
        assertTrue(result.isEmpty());
        vec.add(0.0);
        vec.add(1.0);
        vec.add(1.5);
        vec.add(-2.0);

        result = AdvancedMath.scalarMultiply(2.0,vec);
        //Make sure original list was not modified.
        assertEquals(0,vec.get(0), DELTA);
        assertEquals(1,vec.get(1), DELTA);
        assertEquals(1.5,vec.get(2), DELTA);
        assertEquals(-2,vec.get(3), DELTA);
        //Check that values are doubled.
        assertEquals(0,result.get(0), DELTA);
        assertEquals(2,result.get(1), DELTA);
        assertEquals(3,result.get(2), DELTA);
        assertEquals(-4,result.get(3), DELTA);
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
            ArrayList<Double> result = AdvancedMath.crossProduct(vec,vec);
            assertEquals(0,result.get(0),DELTA);
            assertEquals(0,result.get(1),DELTA);
            assertEquals(0,result.get(2),DELTA);
            result = AdvancedMath.crossProduct(vec,vec2);
            assertEquals(0,result.get(0),DELTA);
            assertEquals(0,result.get(1),DELTA);
            assertEquals(1,result.get(2),DELTA);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        vec.clear();
        try {
           AdvancedMath.crossProduct(vec,vec);
           fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Inputs A and B need to be of size 3 to compute cross product.",e.getMessage());
        }


    }

    @Test
    public void normalize() {
        ArrayList<Double> vec = new ArrayList<>();
        ArrayList<Double> result = AdvancedMath.normalize(vec);
        assertTrue(result.isEmpty());
        vec.add(1.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(1,result.get(0),DELTA);
        vec.clear();
        vec.add(-100.0);
        vec.add(0.0);
        vec.add(0.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(-1,result.get(0),DELTA);
        assertEquals(0,result.get(1),DELTA);
        assertEquals(0,result.get(2),DELTA);
        vec.clear();
        vec.add(0.0);
        vec.add(482910.0);
        vec.add(0.0);
        result = AdvancedMath.normalize(vec);
        assertEquals(0,result.get(0),DELTA);
        assertEquals(1,result.get(1),DELTA);
        assertEquals(0,result.get(2),DELTA);
    }
}