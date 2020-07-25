package com.example.iroidapp.models;

public class Data
{
    private FreshProducts[] fresh_products;

    private Categories[] categories;

    private Banners[] banners;

    private String[] ProductsForYou;

    public Data(FreshProducts[] freshProducts, Categories[] categories, Banners[] banners, String[] productsForYou) {
        this.fresh_products = freshProducts;
        this.categories = categories;
        this.banners = banners;
        ProductsForYou = productsForYou;
    }

    public FreshProducts[] getFreshProducts()
    {
        return fresh_products;
    }

    public void setFreshProducts(FreshProducts[] freshProducts)
    {
        this.fresh_products = freshProducts;
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