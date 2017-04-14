package iyp.cookbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

import iyp.cookbook.account.Account;
import iyp.cookbook.listing.MenuData;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {


    List<MenuData> horizontalList = Collections.emptyList();
    Context context;
    Account account;

    public MenuAdapter(List<MenuData> horizontalList, Context context, Account account) {
        this.horizontalList = horizontalList;
        this.context = context;
        this.account=account;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageicon;
        TextView title;
        public MyViewHolder(View view) {
            super(view);
            title=(TextView) view.findViewById(R.id.menumidname);
            imageicon=(ImageView) view.findViewById(R.id.menumidimage);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menumidlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.title.setText(horizontalList.get(position).Title);
        holder.imageicon.setImageResource(horizontalList.get(position).imageID);
        holder.imageicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,RecipeItemView.class);
                intent.putExtra("menu",horizontalList.get(position));
                intent.putExtra("user",account);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}