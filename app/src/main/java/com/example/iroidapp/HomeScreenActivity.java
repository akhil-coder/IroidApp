package com.example.iroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.iroidapp.adapters.CategoryRecyclerViewAdapter;
import com.example.iroidapp.adapters.FragmentPagerAdapter;
import com.example.iroidapp.adapters.ProductsRecyclerAdapter;
import com.example.iroidapp.models.Banners;
import com.example.iroidapp.models.Categories;
import com.example.iroidapp.models.Data;
import com.example.iroidapp.models.FreshProducts;
import com.example.iroidapp.util.ViewPagerItemFragment;
import com.example.iroidapp.viewmodel.HomeScreenViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {

    private static final String TAG = "HomeScreenActivity";
    HomeScreenViewModel homeScreenViewModel;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewNewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_redemption);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);

        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        homeScreenViewModel.callIroidResponseApi();
        subscribeObservers();
    }

    public void subscribeObservers() {
        homeScreenViewModel.getmData().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if (data != null) {
                    Banners[] banners = data.getBanners();
                    Categories[] categoriesArrayList = data.getCategories();
                    FreshProducts[] freshProducts = data.getFreshProducts();
                    initBanners(banners);
                    initCategoryRecyclerView(categoriesArrayList);
                    initProductsRecyclerView(freshProducts);
                }
            }
        });
    }

    private void initProductsRecyclerView(FreshProducts[] freshProducts) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewNewProducts = findViewById(R.id.rv_new_products);
        recyclerViewNewProducts.setLayoutManager(layoutManager);
        ProductsRecyclerAdapter adapter = new ProductsRecyclerAdapter(freshProducts, this);
        recyclerViewNewProducts.setAdapter(adapter);
    }

    private void initCategoryRecyclerView(Categories[] categories) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewCategory = findViewById(R.id.rv_category);
        recyclerViewCategory.setLayoutManager(layoutManager);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categories, this);
        recyclerViewCategory.setAdapter(adapter);
    }

    private void initBanners(Banners[] banners) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (Banners banner : banners) {
            ViewPagerItemFragment fragment = ViewPagerItemFragment.getInstance(banner);
            fragments.add(fragment);
        }
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
