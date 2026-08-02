package com.example.productcrud.service;

import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.model.Product;
import com.example.productcrud.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProductService {

    public static final String PRODUCT_NOT_FOUND = "Product not found";

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return repository.findAllByOrderByCreatedAtDesc().stream().map(ProductResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return ProductResponse.from(findProductOrThrow(id));
    }

    public ProductResponse create(ProductRequest request) {
        Product product = new Product(
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity()
        );
        return ProductResponse.from(repository.save(product));
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findProductOrThrow(id);
        applyRequest(product, request);
        return ProductResponse.from(repository.save(product));
    }

    public void delete(Long id) {
        repository.delete(findProductOrThrow(id));
    }

    private Product findProductOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND));
    }

    private static void applyRequest(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
    }
}
