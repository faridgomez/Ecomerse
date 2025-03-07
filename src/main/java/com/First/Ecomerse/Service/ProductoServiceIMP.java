package com.First.Ecomerse.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.First.Ecomerse.Model.Productos;
import com.First.Ecomerse.Repository.ProductoRepository;

@Service
public class ProductoServiceIMP implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Productos save(Productos productos) {
        return productoRepository.save(productos);

    }

    public Optional<Productos> get(int id) {
        return productoRepository.findById(id); // Usa 'id' en lugar de 'idProducto'
    }

    @Override
    public void update(Productos productos) {
        productoRepository.save(productos);
    }

    @Override
    public void delete(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }

    @Override
    public List<Productos> findAll() {
        return productoRepository.findAll();
    }

}
