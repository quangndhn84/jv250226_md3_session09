package ra.com.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.com.dto.request.ProductRequestCreateDTO;
import ra.com.model.Product;
import ra.com.repository.ProductRepository;
import ra.com.service.ProductService;
import ra.com.service.UploadFileService;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean create(ProductRequestCreateDTO productDto) {
        /*
         * Input: product, multipart (file)
         * Output: URL của ảnh --> product --> save Database
         * */
        //productDto (file - MultipartFile) --> product entity (image - String - URL)
        //1. Upload ảnh lên cloudiary --> URL của ảnh sau upload
        String imageUrl = uploadFileService.uploadFile(productDto.getFile());
        //2. set URL vào thuộc tính image của product
        //dto --> entity
        Product product = new Product(productDto.getProductId(),
                productDto.getProductName(),
                productDto.getPrice(),
                imageUrl,
                productDto.getCreated(),
                productDto.getCatalog(), true);
        //3. Gọi productRepository để thêm mới
        return productRepository.save(product);
    }
}
