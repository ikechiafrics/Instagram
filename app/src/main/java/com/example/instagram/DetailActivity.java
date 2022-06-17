package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    Post post;
    TextView tvProfileName;
    ImageView ivImage;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvProfileName = findViewById(R.id.tvProfileName);
        ivImage = findViewById(R.id.ivImage);
        tvTime = findViewById(R.id.tvTime);

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        ParseFile image = post.getImage();
        tvProfileName.setText(post.getUser().getUsername());
        Glide.with(this).load(image.getUrl()).into(ivImage);

        Date createdAt = post.getCreatedAt();
        String timeAgo = post.calculateTimeAgo(createdAt);
        tvTime.setText(timeAgo);
    }
}