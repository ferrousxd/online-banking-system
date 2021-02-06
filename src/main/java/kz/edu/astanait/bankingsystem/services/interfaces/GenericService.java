package kz.edu.astanait.bankingsystem.services.interfaces;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll();
    T save(T entity);
    T findById(Long id);
    void delete(T entity);
    void deleteById(Long id);
    Long count();
}
