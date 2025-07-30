package ra.com.service;

import ra.com.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int id);

    boolean create(Category category);

    boolean update(Category category);

    boolean delete(int catalogId);
}
