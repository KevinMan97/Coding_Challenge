package main.java.comparator;

import org.json.simple.JSONObject;

import java.util.Comparator;

public class JSONObjectDifferenceComparator implements Comparator<Object> {

    @Override
    public int compare(Object objectOne, Object objectTwo) {
        JSONObject jsonObjectOne = (JSONObject) objectOne;
        JSONObject jsonObjectTwo = (JSONObject) objectTwo;
        float openPriceOne = ((Number) jsonObjectOne.get("open")).floatValue();
        float closePriceOne = ((Number) jsonObjectOne.get("close")).floatValue();
        float openPriceTwo = ((Number) jsonObjectTwo.get("open")).floatValue();
        float closePriceTwo = ((Number) jsonObjectTwo.get("close")).floatValue();
        float DifferencePriceOne = Math.abs(openPriceOne - closePriceOne);
        float DifferencePriceTwo = Math.abs(openPriceTwo - closePriceTwo);

        return Float.compare(DifferencePriceOne, DifferencePriceTwo);
    }
}
