package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.animations.DescriptionAnimation;
import com.glide.slider.library.indicators.PagerIndicator;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.glide.slider.library.tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String FNAME_KEY = "fname_key";
    public static final String LNAME_KEY = "lname_key";
    List<Banner> bannerList = new ArrayList<>();

    SharedPreferences sharedpreferences;
    String fname, lname;
    SliderLayout sliderLayout;
    PagerIndicator pagerIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sliderLayout = findViewById(R.id.sliderLayout);
        pagerIndicator = findViewById(R.id.custom_indicator);
        getBannerscreen();
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        fname = sharedpreferences.getString("fname_key", null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fname != null) {
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);
        }
    }

    private void getBannerscreen() {
        Banner banner=new Banner(R.drawable.face,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTCWh6aOBxTphDwJqZZOQ9sBrPT_irgf5VEA&usqp=CAU");
        Banner banner1=new Banner(R.drawable.face,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrnzIGwgNmnz9dlN8XmPVQYY9gDFJLSQIfrw&usqp=CAU");
        Banner banner2=new Banner(R.drawable.face,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaJp3srPEyU-BZDTeQDIxkxNnSBw5GqDtAcA&usqp=CAU");
        Banner banner3=new Banner(R.drawable.face,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqKKm5gam8GYryGzdGBqSVQsDr-ybO4CeYo4UV5JLf_Lzg-sAKGVlvhqepigx5WzBbmvc&usqp=CAU");
        Banner banner4=new Banner(R.drawable.face,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm3CqE2iZcQ1q4GyWxN_Y_Ij386nfkS_4pRg&usqp=CAU");
         bannerList.add(banner);
         bannerList.add(banner1);
         bannerList.add(banner2);
         bannerList.add(banner3);
         bannerList.add(banner4);
        showOnLineDataBanner(bannerList);
    }

    private void showOnLineDataBanner(List<Banner> bannerList) {
        for (int i = 0; i < bannerList.size(); i++) {
            TextSliderView sliderView = new TextSliderView(SplashScreen.this);
            final int finalI = i;
            sliderView.image(bannerList.get(i).getImageurl()).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Banner data = bannerList.get(finalI);
                }
            });

            sliderLayout.addSlider(sliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setPresetTransformer("test");
        sliderLayout.setCustomIndicator(pagerIndicator);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        sliderLayout.stopCyclingWhenTouch(false);
    }

    public void signup(View view) {
            Intent i = new Intent(SplashScreen.this, Signup.class);
            startActivity(i);
            finish();
    }

    public void login(View view) {
        Intent i = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}