import main.java.comparator.JSONObjectComparator;
import main.java.comparator.JSONObjectDifferenceComparator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private JSONObject jsonObject;
    private final ArrayList<Object> prices;


    public Data(String filePath) {
        prices = new ArrayList<>();
        deserializeData(filePath);
    }

    public void deserializeData(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Path relativePath = Paths.get(filePath);
            jsonObject = (JSONObject) parser.parse(new FileReader(relativePath.toAbsolutePath().toString()));
            setPrices();

        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
    }

    public void setPrices() {
        prices.addAll((JSONArray) jsonObject.get("prices"));
        filterData();
        validateData();
    }

    public void filterData() {
        ArrayList<Object> toRemove = new ArrayList<>();
        for (Object price : prices) {
            JSONObject openPrice = (JSONObject) price;
            if (openPrice.get("open") == null) {
                toRemove.add(price);
            }
        }
        prices.removeAll(toRemove);
    }

    private void validateData() {
        if (prices.size() == 0) {
            throw new IllegalArgumentException();
        }
    }

    public Date getMinPrice() {
        prices.sort(new JSONObjectComparator("low"));
        JSONObject minPrice = (JSONObject) prices.get(0);

        return convertEpochToDate((long) minPrice.get("date"));
    }

    public Date getMaxPrice() {
        prices.sort(new JSONObjectComparator("high").reversed());
        JSONObject maxPrice = (JSONObject) prices.get(0);

        return convertEpochToDate((long) maxPrice.get("date"));
    }

    public Date getMaxDifference() {
        prices.sort(new JSONObjectDifferenceComparator().reversed());
        JSONObject maxDifferencePrice = (JSONObject) prices.get(0);

        return convertEpochToDate((long) maxDifferencePrice.get("date"));
    }

    public double getAverageClosePrice() {
        double sum = 0;
        for (Object price : prices) {
            JSONObject averageClosePrice = (JSONObject) price;
            sum += ((Number) averageClosePrice.get("close")).floatValue();
        }

        return (sum / prices.size());
    }

    public Date convertEpochToDate(long epoch) {
        return new java.util.Date(epoch * 1000);
    }

    public ArrayList<Object> getPrices() {
        return prices;
    }
}
