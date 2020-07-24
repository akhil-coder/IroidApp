package com.example.iroidapp.models;

public class Data
{
    private FreshProducts[] freshProducts;

    private Categories[] categories;

    private Banners[] banners;

    private String[] ProductsForYou;

    public FreshProducts[] getFreshProducts()
    {
        return freshProducts;
    }

    public void setFreshProducts(FreshProducts[] freshProducts)
    {
        this.freshProducts = freshProducts;
    }

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }

    public Banners[] getBanners ()
    {
        return banners;
    }

    public void setBanners (Banners[] banners)
    {
        this.banners = banners;
    }

    public String[] getProductsForYou ()
    {
        return ProductsForYou;
    }

    public void setProductsForYou (String[] ProductsForYou)
    {
        this.ProductsForYou = ProductsForYou;
    }

}