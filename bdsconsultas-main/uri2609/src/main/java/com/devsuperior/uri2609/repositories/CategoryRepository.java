package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2609.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true,
            value = "SELECT a.name, SUM(b.amount) " +
                    "FROM categories a " +
                    "INNER JOIN products b " +
                    "ON a.id = b.id_categories " +
                    "GROUP BY a.name")
    List<CategorySumProjection> search1();

    //Como é uma relação de um para muitos, inverte-se a consulta, para a entidade que tem a relação de 1
    @Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) " +
            "FROM Product obj " +
            "GROUP BY obj.category.name ")
    List<CategorySumDTO> search2();

}
