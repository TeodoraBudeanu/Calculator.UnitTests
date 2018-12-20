import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private Converter converter;

    @Before
    public void setup() {
        converter = new Converter();
    }


    @Test
    public void testConvertDistanceToGivenUMFromHigherToLower() {
        DistanceValue dv1 = new DistanceValue(1d, "cm");
        String wantedUM = "mm";
        DistanceValue dv2 = converter.convert(dv1, wantedUM);
        assertEquals("10.0mm", dv2.toString());
    }

    @Test
    public void testConvertDistanceToGivenUMFromLowerToHigher() {
        DistanceValue dv1 = new DistanceValue(100d, "mm");
        String wantedUM = "cm";
        DistanceValue dv2 = converter.convert(dv1, wantedUM);
        assertEquals("10.0cm", dv2.toString());
    }

    @Test
    public void testWhenGivenSameUMReturnsValue1() {
        assertEquals("1.0", converter.getCoefficientValue("m", "m").toString());
    }

    @Test
    public void testWhenGivenDifferentUMReturnsProperCoefficient() {
        assertEquals("10.0", converter.getCoefficientValue("cm",
                "mm").toString());
    }


}
