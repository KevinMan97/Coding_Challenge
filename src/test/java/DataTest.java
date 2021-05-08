import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataTest {
    @Test
    public void testMinPrice() {
        Data data = new Data("src/test/resources/testData.txt");
        assertEquals(data.getMinPrice(), data.convertEpochToDate(1605254400));
    }

    @Test
    public void testMaxPrice() {
        Data data = new Data("src/test/resources/testData.txt");
        assertEquals(data.getMaxPrice(), data.convertEpochToDate(1605513600));
    }

    @Test
    public void testMaxDifferencePrice() {
        Data data = new Data("src/test/resources/testData.txt");
        assertEquals(data.getMaxDifference(), data.convertEpochToDate(1605254400));
    }

    @Test
    public void testAverageClosePrice() {
        Data data = new Data("src/test/resources/testData.txt");
        assertEquals(data.getAverageClosePrice(), 435.3);
    }

    @Test
    public void testFilterData() {
        Data data = new Data("src/test/resources/testData.txt");
        assertEquals(data.getPrices().size(), 5);
        try {
            Data dataNull = new Data("src/test/resources/nullData.txt");
            assertEquals(dataNull.getPrices().size(), 0);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testNullData() {
        assertThrows(IllegalArgumentException.class, () -> new Data("src/test/resources/nullData.txt"));
    }
}