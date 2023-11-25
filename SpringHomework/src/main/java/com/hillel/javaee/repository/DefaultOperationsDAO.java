package com.hillel.javaee.repository;

import java.util.ArrayList;

public interface DefaultOperationsDAO<E> {
    ArrayList<E> getAll();
    E getById(int id);
    void create(E e);
    void update(E e, String[] params);
    void delete(E e);

}
