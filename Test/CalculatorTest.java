import Exceptions.StringValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testWhenGivenStringReadsFirstNumber() {
        String s = "1mm+5mm";
        DistanceValue dv = Calculator.readDistanceValue(s);
        assertEquals("1.0mm", dv.toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfTwoDVWithSameUM() {
        String s = "1mm+5mm";
        assertEquals("6.0mm", Calculator.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfThreeDVWithSameUM() {
        String s = "1mm+5mm+10mm";
        assertEquals("16.0mm", Calculator.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfTwoDVWithDifferentUM() {
        String s = "10mm+15cm";
        assertEquals("160.0mm", Calculator.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenParametersComputesResult() {
        DistanceValue dv1 = new DistanceValue(20d, "cm");
        DistanceValue dv2 = new DistanceValue(10d, "mm");
        assertEquals("21.0cm", Calculator.compute(dv1, dv2, "addition").toString());
        assertEquals("19.0cm", Calculator.compute(dv1, dv2, "subtraction").toString());
    }

}
