package com.hillel.javaee.repository;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.models.Category;
import com.hillel.javaee.models.Product;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO implements DefaultOperationsDAO<Category> {

    private ArrayList<Category> categories = new ArrayList<>();

    public CategoryDAO() {

    }

    public CategoryDAO(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Category> getProductCategoriesByID(int id) {
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT category_id, name  FROM product_category JOIN category ON category_id=category.id WHERE product_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
            connection.close();

        } catch (SQLException | URISyntaxException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Category category:
             categories) {
            System.out.println(category.getId() + category.getName());
        }
        return categories;
    }

    @Override
    public ArrayList<Category> getAll() {
        return null;
    }

    @Override
    public Category getById(int id) {
        return null;
    }

    @Override
    public void create(Category category) {

    }

    @Override
    public void update(Category category, String[] params) {

    }

    @Override
    public void delete(Category category) {

    }
}
