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
    TextView ivProfileName;
    ImageView tvImage;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivProfileName = findViewById(R.id.ivProfileName);
        tvImage = findViewById(R.id.tvImage);
        tvTime = findViewById(R.id.tvTime);

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        ParseFile image = post.getImage();
        ivProfileName.setText(post.getUser().getUsername());
        Glide.with(this).load(image.getUrl()).into(tvImage);

        Date createdAt = post.getCreatedAt();
        String timeAgo = post.calculateTimeAgo(createdAt);
        tvTime.setText(timeAgo);
    }
}