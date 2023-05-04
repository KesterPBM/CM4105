package cm3110.gigachadapp;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment implements View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Lists and Text to be Used//
    EditText goalInput;
    private List<String> goalList = new ArrayList<>();
    ListView lv_exampleGoals;
    TextView tfSavedTreatment;
    TextView tfMentalSavedTreatment;
    TextView tfSavedTreatmentLabel;

    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment//

        FragmentActivity contexts = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Set the goalInput and submitGoal to the defined widgets in the xml//


        //Creates a list to add the inputs of goal to//

        tfSavedTreatment = view.findViewById(R.id.tfSavedTreatment);
        tfMentalSavedTreatment=view.findViewById(R.id.tfSavedMentalTreatment);
        tfMentalSavedTreatment.setMovementMethod(new ScrollingMovementMethod());
        tfSavedTreatmentLabel=view.findViewById(R.id.tfsavedTreatmentLabel);
        Button btnDelete = view.findViewById(R.id.btDelete);
        btnDelete.setOnClickListener(this);
        readFile();
        readFileName();
        //Array Adapter to add the data from the user//



        return view;
    }

    public void onClick(View v) {
try {
    TextView etCrypto = getView().findViewById(R.id.tvTreatmentTitle);
    TextView treatmentText = getView().findViewById(R.id.tvTreatment);
    String Clear = "";
    FileOutputStream fileInputStreamTitle= (getContext().openFileOutput("Titles.txt", Context.MODE_PRIVATE));
    FileOutputStream fileInputStreamPhysical= (getContext().openFileOutput("SavedTreatments.txt", Context.MODE_PRIVATE));
    FileOutputStream fileInputStreamMental = (getContext().openFileOutput("SavedMentalTreatments.txt", Context.MODE_PRIVATE));
    fileInputStreamMental.write(Clear.getBytes());

    fileInputStreamMental.close();
    fileInputStreamPhysical.close();
    fileInputStreamTitle.close();
    readFile();
    tfSavedTreatment.setText("");
    tfMentalSavedTreatment.setText("");
}
        catch (FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readFileName(){
        try {
            String allSavedTitles = "";
            String allSavedMentalTreatments = "";
            FileInputStream fileInputStreamTitle = getContext().openFileInput("Titles.txt");

            InputStreamReader inputStreamReaderTitle= new InputStreamReader(fileInputStreamTitle);

            BufferedReader bufferedReaderTitle = new BufferedReader(inputStreamReaderTitle);

            StringBuffer stringBufferTitle = new StringBuffer();

            String lines;
            while ((lines = bufferedReaderTitle.readLine()) !=null)
            {
                stringBufferTitle.append(lines + "\n");
            }

            System.out.println(allSavedTitles);
            allSavedTitles = stringBufferTitle.toString();


            tfSavedTreatmentLabel.setText(allSavedTitles);

        }
        catch (FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readFile() {
        try {
            System.out.println("Testing is stuff is saving: " + tfSavedTreatment.getText().toString());
            String allSavedTreatments = "";
            String allSavedMentalTreatments = "";
            FileInputStream fileInputStreamPhysical = getContext().openFileInput("SavedTreatments.txt");
            FileInputStream fileInputStreamMental = getContext().openFileInput("SavedMentalTreatments.txt");

            InputStreamReader inputStreamReaderPhysical = new InputStreamReader(fileInputStreamPhysical);
            InputStreamReader inputStreamReaderMental = new InputStreamReader(fileInputStreamMental);

            BufferedReader bufferedReaderMental = new BufferedReader(inputStreamReaderMental);
            BufferedReader bufferedReaderPhysical = new BufferedReader(inputStreamReaderPhysical);

            StringBuffer stringBufferMental = new StringBuffer();
            StringBuffer stringBufferPhyscial = new StringBuffer();

            String lines;
            while ((lines = bufferedReaderPhysical.readLine()) !=null)
            {
                stringBufferPhyscial.append(lines + "\n");
            }
            while ((lines = bufferedReaderMental.readLine()) !=null)
            {
                stringBufferMental.append(lines + "\n");
            }
            System.out.println(allSavedTreatments);
            allSavedTreatments = stringBufferPhyscial.toString();
            allSavedMentalTreatments = stringBufferMental.toString();


            tfSavedTreatment.setText(allSavedTreatments);
            tfMentalSavedTreatment.setText(allSavedMentalTreatments);
            tfSavedTreatment.setMovementMethod(new ScrollingMovementMethod());
            tfMentalSavedTreatment.setMovementMethod(new ScrollingMovementMethod());

        }
        catch (FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }




}
