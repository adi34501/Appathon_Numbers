package ad.numbers.Fragments;

/**
 * Created by ADITYA on 25-09-2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ad.numbers.Activity.Random;
import ad.numbers.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class OneFragment extends Fragment {


    String inpu;
    @Bind(R.id.textViewName)
    TextView txt;
    @Bind(R.id.textViewName1)
    TextView txt2;
    @Bind(R.id.textViewName3)
    TextView txt3;
    @Bind(R.id.textViewName4)
    TextView txt4;


    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                populate((String) txt.getText());

            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                populate((String) txt2.getText());

            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date((String) txt3.getText());

            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                populate((String) txt4.getText());

            }
        });


        return view;
    }


    private void populate(final String txt) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);

        builder.setTitle("Provide Input");
        final EditText input = new EditText(getActivity());
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                inpu = input.getText().toString();

                if (!validate()) {
                    onLoginFailed();
                } else {
                    Intent in = new Intent(getContext(), Random.class);
                    in.putExtra("Category", txt);
                    in.putExtra("Input", inpu);
                    startActivity(in);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }


    private void date(final String txt) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle("Provide Input(month/date)");

        final EditText input = new EditText(getActivity());
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                inpu = input.getText().toString();
                if (!validatedate()) {
                    onLoginFailed();
                } else {
                    Intent in = new Intent(getContext(), Random.class);
                    in.putExtra("Category", txt);
                    in.putExtra("Input", inpu);
                    startActivity(in);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();


    }

    public boolean validatedate() {
        boolean valid = false;
        String regex = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";
        System.out.println("Regex : " + regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inpu);


        if (inpu.length() > 0 & matcher.matches()) {
            String month = matcher.group(1);
            String day = matcher.group(2);
            if (day.equals("31") &&
                    (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11") || month.equals("04") || month.equals("06") ||
                            month.equals("09"))) {
                return valid;
            }
            if ((month.equals("2") || month.equals("02")) && (day.equals("30") || day.equals("31"))) {
                return valid;
            }
            valid = true;
        } else {
            Log.e("error", "ERROR");
        }
        return valid;
    }


    public boolean validate() {
        boolean valid = false;
        String reg = "^\\d+$";

        Pattern pattern2 = Pattern.compile(reg);
        Matcher matcher2 = pattern2.matcher((inpu));

        if (inpu.length() > 0 & matcher2.matches()) {
            valid = true;
        } else {
            Log.e("error", "EROROR");
        }
        return valid;
    }


    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Provide Valid Input", Toast.LENGTH_LONG).show();
    }

}



