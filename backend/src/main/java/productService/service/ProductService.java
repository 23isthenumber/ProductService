package productService.service;

import org.springframework.stereotype.Service;
import productService.data.ProductRepository;
import productService.model.Product;

import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService (ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<Product> findAll() {

        return productRepository.findAll();
    }

    public Product findById(Integer integer) {
        return productRepository.findById(integer).get();
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public Product findOne (Integer integer) {
        return productRepository.getOne(integer);
    }

    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }



    public void deleteAll() {
        productRepository.deleteAll();
    }
}
