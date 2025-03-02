package com.First.Ecomerse.Service;

import java.util.List;
import java.util.Optional;

import com.First.Ecomerse.Model.Productos;


public interface ProductoService {
    public Productos save(Productos productos);
    public Optional<Productos> get(Integer idProducto);
    public void update(Productos productos);
    public void delete(Integer idProducto);
    public List<Productos> findAll();

}
