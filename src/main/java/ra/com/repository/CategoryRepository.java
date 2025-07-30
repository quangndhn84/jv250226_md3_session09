package ra.com.repository;

import ra.com.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    Category findById(int catalogId);

    boolean create(Category catalog);

    boolean update(Category catalog);

    boolean delete(int catalogId);

}
