package com.example.ilya.smartcap_v31b;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Calendar;
import java.util.HashMap;
import java.lang.*;

public class TodayFragment2 extends Fragment{
    static HashMap<String, HashMap<String, String>> patient = new HashMap<String, HashMap<String, String>>();
    static HashMap<String, String> pill1 = new HashMap<String, String>();
    static HashMap<String, String> pill2 = new HashMap<String, String>();


    public Calendar c = Calendar.getInstance();
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TodayFragment2 newInstance(int sectionNumber) {
        TodayFragment2 fragment = new TodayFragment2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        ProgressBar progress = (ProgressBar) rootView.findViewById(R.id.progressWheel);
        progress.setSecondaryProgress(80);
        addTimeViews(rootView, inflater);
        return rootView;
    }

    private void addTimeViews(View rootView, LayoutInflater timeViewInflater) {
        LinearLayout containerView = (LinearLayout) rootView.findViewById(R.id.today_container);
        pill1.put("02:30pm","1");
        pill1.put("01:30pm","2");
        pill1.put("09:00pm","3");
        pill2.put("01:00pm","1");
        pill2.put("10:00pm","2");
        pill2.put("11:29pm","3");
        patient.put("vitamin1",pill1);
        patient.put("vitamin2",pill2);
        int currenthour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentminute = Calendar.getInstance().get(Calendar.MINUTE);

        for(String key1:patient.keySet()){
            System.out.println(key1);
            View timeView = timeViewInflater.inflate(R.layout.time_view, null);
            TextView timeStamp = (TextView) timeView.findViewById(R.id.time_stamp);
            timeStamp.setText(key1);
            LinearLayout pillListContainer = (LinearLayout) timeView.findViewById(R.id.time_pill_list);

            for(String key2:patient.get(key1).keySet()){
                String timehour=key2.substring(0,2);
                String timeminute=key2.substring(3,5);
                String apm=key2.substring(key2.length()-2,key2.length());
                int hourtime=Integer.valueOf(timehour);
                int minutetime=Integer.valueOf(timeminute);
                if(apm.equals("am")&&(hourtime == 12)){hourtime -=12;}
                if(apm.equals("pm")&&(hourtime != 12)){hourtime+=12;}
                //System.out.println(hourtime);
                //currenthour-=20;
                if(hourtime>currenthour || (hourtime == currenthour && minutetime>=currentminute)){
                    View  pillView = timeViewInflater.inflate(R.layout.pill_list_view, null);
                    TextView pillName = (TextView) pillView.findViewById(R.id.pill_name);
                    pillName.setText(key2);
                    pillName.setTextColor((this.getResources().getColor(R.color.red)));
                    TextView pillCount = (TextView) pillView.findViewById(R.id.pill_count);
                    pillCount.setText(patient.get(key1).get(key2));
                    pillCount.setTextColor((this.getResources().getColor(R.color.red)));
                    pillListContainer.addView(pillView);
                }
                else{
                    View  pillView = timeViewInflater.inflate(R.layout.pill_list_view, null);
                    TextView pillName = (TextView) pillView.findViewById(R.id.pill_name);
                    pillName.setText(key2);
                    TextView pillCount = (TextView) pillView.findViewById(R.id.pill_count);
                    pillCount.setText(patient.get(key1).get(key2));
                    pillListContainer.addView(pillView);
                }
            }
            containerView.addView(timeView);
        }

//        String[] stamps = {"a", "s", "9sM"};
//        String[] pillNames = {"Ps", "Ps B", "PilsC", "Pill D"};
//        String[] pillCounts = {"4", "3", "2121dv", "1"};
//        for (int i = 0; i < 3; i++) {
//            View timeView = timeViewInflater.inflate(R.layout.time_view, null);
//            TextView timeStamp = (TextView) timeView.findViewById(R.id.time_stamp);
//            timeStamp.setText(stamps[i]);
//            LinearLayout pillListContainer = (LinearLayout) timeView.findViewById(R.id.time_pill_list);
//            for (int j = 0; j < 4; j++) {
//                View  pillView = timeViewInflater.inflate(R.layout.pill_list_view, null);
//                TextView pillName = (TextView) pillView.findViewById(R.id.pill_name);
//                pillName.setText(pillNames[j]);
//                TextView pillCount = (TextView) pillView.findViewById(R.id.pill_count);
//                pillCount.setText(pillCounts[j]);
//                pillListContainer.addView(pillView);
//            }
//            containerView.addView(timeView);
//        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((NextActivity) context).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}