package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Consulta feita no modo SQL padrão
    @Query(nativeQuery = true, value = "SELECT name FROM customers WHERE state = UPPER(:state)")
    List<CustomerMinProjection> search1(String state);

    //Consulta feita com JPQL, não precisa da projection
    //O SELECT de uma consulta restringida, precisa trazer o pacote e o atributo requerido. Com o new antes.
    //O FROM precisa ter o nome da entidade igual ao do mapeado e um alias.
    //O atributo do WHERE tmb precisa do alias antes
    @Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name)" +
            "FROM Customer obj " +
            "WHERE obj.state = UPPER(:state)")
    List<CustomerMinDTO> search2(String state);
}
