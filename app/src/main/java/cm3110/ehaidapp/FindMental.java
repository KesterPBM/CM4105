package cm3110.gigachadapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindMental#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindMental extends Fragment implements View.OnClickListener  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String ARG_CRYPTO_NAME = "cryptoName";

    //Set up Text Field for the Calorie Result at the end//
    public TextView tfCalorieResult;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FindMental() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalorieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindMental newInstance(String param1, String param2) {
        FindMental fragment = new FindMental();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_find_mental, container, false);

        //Creates the button to get the Stock//
        Button btFindMental= view.findViewById(R.id.btgetMental);
        btFindMental.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        //If the button is selected, get the crypto typed in by the user and carry it to the next fragment//
        if (v.getId() == R.id.btgetMental) {
            // Get the stock entered by the user//
            EditText etMental = getView().findViewById(R.id.etEnterMental);
            String crypto = etMental.getText().toString();

            ARG_CRYPTO_NAME = "cryptoName";

            // create bundle for the arguments//
            Bundle args = new Bundle();
            args.putString(ARG_CRYPTO_NAME, crypto);


            //Navigates to the next fragment which will display the data//
            Navigation.findNavController(v).navigate(
                    R.id.displayMentalData, args);
        }

    }



}