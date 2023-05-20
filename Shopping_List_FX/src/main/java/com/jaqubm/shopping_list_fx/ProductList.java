package com.jaqubm.shopping_list_fx;

public class ProductList {
    private final String productName;
    private final String productMeasurements;
    private float productNumber;

    /**
     * Constructor of product class
     * @param name is a product name
     * @param measurements is a type of product measurements
     * @param number is a number of products
     */
    ProductList(String name, String measurements, float number) {
        productName = name;
        productMeasurements = measurements;
        productNumber = number;
    }

    /**
     * Getting the name of this product
     * @return product name
     */
    public String getProductName() { return productName; }

    /**
     * Getting the measurements of this product
     * @return measurements of this product
     */
    public String getProductMeasurements() { return productMeasurements; }

    /**
     * Getting the numbers of this product
     * @return numbers of this product
     */
    public float getProductNumber() { return productNumber; }

    /**
     * Increasing number of products
     * @param increase productNumber by increase value
     */
    public void increaseProductNumber(float increase) { this.productNumber += increase; }
}
