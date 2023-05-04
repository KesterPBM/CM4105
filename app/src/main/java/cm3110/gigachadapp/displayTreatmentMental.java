package cm3110.gigachadapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cm3110.gigachadapp.data.MentalTreatmentAPIParser;
import cm3110.gigachadapp.data.StockRepository;
import cm3110.gigachadapp.data.StocksList;
import cm3110.gigachadapp.data.TreatmentAPIParser;


/**
 * A simple {@link Fragment} subclass.

 */
public class displayTreatmentMental extends Fragment implements View.OnClickListener {

    private static final String TAG = "StockData";
    public static String ARG_CRYPTO_NAME = "cryptoName";
    public static String  ARG_SAVE_CRYPTO = "crypto";
    // member variables for the setting up the display
    private String mCryptoName;
    public String savedMentalTreatments;
    public TextView tvMentalTreatmentTitle;
    public TextView tvMentalTreatment;

    // for data access via repository
    private StockRepository stockRepository;

    // for the data being display
    private List<StocksList> stocksLists;
    StocksList displaySymptoms = new StocksList();


    public displayTreatmentMental() {
        // Required empty public constructor
    }

    //Gets the passed on args to be used in the class//
    public static displayTreatmentMental newInstance(String stock) {
        displayTreatmentMental fragment = new displayTreatmentMental();
        Bundle args = new Bundle();
        args.putString(FindCrypto.ARG_CRYPTO_NAME, stock);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCryptoName = getArguments().getString(FindCrypto.ARG_CRYPTO_NAME);

        }
        //Gets the repository//
        stockRepository = StockRepository.getRepository(getContext());

        // get the data to display, is empty until filled//
        stocksLists = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.display_mental_treatment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // update The Text for the Crypto Title//

        //Sets up the text field which will display the Crypto Data//
        tvMentalTreatmentTitle = view.findViewById(R.id.tvMentalTreatmentTitle);
        tvMentalTreatment = view.findViewById(R.id.tvMentalTreatment);


        //Sets up the button for possible deletion of the crypto//
       Button btnSaveMental = view.findViewById(R.id.btsaveMentalTreatment);
        btnSaveMental.setOnClickListener(this);



        //Gets the crypto data, if it is in the database or not//
        stockRepository.getStockDataFromDB(mCryptoName).observe(getViewLifecycleOwner(), new Observer<List<StocksList>>() {
            @Override
            public void onChanged(List<StocksList> newStocksLists) {
                if (newStocksLists.size() > 0) {
                    System.out.println("Already in database");
                    stocksLists.clear();
                    stocksLists.addAll(newStocksLists);


                } else {
                    // download the Stock Data//
                    downloadStockData();
                }
            }
        });


    }

    //Option to delete Data with a redownload//
    @Override
    public void onClick(View v) {

        TextView etCrypto = getView().findViewById(R.id.tvTreatmentTitle);
        TextView treatmentText = getView().findViewById(R.id.tvTreatment);
        writeFile();
        writeFileName();


    }

    private void writeFileName() {
        String TitleToFile = displaySymptoms.getName() + "\n";
        try {

            FileOutputStream fileOutputStreamTitle = (getContext().openFileOutput("Titles.txt", Context.MODE_APPEND));
            fileOutputStreamTitle.write(TitleToFile.getBytes());
            fileOutputStreamTitle.close();


        }

        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    private void writeFile() {
        String textToFile =  "\n" + displaySymptoms.getName() + "\n" + tvMentalTreatment.getText().toString();

        try {

            FileOutputStream fileOutputStream = (getContext().openFileOutput("SavedMentalTreatments.txt", Context.MODE_APPEND));
            fileOutputStream.write(textToFile.getBytes());
            fileOutputStream.close();

            Toast.makeText(getActivity().getApplicationContext(), "Mental Treatment Saved", Toast.LENGTH_SHORT).show();


                savedMentalTreatments += tvMentalTreatment.getText().toString();
                System.out.println(savedMentalTreatments);

        }

        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    //Download the Stock Data//
    private void downloadStockData() {
        String disease = "";
        disease.replace("", mCryptoName + "/?");
        System.out.println(disease);

        // build URI with the https request from the crypto API//
        Uri uri = Uri.parse("https://api.nhs.uk/mental-health/conditions/");
        Uri.Builder uriBuilder = uri.buildUpon();
        //Add user defined crypto to the path to get the data//
        uriBuilder.appendEncodedPath(mCryptoName);
        uriBuilder.appendEncodedPath("/treatment/?subscription-key=ef2f90a4509245e18f25a441ac2b5b97");
        // create the final URL
        uri = uriBuilder.build();
        System.out.println(uri);



        // use Volley to make the request
        StringRequest request = new StringRequest(
                Request.Method.GET,
                uri.toString(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        //Clears stocksList just in case there is data//
                        stocksLists.clear();

                        // parse the response with a StockApiParser
                        MentalTreatmentAPIParser parser = new MentalTreatmentAPIParser();

                        try {
                            //Convert the parser to JSON//

                            StocksList crypto = parser.convertStockJson(response, mCryptoName);

                            System.out.println(crypto.getDescription());

                            System.out.println("Crypto Saved");
                            //Adds the new List created to stocksLists//
                            displaySymptoms = crypto;
                            System.out.println("Stock list data = " + displaySymptoms);

                            //Stores the List into the database//




                            //Just in case of errors//
                        } catch (JSONException | ParseException e) {
                            Log.d(TAG, e.getLocalizedMessage());
                            // display error message//
                            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.crypto_download_error), Toast.LENGTH_LONG);
                        }

                        //Sets the text on the page to the stocksList infomation for the user to see//
                        String diseaseList = stocksLists.toString();
                        diseaseList = diseaseList.substring(1, diseaseList.length() - 1);
                        if(displaySymptoms.getSymptoms().contains("<b>"))
                        {
                            displaySymptoms.getSymptoms().replace("<b>", "");
                        }
                        tvMentalTreatmentTitle.setText(displaySymptoms.getName());
                        tvMentalTreatment.setText(displaySymptoms.getSymptoms());

                    }
                }, new Response.ErrorListener() {
            //Just in case of errors//
                    @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.crypto_download_error), Toast.LENGTH_LONG);

            }
        });
        // now make the request
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}