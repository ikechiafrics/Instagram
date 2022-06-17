//package com.example.instagram.fragments;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.instagram.Post;
//import com.example.instagram.R;
//import com.parse.ParseFile;
//
//import org.parceler.Parcels;
//import java.util.Date;
//
//public class DetailFragment extends Fragment {
//    Post post;
//    TextView ivProfileName;
//    ImageView tvImage;
//    TextView tvTime;
//
//
//
//    public DetailFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_detail);
//
//            ivProfileName = view.findViewById(R.id.ivProfileName);
//            tvImage = view.findViewById(R.id.tvImage);
//            tvTime = view.findViewById(R.id.tvTime);
//
//            post = Parcels.unwrap(getIntent.getParcelableExtra(Post.class.getSimpleName()));
//
//            ParseFile image = post.getImage();
//            ivProfileName.setText(post.getUser().getUsername());
//            Glide.with(this).load(image.getUrl()).into(tvImage);
//
//            Date createdAt = post.getCreatedAt();
//            String timeAgo = post.calculateTimeAgo(createdAt);
//            tvTime.setText(timeAgo);
//            querypost();
//        }
//
//
//    private void setContentView(int activity_detail) {
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
//    }
//}