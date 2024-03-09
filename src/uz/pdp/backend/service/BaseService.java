package uz.pdp.backend.service;

import java.util.List;

public interface BaseService<E>{
    boolean add(E object);
    boolean remove(String id);
    E findById(String id);
    List<E> getAll();
    List<E> search(String query);
}
