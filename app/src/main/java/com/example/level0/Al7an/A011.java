package com.example.level0.Al7an;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.level0.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class A011 extends Fragment {
 //   boolean play=true;
   // boolean puse=false;

    private AdView mAdView;
    boolean play=true;
    boolean puse=false;

    public static ScrollView scrollView;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static SeekBar seekBar;
    boolean wasPlaying = false;
    int fprograss;


    public static double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private TextView timeend_011,timestart_011;
    public static int oneTimeOnly = 0;
    public A011() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a011, container, false);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MobileAds.initialize(getContext(), "ca-app-pub-9066719717930706~6805817120");

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Typeface typeface=Typeface.createFromAsset( getActivity().getAssets(),"Avva_Shenouda.ttf" );
        TextView textView=view.findViewById( R.id.COPTIC_A011 );
        textView.setTypeface( typeface );


        chang_play(play,puse);

        scrollView=view.findViewById(R.id.scrollView2);



        FloatingActionButton play=view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chang_play(false,true);

                if (wasPlaying==true){
                    mediaPlayer.start();
                }
                else {
                    Toast.makeText(getActivity(), " لم يتم اضافة صوت هذا اللحن ", Toast.LENGTH_SHORT).show();
                    chang_play(true,false);
                }

            }
        });

        FloatingActionButton puse=view.findViewById(R.id.puse);
        puse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chang_play(true,false);
                mediaPlayer.pause();
            }
        });




        seekBar=view.findViewById(R.id.seekBar_A011);

        final TextView Ta=view.findViewById(R.id.ARAB_A011);
        final TextView TC=view.findViewById(R.id.COPTIC_A011);
        final TextView TCA=view.findViewById(R.id.Ca_A011);
        timestart_011 =view.findViewById(R.id.timestart_011);
        timeend_011 =view.findViewById(R.id.timeend_011);
        seekBar=view.findViewById(R.id.seekBar_A011);
        Spinner spinner=view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (view != null) {
                    if (i == 0) {
                        Ta.setText(R.string.oon_A011a);
                        TC.setText(R.string.oon_A011c);
                        TCA.setText(R.string.oon_A011ca);

                        wasPlaying = true;
                        scrollView.smoothScrollTo(0, 0);
                        mediaPlayer.stop();
                        chang_play(true, false);
                          mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ooneaton);
                          seekBar.setMax(mediaPlayer.getDuration());

                        time(mediaPlayer);

                    } else if (i == 1) {
                        Ta.setText(R.string.mrdengel1_A011a);
                        TC.setText("");
                        TCA.setText("");

                        wasPlaying=true;
                        scrollView.smoothScrollTo(0, 0);
                        mediaPlayer.stop();
                        chang_play(true,false);
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.nahno011);
                       seekBar.setMax(mediaPlayer.getDuration());

                        time(mediaPlayer);

                    } else if (i == 2) {
                        Ta.setText(R.string.madi71_A011a);
                        TC.setText("");
                        TCA.setText("");

                        wasPlaying=true;
                        scrollView.smoothScrollTo(0, 0);
                        mediaPlayer.stop();
                        chang_play(true,false);
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.el3oleka);
                      seekBar.setMax(mediaPlayer.getDuration());

                        time(mediaPlayer);


                    } else {
                        Ta.setText(R.string.mrdengel2_A011a);
                        TC.setText("");
                        TCA.setText("");

                        wasPlaying=true;
                        scrollView.smoothScrollTo(0, 0);
                        mediaPlayer.stop();
                        chang_play(true,false);
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.maradelengil01);
                       seekBar.setMax(mediaPlayer.getDuration());

                        time(mediaPlayer);


                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        try {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ooneaton);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
                fprograss=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(fprograss);
            }
        });
    }

    @SuppressLint("RestrictedApi")
    public void chang_play(boolean play, boolean puse){
        FloatingActionButton Play=getView().findViewById(R.id.play);
        FloatingActionButton Puse=getView().findViewById(R.id.puse);

        if(puse==false&&play==true){
            Play.setVisibility(View.VISIBLE);
            Puse.setVisibility(View.INVISIBLE);
        }
        else if (puse==true&&play==false){
            Play.setVisibility(View.INVISIBLE);
            Puse.setVisibility(View.VISIBLE);
        }
    }


    private void  time (MediaPlayer media){

        finalTime = media.getDuration();
        startTime = media.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekBar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

        timeend_011.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );

        timestart_011.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );

        seekBar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime,100);

            }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            timestart_011.setText(String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);


        }
    };



}