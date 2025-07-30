package ra.com.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.com.model.Category;
import ra.com.repository.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {
    //Tiêm vào instance của EntityManager --> session làm việc với entity
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category findById(int catalogId) {
        return entityManager.createQuery("from Category where catalogId = :id", Category.class)
                .setParameter("id", catalogId).getSingleResult();
    }

    @Override
    @Transactional
    public boolean create(Category catalog) {
        try {
            entityManager.persist(catalog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(Category catalog) {
        try {
            entityManager.merge(catalog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int catalogId) {
        try {
            //catalogId --> catalog cần xóa
            Category catalog = findById(catalogId);
            //xóa theo catalog
            entityManager.remove(catalog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
