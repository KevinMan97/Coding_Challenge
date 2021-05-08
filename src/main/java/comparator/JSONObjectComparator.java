package comparator;

import org.json.simple.JSONObject;

import java.util.Comparator;

public class JSONObjectComparator implements Comparator<Object> {
    private final String key;

    public JSONObjectComparator(String key) {
        this.key = key;
    }

    @Override
    public int compare(Object objectOne, Object objectTwo) {
        JSONObject jsonObjectOne = (JSONObject) objectOne;
        JSONObject jsonObjectTwo = (JSONObject) objectTwo;
        float keyPriceOne = ((Number) jsonObjectOne.get(key)).floatValue();
        float keyPriceTwo = ((Number) jsonObjectTwo.get(key)).floatValue();

        return Float.compare(keyPriceOne, keyPriceTwo);
    }
}
