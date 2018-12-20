import Exceptions.StringValidationException;
import Exceptions.UMValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testWhenGivenOnlyOneDVInStringRuns() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "10cm";
        assertEquals("10.0cm", c.calculate(s, "cm").toString());
    }

    @Test
    public void testWhenGivenOnlyOneDVInStringConvertsItToWantedUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "10cm";
        assertEquals("100.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringWithMultipleDVReadsFirstNumber() throws UMValidationException {
        Calculator c = new Calculator();
        String s = "1mm+5mm";
        DistanceValue dv = c.readDistanceValue(s);
        assertEquals("1.0mm", dv.toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfTwoDVWithSameUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "1mm+5mm";
        assertEquals("6.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfTwoDVWithSameUMAndConvertsResultToDifferentUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "10mm+50mm";
        assertEquals("6.0cm", c.calculate(s, "cm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfThreeDVWithSameUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "1mm+5mm+10mm";
        assertEquals("16.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesAdditionOfTwoDVWithDifferentUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "10mm+15cm";
        assertEquals("160.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesSubtractionOfTwoDVWithSameUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "20mm-10mm";
        assertEquals("10.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenStringCalculatesSubtractionOfTwoDVWithSameUMAndConvertsResultToDifferentUM() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "20mm-10mm";
        assertEquals("10.0mm", c.calculate(s, "mm").toString());
    }

    @Test
    public void testWhenGivenParametersForAdditionComputesResult() {
        Calculator c = new Calculator();
        DistanceValue dv1 = new DistanceValue(20d, "cm");
        DistanceValue dv2 = new DistanceValue(10d, "mm");
        assertEquals("21.0cm", c.compute(dv1, dv2, "addition").toString());

    }

    @Test
    public void testWhenGivenParametersForSubtractionComputesResult() {
        Calculator c = new Calculator();
        DistanceValue dv1 = new DistanceValue(20d, "cm");
        DistanceValue dv2 = new DistanceValue(10d, "mm");
        assertEquals("19.0cm", c.compute(dv1, dv2, "subtraction").toString());
    }

    @Test(expected = StringValidationException.class)
    public void testStringValidationExceptionIsThrown() throws StringValidationException, UMValidationException {
        Calculator c = new Calculator();
        String s = "*10mm+15cm";
        c.calculate(s, "mm");
    }

    @Test (expected = UMValidationException.class)
    public void testUMValidationExceptionIsThrown() throws UMValidationException, StringValidationException {
        Calculator c = new Calculator();
        String s = "10mmm+15cm";
        c.calculate(s, "mm");
    }
}
