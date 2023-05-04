package cm3110.gigachadapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles converting the JSON returned by the WeatherAPI Web Services into
 * objects usable by the app.
 */
public class TreatmentAPIParser {

    /**
     *
     * @param jsonString The JSON String returned by the Crypto APi
     * @param Cname The name of the crypto
     * @return A {@link List} of {@link StocksList} objects parsed from the jsonString.
     * @throws JSONException if any error occurs with processing the jsonString
     * @throws ParseException if any error occurs with processing the jsonString
     */
    public StocksList convertStockJson(String jsonString, String Cname) throws JSONException, ParseException{


        // for storing the parsed Crypto//
        List<StocksList> stocksLists = new ArrayList<StocksList>();
        StocksList treatmentsaved = new StocksList();

        //From the API we search the data JSON object and then get the necessary fields//
        try {
            JSONObject rootObject = new JSONObject(jsonString);
            JSONObject cryptoObject = rootObject.getJSONObject("about");
            Object descriptionObject = rootObject.get("description");
            JSONArray hasPart = rootObject.getJSONArray("mainEntityOfPage");
            JSONObject haspartParts = hasPart.getJSONObject(0);
            JSONArray mainEntity = haspartParts.getJSONArray("mainEntityOfPage");
            JSONObject haspartParts2 = mainEntity.getJSONObject(0);


            System.out.println( "TESTING ARRAY SEARCH" + haspartParts);

                String name = cryptoObject.getString("name");
                String description = descriptionObject.toString();
                String treatment = haspartParts2.getString("text");
                if (treatment.contains("<b>")|| treatment.contains("<p>") || treatment.contains("/<p>")||treatment.contains("</b>"))
                {
                    treatment = treatment.replace("<b>", "");
                    treatment = treatment.replace("<p>", "");
                    treatment = treatment.replace("</b>", "");
                    treatment = treatment.replace("</p>", "");


                }


                    // create a StocksList with the extracted information
                    StocksList cl = new StocksList();
                    cl.setSymptoms(treatment);
                    cl.setName(name);
                    cl.setDescription(description);
                    treatmentsaved = cl;



                    //Just in case of an error//
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }
        return treatmentsaved;
    }
}
