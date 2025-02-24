package com.First.Ecomerse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.First.Ecomerse.Model.Productos;

@Repository
public interface ProductoRepository extends JpaRepository<Productos,Integer> {

}
