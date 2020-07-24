package com.example.iroidapp.util;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iroidapp.R;
import com.example.iroidapp.models.Banners;

public class ViewPagerItemFragment extends Fragment {

    private static final String TAG = "ViewPagerItemFragment";
    private ImageView mImageView;
    private Banners banner;

    public static ViewPagerItemFragment getInstance(Banners banner){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        if(banner != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("banner", banner);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            banner = getArguments().getParcelable("banner");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImageView = view.findViewById(R.id.banner_image);
        initGlide();
    }

    private void initGlide(){
        if(banner != null){
            Log.d(TAG, "initGlide: " + banner.getImage());
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);
            Glide.with(getActivity()).setDefaultRequestOptions(options)
                    .load(banner.getImage())
                    .into(mImageView);
        }
    }
}
