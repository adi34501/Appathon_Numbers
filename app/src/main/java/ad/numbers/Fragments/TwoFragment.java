package ad.numbers.Fragments;

/**
 * Created by ADITYA on 25-09-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ad.numbers.Activity.Random;
import ad.numbers.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class TwoFragment extends Fragment {

    @Bind(R.id.textViewName)
    TextView text;
    @Bind(R.id.textViewName1)
    TextView text2;
    @Bind(R.id.textViewName3)
    TextView text3;
    @Bind(R.id.textViewName4)
    TextView text4;


    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Random.class);
                i.putExtra("Category", text2.getText());
                startActivity(i);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Random.class);
                i.putExtra("Category", text3.getText());
                startActivity(i);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Random.class);
                i.putExtra("Category", text4.getText());
                startActivity(i);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Random.class);
                i.putExtra("Category", text.getText());
                startActivity(i);
            }
        });
        return view;
    }


}
