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
public class MentalApiParser {

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
        StocksList mentalsaved = new StocksList();

        //From the API we search the data JSON object and then get the necessary fields//
        try {
            JSONObject rootObject = new JSONObject(jsonString);
            JSONArray firstArray = rootObject.getJSONArray("relatedLink");
            JSONObject searchArray = firstArray.getJSONObject(0);
            JSONArray mainEntityOfPage1 = rootObject.getJSONArray("mainEntityOfPage");
            JSONObject main0 = mainEntityOfPage1.getJSONObject(0);
            JSONObject mainSymp = mainEntityOfPage1.getJSONObject(1);
            JSONArray mainEntityOfPage2 = main0.getJSONArray("mainEntityOfPage");
            JSONArray mainEntityOfPage3 = mainSymp.getJSONArray("hasPart");

            JSONObject main1 = mainEntityOfPage2.getJSONObject(0);
            JSONObject mainSymp1 = mainEntityOfPage3.getJSONObject(0);







            String name = searchArray.getString("name");
                String description = main1.getString("text");
                String symptoms = mainSymp1.getString("text");

            if (description.contains("<b>")|| description.contains("<p>") || description.contains("/<p>")||description.contains("</b>")||symptoms.contains("<li>"))
            {
                description = description.replace("<b>", "");
                description = description.replace("<p>", "");
                description = description.replace("</b>", "");
                description = description.replace("</p>", "");


            }

            if (symptoms.contains("<b>")|| symptoms.contains("<p>") || symptoms.contains("/<p>")||symptoms.contains("</b>")||symptoms.contains("<li>"))
            {
                symptoms = symptoms.replace("<b>", "");
                symptoms = symptoms.replace("<p>", "");
                symptoms = symptoms.replace("</b>", "");
                symptoms = symptoms.replace("</p>", "");
                symptoms = symptoms.replace("<li>", "\n");
                symptoms = symptoms.replace("<ul>", "");
                symptoms = symptoms.replace("</ul>", "\n");
                symptoms = symptoms.replace("</li>", "");
                symptoms = symptoms.replace("</h3>", "");
                symptoms = symptoms.replace("<h3>", "");







            }


                    // create a StocksList with the extracted information
                    StocksList cl = new StocksList();
                    cl.setSymptoms(symptoms);
                    cl.setName(name);
                    cl.setDescription(description);
                    mentalsaved= cl;



                    //Just in case of an error//
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }
        return mentalsaved;
    }
}
