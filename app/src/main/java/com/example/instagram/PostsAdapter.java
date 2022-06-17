package com.example.instagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.lang.reflect.Parameter;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        private ImageView ivProfilePic;
        private ImageView btnLike;
        private TextView LikeCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            btnLike = itemView.findViewById(R.id.btnLike);
            LikeCount = itemView.findViewById(R.id.LikeCount);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvCreatedAt.setText((post.getCreatedAt().toString()));
            LikeCount.setText(String.valueOf(post.getLikedBy().size()));
            ParseFile profilePic = post.getUser().getParseFile("profile_picture");
            if (ivProfilePic != null) {
                Glide.with(context).load(profilePic.getUrl()).transform(new RoundedCorners(90)).into(ivProfilePic);
            }
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).transform(new RoundedCorners(90)).into(ivImage);
            }
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                    context.startActivity(i);
                }
            });

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> likeBy = post.getLikedBy();
                    String likeText;
                    String likeCount;

                    if(!likeBy.contains(ParseUser.getCurrentUser().getObjectId())) {
                        likeBy.add(ParseUser.getCurrentUser().getObjectId());
                        post.setLikedBy(likeBy);
                        Drawable newImage = context.getDrawable(R.drawable.ic_baseline_favorite_24);
                        btnLike.setImageDrawable(newImage);
                        likeText = String.valueOf(post.likeCountDisplay());
                        likeCount = String.valueOf(likeBy.size());
                        LikeCount.setText(likeCount+likeText);
                    }
                    else{
                        likeBy.remove(ParseUser.getCurrentUser().getObjectId());
                        post.setLikedBy(likeBy);
                        Drawable newImage = context.getDrawable(R.drawable.ic_baseline_favorite_border_24);
                        btnLike.setImageDrawable(newImage);
                        likeText = String.valueOf(post.likeCountDisplayText());
                        likeCount = String.valueOf(likeBy.size());
                        LikeCount.setText("liked");
                    }
                    post.saveInBackground();
                }
            });
        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}
