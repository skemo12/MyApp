package com.example.myapplication.ui.dashboard;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView wordToGuess = binding.wordToGuess;
        EditText inputText = binding.textInput;
        Button submit = binding.submit;
        submit.setVisibility(View.INVISIBLE);
        wordToGuess.setVisibility(View.INVISIBLE);
        inputText.setVisibility(View.INVISIBLE);

        String S1 = "Te iubesc foarte mult";
        String S2 = "Nu mai ma necaji!";
        String S3 = "Nu mai pot fara tine";
        String S4 = "Mi-e dor de tine!";
        String S5 = "Mi-e dor de tine!";

        String win = "Ai castigat iubire!\n ❤";
        String notYet = "Mai incearca iubire!";

        ArrayList<String> solutions = new ArrayList<>();
        solutions.add(S1);
        solutions.add(S2);
        solutions.add(S3);
        solutions.add(S4);

        Button beginGame = binding.beginGame;
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("T_ i__e__ f____e mu__");
        listOfWords.add("__ m__ ma n_c_j_!");
        listOfWords.add("_u __i p__ f___ t___");
        listOfWords.add("M_-e d_r d_ _in_!");



        beginGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int)(Math.random() * listOfWords.size());
                wordToGuess.setText(listOfWords.get(index));
                wordToGuess.setVisibility(View.VISIBLE);
                inputText.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                Handler handler = new Handler();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Editable input = inputText.getText();
                        if (input.toString().equalsIgnoreCase(solutions.get(index))){
                            wordToGuess.setText(win);
                            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.victory);
                            mp.start();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    submit.setVisibility(View.INVISIBLE);
                                    wordToGuess.setVisibility(View.INVISIBLE);
                                    inputText.setText("");
                                    inputText.setVisibility(View.INVISIBLE);
                                    mp.stop();
                                }
                            },5500);
                        } else {
                            wordToGuess.setText(notYet);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    wordToGuess.setText(listOfWords.get(index));
                                }
                            },2500);
                        }
                    }
                });

            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}