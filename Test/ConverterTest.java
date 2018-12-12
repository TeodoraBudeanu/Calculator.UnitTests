import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @Test
    public void testConvertDistanceToGivenUM(){
        DistanceValue dv1 = new DistanceValue(1d, "cm");
        String wantedUM = "mm";
        DistanceValue dv2 = Converter.convert(dv1, wantedUM);
        assertEquals("10.0mm", dv2.toString());
    }
}
