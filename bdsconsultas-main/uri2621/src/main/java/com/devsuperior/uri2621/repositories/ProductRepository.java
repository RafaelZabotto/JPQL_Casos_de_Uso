package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT a.name FROM products a INNER JOIN providers b " +
            "ON a.id_providers = b.id " +
            "WHERE a.amount BETWEEN :min AND :max " +
            "AND b.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String beginName);

    @Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " +
            "FROM Product obj " +
            "WHERE obj.amount BETWEEN :min AND :max " +
            "AND obj.provider.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinDTO> search2(Integer min, Integer max, String beginName);

}
