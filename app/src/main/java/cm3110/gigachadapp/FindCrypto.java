package cm3110.gigachadapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindCrypto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindCrypto extends Fragment implements View.OnClickListener {

    public static String ARG_CRYPTO_NAME = "cryptoName";



    public FindCrypto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FindCrypto.
     */
    // TODO: Rename and change types and number of parameters
    public static FindCrypto newInstance() {
        FindCrypto fragment = new FindCrypto();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_crypto, container, false);

        //Creates the button to get the Stock//
        Button btnGetStock = view.findViewById(R.id.btnGetStock);
        btnGetStock.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        //If the button is selected, get the crypto typed in by the user and carry it to the next fragment//
        if (v.getId() == R.id.btnGetStock) {
            // Get the stock entered by the user//
            EditText etCrypto = getView().findViewById(R.id.etEnterStock);
            String crypto = etCrypto.getText().toString();

            ARG_CRYPTO_NAME = "cryptoName";

            // create bundle for the arguments//
            Bundle args = new Bundle();
            args.putString(ARG_CRYPTO_NAME, crypto);


            //Navigates to the next fragment which will display the data//
            Navigation.findNavController(v).navigate(
                    R.id.displayStockData, args);
        }

    }



    }
