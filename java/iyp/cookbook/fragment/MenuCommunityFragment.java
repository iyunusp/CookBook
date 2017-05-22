package iyp.cookbook.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import iyp.cookbook.adapter.CommentAdapter;
import iyp.cookbook.R;
import iyp.cookbook.listing.CommentData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuCommunityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuCommunityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuCommunityFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private List<CommentData> commen;
    private int ImageId;
    private float rate=(float)0.0;
    private String Username;
    private Button commentButton;
    private EditText commentText;
    private RecyclerView.Adapter adapter;
    public void setCommen(List<CommentData> commen) {
        this.commen = commen;
    }
    public void setImageId(int imageId) {
        ImageId = imageId;
    }
    public MenuCommunityFragment() {
        // Required empty public constructor
    }
    public void setUsername(String username) {
        Username = username;
    }
    public static MenuCommunityFragment newInstance(String Username,int ImageId, List<CommentData> comment) {
        MenuCommunityFragment fragment = new MenuCommunityFragment();
        fragment.setImageId(ImageId);
        fragment.setCommen(comment);
        fragment.setUsername(Username);
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
        final View v=inflater.inflate(R.layout.fragment_menu_community, container, false);
        TextView user=(TextView)v.findViewById(R.id.userCommentName);
        user.setText(Username);
        ImageView image=(ImageView)v.findViewById(R.id.userCommentImage);
        image.setImageResource(ImageId);
        commentText=(EditText)v.findViewById(R.id.userCommentText);
        RecyclerView comlist=(RecyclerView)v.findViewById(R.id.commentList);
        comlist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter=new CommentAdapter(commen,getContext());
        comlist.setAdapter(adapter);
        final RatingBar rating=(RatingBar)v.findViewById(R.id.userCommentStar);
        commentButton=(Button)v.findViewById(R.id.userCommentComment);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CommentData com:commen){
                    if(com.username.equals(Username)){
                        Toast.makeText(getActivity().getApplicationContext(),"You've Comment this Recipe",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                hideKeyboard(view);
                if(!Username.equals("GUEST") && commentText.getText().toString().length()>1){
                    commen.add(new CommentData(Username,ImageId,commentText.getText().toString(),rating.getRating()));
                    adapter.notifyDataSetChanged();
                    communicate cm=(communicate) getActivity();
                    cm.sendData(commen);
                }else if(commentText.getText().toString().length()<=1) {
                    Toast.makeText(getActivity().getApplicationContext(),"Comment must be more than 2 character",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"You must Login First",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
