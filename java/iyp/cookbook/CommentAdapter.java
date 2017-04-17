package iyp.cookbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import iyp.cookbook.listing.CommentData;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {


    List<CommentData> horizontalList = Collections.emptyList();
    Context context;
    private int lastPos=-1;
    public CommentAdapter(List<CommentData> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageicon;
        TextView comment,username,star;
        public MyViewHolder(View view) {
            super(view);
            username=(TextView) view.findViewById(R.id.commentUsername);
            imageicon=(ImageView) view.findViewById(R.id.commentImage);
            comment=(TextView) view.findViewById(R.id.commentText);
            star=(TextView) view.findViewById(R.id.commentStar);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.username.setText(horizontalList.get(position).username);
        holder.comment.setText(horizontalList.get(position).comment);
        holder.imageicon.setImageResource(horizontalList.get(position).imageID);
        holder.star.setText(horizontalList.get(position).star+" Star");
        if(position >lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.recycleanim);
            holder.itemView.startAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}