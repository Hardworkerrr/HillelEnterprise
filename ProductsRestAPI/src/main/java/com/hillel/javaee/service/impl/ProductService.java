package com.hillel.javaee.service.impl;

import com.hillel.javaee.exception.BadEntityFormat;
import com.hillel.javaee.exception.NoSuchProductExists;
import com.hillel.javaee.model.Category;
import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.ProductServiceInterface;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProductService implements ProductServiceInterface {
    @Override
    public List<Product> getAllProducts() {
        List<Product> productList;
        try (SessionFactory sessionFactory = getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            productList = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        Product product;
        try (SessionFactory sessionFactory = getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            if ((product = session.get(Product.class, id)) == null) {
                throw new NoSuchProductExists("No such product exists!");
            }
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        try (SessionFactory sessionFactory = getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            for (Category category :
                    product.getCategories()) {
                Category result = session.get(Category.class, category.getId());
                if (result == null) {
                    session.persist(category);
                } else
                    result.setName(category.getName());
            }
            session.persist(product);
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product oldProduct;
        try (SessionFactory sessionFactory = getSessionFactory()) {
            if (product.getId() == 0) {
                throw new BadEntityFormat("Id of product can't be 0");
            }
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            if ((oldProduct = session.get(Product.class, product.getId())) != null) {
                oldProduct.setName(product.getName());
                oldProduct.setQuantity(product.getQuantity());
                oldProduct.setPrice(product.getPrice());
                oldProduct.setCalories(product.getCalories());
                for (Category category :
                        product.getCategories()) {
                    Category result = session.get(Category.class, category.getId());
                    if (result == null) {
                        session.persist(category);
                    } else
                        result.setName(category.getName());
                }
                oldProduct.setCategories(product.getCategories());
            } else {
                oldProduct = product;
                session.persist(product);
            }
            session.getTransaction().commit();
        }
        return oldProduct;
    }

    @Override
    public String removeProduct(int id) {
        try (SessionFactory sessionFactory = getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.remove(getProductById(id));
            session.getTransaction().commit();
        }
        return "Successfully deleted";
    }

    private SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();
    }
}
