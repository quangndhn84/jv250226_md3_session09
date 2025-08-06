package ra.com.repository;

import ra.com.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    boolean save(Product product);
}
