package com.hillel.javaee.repository;

import com.hillel.javaee.model.Order;

import java.util.ArrayList;

public class OrderDAO implements DefaultOperationsDAO<Order> {
    private ArrayList<Order> orders = new ArrayList<>();

    public OrderDAO() {

    }
    public OrderDAO(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    @Override
    public Order getById(int id) {
        return null;
    }

    @Override
    public void create(Order order) {

    }

    @Override
    public void update(Order order, String[] params) {

    }

    @Override
    public void delete(Order order) {

    }
}
