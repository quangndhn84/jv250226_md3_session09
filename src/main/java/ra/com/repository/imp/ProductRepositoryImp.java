package ra.com.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.com.model.Product;
import ra.com.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    @Transactional
    public boolean save(Product product) {
        try {
            entityManager.persist(product);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
