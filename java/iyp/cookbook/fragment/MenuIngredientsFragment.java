package iyp.cookbook.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import iyp.cookbook.IngredientAdapter;
import iyp.cookbook.R;
import iyp.cookbook.listing.IngredientData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuIngredientsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuIngredientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuIngredientsFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private List<IngredientData> ingredient= Collections.emptyList();
    private OnFragmentInteractionListener mListener;
    private int imageId;
    private ImageView image;
    private ViewPager viewpager;
    public void setIngredient(List<IngredientData> ingredient){
        this.ingredient=ingredient;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setViewpager(ViewPager viewpager) {
        this.viewpager = viewpager;
    }

    public MenuIngredientsFragment() {
        // Required empty public constructor
    }
    public static MenuIngredientsFragment newInstance(int imageId,List<IngredientData>  ingredient,ViewPager viewpager) {
        MenuIngredientsFragment fragment = new MenuIngredientsFragment();
        fragment.setIngredient(ingredient);
        fragment.setImageId(imageId);
        fragment.setViewpager(viewpager);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO incomplete button func, image hover(xml & java)
        View v=inflater.inflate(R.layout.fragment_menu_ingrendients, container, false);
        image=(ImageView)v.findViewById(R.id.menuImageIngredient);
        image.setImageResource(imageId);
        ImageButton cook=(ImageButton)v.findViewById(R.id.ingredientOrder),
                start=(ImageButton)v.findViewById(R.id.ingredientStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(IngredientData i:ingredient){
                    if(i.clicked==false){
                        Toast.makeText(getActivity().getApplicationContext(),"incomplete Ingredients",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                viewpager.setCurrentItem(2);
            }
        });
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make order activity
            }
        });
        RecyclerView ingredientlist=(RecyclerView)v.findViewById(R.id.IngredientList);
        ingredientlist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ingredientlist.setAdapter(new IngredientAdapter(ingredient,getContext()));
        return v ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
