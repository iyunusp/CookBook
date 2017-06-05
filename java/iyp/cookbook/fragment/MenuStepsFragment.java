package iyp.cookbook.fragment;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iyp.cookbook.R;
import iyp.cookbook.listing.StepData;


public class MenuStepsFragment extends Fragment implements SensorEventListener{
    private OnFragmentInteractionListener mListener;
    private TextView steplocker;
    private VerticalViewPager StepUI;
    private SectionsPagerAdapter section;
    private Sensor sense;
    private SensorManager senseMan;
    private long timestamp;
    private int positem=0;
    private boolean senseAvail=false;
    List<StepData> steps;
    private void testsensor(){
        try {
            senseMan = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            sense = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            timestamp= System.currentTimeMillis();
            senseAvail=true;
        }catch (Exception e){
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        senseMan.registerListener(this, sense, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    public void onPause(){
        super.onPause();
        senseMan.unregisterListener(this);
    }
    public MenuStepsFragment(){

    }
    public void setSteps(List<StepData> steps){
        this.steps=steps;
    }
    public void enableStep(){
        steplocker.setVisibility(View.GONE);
        StepUI.setVisibility(View.VISIBLE);
    }
    public static MenuStepsFragment newInstance(List<StepData> steps) {
        MenuStepsFragment fragment = new MenuStepsFragment();
        fragment.setSteps(steps);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the customrating for this fragment
        View v=inflater.inflate(R.layout.fragment_menu_steps, container, false);
        steplocker=(TextView)v.findViewById(R.id.stepLocker);
        section= new SectionsPagerAdapter();
        StepUI=(VerticalViewPager)v.findViewById(R.id.stepPager);
        StepUI.setAdapter(section);
        for(StepData step:steps){
            FrameLayout v0 = (FrameLayout) inflater.inflate (R.layout.steplist, null);
            TextView desc=(TextView) v0.findViewById(R.id.stepDesc);
            desc.setText(step.desc);
            if(steps.indexOf(step)%2==0){
                v0.setBackgroundColor(Color.RED);
            }else{
                v0.setBackgroundColor(Color.WHITE);
            }
            section.addView (v0, steps.indexOf(step));
        }
        testsensor();
        section.notifyDataSetChanged();
        StepUI.setVisibility(View.INVISIBLE);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(senseAvail){
            if((sensorEvent.timestamp)-timestamp<120000000) {//good enough?
                StepUI.setCurrentItem(positem);
                positem++;
                if(positem>steps.size()) positem=0;
            }
            timestamp=sensorEvent.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class SectionsPagerAdapter extends PagerAdapter {
        //https://stackoverflow.com/questions/13664155/dynamically-add-and-remove-view-to-viewpager without remove
        private ArrayList<View> views = new ArrayList<View>();
        @Override
        public int getItemPosition (Object object)
        {
            int index = views.indexOf (object);
            if (index == -1)
                return POSITION_NONE;
            else
                return index;
        }
        @Override
        public Object instantiateItem (ViewGroup container, int position)
        {
            View v = views.get (position);
            container.addView (v);
            return v;
        }
        @Override
        public void destroyItem (ViewGroup container, int position, Object object){
            container.removeView (views.get (position));
        }
        @Override
        public int getCount() {
            return views.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        public int addView (View v){
            return addView (v, views.size());
        }
        public int addView (View v, int position){
            views.add (position, v);
            return position;
        }
        public View getView (int position){
            return views.get (position);
        }
    }
}
