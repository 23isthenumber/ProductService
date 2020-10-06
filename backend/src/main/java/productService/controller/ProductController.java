package productService.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productService.model.Product;
import productService.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Integer id){
        try {
            Product product = productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update (@Valid @RequestBody Product product, @PathVariable Integer id){
        Product beforeUpdate = productService.findOne(id);
        beforeUpdate.setName(product.getName());
        beforeUpdate.setPrice(product.getPrice());
        beforeUpdate.setAvailable(product.isAvailable());
        Product afterUpdate = productService.save(beforeUpdate);
        return ResponseEntity.ok(afterUpdate);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteById(id);
    }


    @DeleteMapping
    public void deleteAll(){
        productService.deleteAll();
    }



}
