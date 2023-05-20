package com.jaqubm.shopping_list_fx;

import java.util.ArrayList;

public class CategoryList {
    private final String category;
    private final ArrayList<ProductList> products = new ArrayList<>();

    /**
     * Constructor of category class
     * @param category is a category name
     */
    public CategoryList(String category) {
        this.category = category;
    }

    /**
     * Getting the name of this category
     * @return category name
     */
    public String getCategory() { return category; }

    /**
     * Getting ArrayList of products from this category
     * @return ArrayList of products
     */
    public ArrayList<ProductList> getProducts() { return products; }

    /**
     * Adding new product to this category
     * @param productName is a product name
     * @param productMeasurements is a type of product measurements
     * @param productNumber is a number of products
     */
    public void addProduct(String productName, String productMeasurements, float productNumber) {
        products.add(new ProductList(productName, productMeasurements, productNumber));
    }
}
