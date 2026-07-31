package com.mthombe.service.impl;

import com.mthombe.mapper.ProductMapper;
import com.mthombe.modal.Category;
import com.mthombe.modal.Product;
import com.mthombe.modal.Store;
import com.mthombe.modal.User;
import com.mthombe.payload.dto.ProductDTO;
import com.mthombe.repository.CategoryRepository;
import com.mthombe.repository.ProductRepository;
import com.mthombe.repository.StoreRepository;
import com.mthombe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
        Store store = storeRepository.findById(
                productDTO.getStoreId()).orElseThrow(
                ()-> new Exception("Store not found")
        );

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                () -> new Exception("category not found...")
        );
        Product product = ProductMapper.toEntity(productDTO, store, category);
        Product saveProduct = productRepository.save(product);
        return ProductMapper.toDTO(saveProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("product not found"));

        // update fields from DTO
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());

        // update category if provided
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new Exception("category not found..."));

            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception ("product not found...")
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductByStoreId(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().map(
                ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(
                        ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
