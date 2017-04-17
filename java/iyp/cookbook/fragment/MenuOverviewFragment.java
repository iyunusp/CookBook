package iyp.cookbook.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import iyp.cookbook.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuOverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MenuOverviewFragment extends Fragment{

    private OnFragmentInteractionListener mListener;
    private int imageid, minute;
    private float star=0;
    private String title,desc;
    private ViewPager viewpager;
    private TextView str;
    public MenuOverviewFragment() {
        // Required empty public constructor
    }
    public void setAll(String title,String desc, int imageid, int minute, float star,ViewPager viewpager){
        this.desc = desc;
        this.imageid = imageid;
        this.minute = minute;
        this.star = star;
        this.title = title;
        this.viewpager=viewpager;
    }
    public static MenuOverviewFragment newInstance(String title,String desc, int imageid, int minute, float star,ViewPager viewpager) {
        MenuOverviewFragment fragment = new MenuOverviewFragment();
        fragment.setAll( title, desc,  imageid,  minute,  star,viewpager);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_menu_overview, container, false);
        ImageView image=(ImageView)v.findViewById(R.id.menuImageOverview);
        image.setImageResource(imageid);
        TextView tit=(TextView)v.findViewById(R.id.menuTitleOverview),
                des=(TextView)v.findViewById(R.id.menuTitleDesc),
                min=(TextView)v.findViewById(R.id.menuTitleMinute);
        str=(TextView)v.findViewById(R.id.menuTitleStar);
        ImageButton cook=(ImageButton)v.findViewById(R.id.overviewOrder),
                start=(ImageButton)v.findViewById(R.id.overviewStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(1);
            }
        });
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make order activity
            }
        });
        tit.setText(title);
        updateStar(star);
        min.setText("Estimated : "+minute+" Min");
        des.setText(desc);
        return v;
    }
    public void updateStar(float star){
        this.star=star;
        str.setText(String.format("Rating : %.1f *",star));
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
