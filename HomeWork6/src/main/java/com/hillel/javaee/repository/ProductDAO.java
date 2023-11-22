package com.hillel.javaee.repository;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.model.Product;
import com.hillel.javaee.utils.SpringScriptUtility;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO implements DefaultOperationsDAO<Product> {

    private ArrayList<Product> products = new ArrayList<>();

    public ProductDAO(ArrayList<Product> products) {
        this.products = products;
    }

    public ProductDAO() {

    }

    @Override
    public ArrayList<Product> getAll() {
        products = new ArrayList<>();
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SpringScriptUtility.readResourceSql("sql/getAllProducts.sql"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setCalories(resultSet.getDouble(3));
                product.setPrice(resultSet.getDouble(4));
                product.setQuantity(resultSet.getInt(5));
                product.setCategories(new CategoryDAO().getProductCategoriesByID(resultSet.getInt(1)));
                products.add(product);
            }
            connection.close();

        } catch (SQLException | URISyntaxException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        Product product = new Product();
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.readResourceSql("sql/getProductById.sql"));
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(id);
                product.setName(resultSet.getString(2));
                product.setCalories(resultSet.getDouble(3));
                product.setPrice(resultSet.getDouble(4));
                product.setQuantity(resultSet.getInt(5));
                product.setCategories(new CategoryDAO().getProductCategoriesByID(resultSet.getInt(1)));
            }
            connection.close();

        } catch (SQLException | URISyntaxException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void create(Product product) {

    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }


}
