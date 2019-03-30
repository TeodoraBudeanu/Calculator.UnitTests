package model;

import main.model.Calculator;
import main.model.DistanceValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private DistanceValue dv1;
    private DistanceValue dv2;
    private DistanceValue dv3;
    private DistanceValue expected;

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[][]{
                {new DistanceValue(10d, "mm"), new DistanceValue(5d, "dm"),
                        new DistanceValue(20d, "cm"), new DistanceValue(310d, "mm")},
                {new DistanceValue(20d, "m"), new DistanceValue(1d, "km"),
                new DistanceValue(30000d, "cm"), new DistanceValue(720d, "m")}});
    }

    public ParameterizedTest(DistanceValue dv1, DistanceValue dv2, DistanceValue dv3, DistanceValue expected) {
        this.dv1 = dv1;
        this.dv2 = dv2;
        this.dv3 = dv3;
        this.expected = expected;
    }

    @Test
    public void testComputeDistanceValuesWithAdditionAndSubtraction() {
        Calculator c = new Calculator();
        DistanceValue result1 = c.compute(dv1, dv2, "addition");
        DistanceValue result2 = c.compute(result1, dv3, "subtraction");
        assertEquals(result2.toString(), expected.toString());
    }

}