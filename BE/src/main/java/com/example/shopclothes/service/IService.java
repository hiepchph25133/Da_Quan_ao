package com.example.shopclothes.service;

import java.util.List;

public interface IService<T> {

    void save(T object);

    void update(T object, Long id);

    void delete(Long id);

    void search(Long id);

    List<T> select();
}
