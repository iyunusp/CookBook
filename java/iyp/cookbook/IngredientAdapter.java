package iyp.cookbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import iyp.cookbook.listing.IngredientData;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {


    List<IngredientData> horizontalList = Collections.emptyList();
    Context context;
    public IngredientAdapter(List<IngredientData> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageicon;
        TextView title;
        public MyViewHolder(View view) {
            super(view);
            title=(TextView) view.findViewById(R.id.IngredientText);
            imageicon=(ImageView) view.findViewById(R.id.IngredientIcon);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.title.setText(horizontalList.get(position).Title);
        holder.imageicon.setImageResource(R.drawable.setting);
        holder.imageicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO add hover info with .setImageResource(horizontalList.get(position).imageID);
                if(!horizontalList.get(position).clicked) {
                    holder.imageicon.setImageResource(R.drawable.bookmark);
                    horizontalList.get(position).clicked=true;
                }
                else {
                    holder.imageicon.setImageResource(R.drawable.setting);
                    horizontalList.get(position).clicked=false;
                }
            }
        });

    }
    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}