package com.example.iroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.iroidapp.adapters.CategoryRecyclerViewAdapter;
import com.example.iroidapp.adapters.FragmentPagerAdapter;
import com.example.iroidapp.models.Banners;
import com.example.iroidapp.models.Categories;
import com.example.iroidapp.models.Data;
import com.example.iroidapp.util.ViewPagerItemFragment;
import com.example.iroidapp.viewmodel.HomeScreenViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {

    private static final String TAG = "HomeScreenActivity";
    HomeScreenViewModel homeScreenViewModel;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<String> mCategoryNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);

        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        homeScreenViewModel.callIroidResponseApi();
        subscribeObservers();

    }

    private void initRecyclerView(Categories[] categories){
        Log.d(TAG, "initRecyclerView: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(layoutManager);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categories, this);
        recyclerView.setAdapter(adapter);
    }

    private void initBanners(Banners[] banners) {
        ArrayList<Fragment> fragments = new ArrayList<>();
                for(Banners banner: banners){
                    ViewPagerItemFragment fragment = ViewPagerItemFragment.getInstance(banner);
                    fragments.add(fragment);
                }
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    public void subscribeObservers(){

        homeScreenViewModel.getmData().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                Log.d(TAG, "onChanged: Data changed ");
                if(data != null){
                    Banners[] banners = data.getBanners();
                    Categories[] categoriesArrayList = data.getCategories();
                    initBanners(banners);
                    initRecyclerView(categoriesArrayList);
                }
            }
        });
    }
}
