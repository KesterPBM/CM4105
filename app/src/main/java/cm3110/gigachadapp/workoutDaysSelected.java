package cm3110.gigachadapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link workoutDaysSelected#newInstance} factory method to
 * create an instance of this fragment.
 */
public class workoutDaysSelected extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EXERCISE_SEARCH = "param1";


    //Variable from Testing with the Workout API which ultimately failed//
    private String exerciseSearch;

    //Lists being Used//
    private List<String> exerciseList = new ArrayList<>();
    ListView lv_exerciseList;
    public workoutDaysSelected() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment workoutDaysSelected.
     */
    // TODO: Rename and change types and number of parameters
    public static workoutDaysSelected newInstance(String param1) {
        workoutDaysSelected fragment = new workoutDaysSelected();
        Bundle args = new Bundle();
        args.putString(ARG_EXERCISE_SEARCH, fragment.exerciseSearch);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exerciseSearch = getArguments().getString(ARG_EXERCISE_SEARCH);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ExerciseView = inflater.inflate(R.layout.fragment_workout_days_selected, container, false);

        //Creates the List//

        //Creates Array adapter to fill list in with data, then fills it in with setAdapter//


        return ExerciseView;
    }




}
