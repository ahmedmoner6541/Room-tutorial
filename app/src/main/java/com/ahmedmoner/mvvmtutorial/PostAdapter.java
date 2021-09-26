package com.ahmedmoner.mvvmtutorial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostVH> {
    private static final String TAG = "PostAdapter";
    ArrayList<Post> arrayList = new ArrayList<>();
    Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostVH holder, int position) {

        holder.name.setText(arrayList.get(position).getUserName());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.disc.setText(arrayList.get(position).getDisc());

        Log.d(TAG, "onBindViewHolder: " + arrayList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setlist(ArrayList<Post> posts) {
        this.arrayList = posts;
    }

    public class PostVH extends RecyclerView.ViewHolder {
        TextView name, title, disc;

        public PostVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.post_name);
            title = itemView.findViewById(R.id.post_title);
            disc = itemView.findViewById(R.id.post_description);
        }
    }
}
