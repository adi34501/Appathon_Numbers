package ad.numbers.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import ad.numbers.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Random extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView txtString;
    TextView txtStringhead;
    String Category;
    String url;
    String fact;
    ProgressDialog mProgress;
    private TextToSpeech tts;
    ArrayList<String> scores;
    Set<String> tasksSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        if (isOnline() == false) {
            for (int i = 0; i < 3; i++) {
                Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            }
        }
        tts = new TextToSpeech(this, this);
        init();
        Intent in = getIntent();
        String Input = in.getExtras().getString("Input");

        ImageButton speaker = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton fav = (ImageButton) findViewById(R.id.fav);
        txtString = (TextView) findViewById(R.id.textString);
        txtStringhead = (TextView) findViewById(R.id.textString2);
        Category = getIntent().getExtras().getString("Category");
        tasksSet = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getStringSet("tasks_set", new HashSet<String>());
//        tasksSet.clear();


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFav();
            }

        });
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

        if (Input != null) {
            url = "http://numbersapi.com/" + Input + "/" + Category.toLowerCase() + "";
        } else {
            url = "http://numbersapi.com/random/" + Category.toLowerCase() + "";
        }

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {

        loadprogressbar();
        Intent in = getIntent();
        String Input = in.getExtras().getString("Input");

        txtString = (TextView) findViewById(R.id.textString);
        Category = getIntent().getExtras().getString("Category");


        if (Input != null) {
            url = "http://numbersapi.com/" + Input + "/" + Category.toLowerCase() + "";
        } else {
            url = "http://numbersapi.com/random/" + Category.toLowerCase() + "";
        }
    }

    private void loadprogressbar() {
        mProgress = new ProgressDialog(Random.this);
        mProgress.setIndeterminate(true);
        mProgress.setCancelable(false);
        mProgress.setMessage("Fetching Facts...");
        mProgress.show();
    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mProgress.dismiss();
                final String myResponse = response.body().string();

                Random.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtStringhead.setText(Category);
                        YoYo.with(Techniques.DropOut)
                                .duration(1500)
                                .playOn(findViewById(R.id.textString2));
                        txtString.setText(myResponse);
                        fact = myResponse;
                    }
                });
            }
        });
    }


    private void speakOut() {
        tts.speak(fact, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void addToFav() {

        Toast.makeText(getApplicationContext(), "Fact Added To Favorites", Toast.LENGTH_LONG).show();


        if (tasksSet == null) {
            scores = new ArrayList<String>();
            scores.add(fact);
            Set<String> tasksSet2 = new HashSet<String>(scores);
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .edit()
                    .putStringSet("tasks_set", tasksSet2)
                    .commit();
        } else {
            List<String> tasksList = new ArrayList<String>(tasksSet);
            tasksList.add(fact);
            Set<String> tasksSet2 = new HashSet<String>(tasksList);
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .edit()
                    .putStringSet("tasks_set", tasksSet2)
                    .commit();

        }

    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
                speakOut();
            }
        } else {
        }
    }
}