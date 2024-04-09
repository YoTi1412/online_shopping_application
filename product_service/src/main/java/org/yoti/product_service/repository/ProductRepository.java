package org.yoti.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yoti.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
