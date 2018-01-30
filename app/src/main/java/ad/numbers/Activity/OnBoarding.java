package ad.numbers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;

import ad.numbers.OnBoarding.OnBoardingFragment;
import ad.numbers.OnBoarding.OnBoardingFragmentAdapter;
import ad.numbers.R;

public class OnBoarding extends FragmentActivity {


    private OnBoardingFragmentAdapter mOnBoardingFragmentAdapter;
    private ViewPager mViewPager;
    private ExtensiblePageIndicator extensiblePageIndicator;
    Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        PageIndicator();

        proceed = (Button) findViewById(R.id.login_button);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }


    private void PageIndicator() {

        extensiblePageIndicator = (ExtensiblePageIndicator) findViewById(R.id.flexibleIndicator);
        mOnBoardingFragmentAdapter = new OnBoardingFragmentAdapter(getSupportFragmentManager());

        mOnBoardingFragmentAdapter.addFragment(
                OnBoardingFragment.newInstance(R.color.frag1, R.drawable.splashscreen, R.string.text1));
        mOnBoardingFragmentAdapter.addFragment(
                OnBoardingFragment.newInstance(R.color.frag2, R.drawable.menu, R.string.text2));
        mOnBoardingFragmentAdapter.addFragment(
                OnBoardingFragment.newInstance(R.color.frag2, R.drawable.input, R.string.text3));
        mOnBoardingFragmentAdapter.addFragment(
                OnBoardingFragment.newInstance(R.color.frag2, R.drawable.favorites, R.string.text4));


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mOnBoardingFragmentAdapter);
        extensiblePageIndicator.initViewPager(mViewPager);


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}


