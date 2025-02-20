package com.First.Ecomerse.Model;

public class producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private String descripcion;
    private String imagen;

    public producto(){
    }

    public producto(int idProducto, String nombre, double precio, int cantidad, String descripcion, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "producto [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", cantidad="
                + cantidad + ", descripcion=" + descripcion + ", imagen=" + imagen + "]";
    }

    

}
