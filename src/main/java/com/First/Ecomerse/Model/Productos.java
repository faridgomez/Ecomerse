package com.First.Ecomerse.Model;





import jakarta.persistence.*;

@Entity
@Table
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    private String nombre;
    private double precio;
    private Integer cantidad;
    private String descripcion;
    private String img;

    @ManyToOne
    private Usuario usuarios;

    public Productos() {
    }

    public Productos(int idProducto, String nombre, double precio, int cantidad, String descripcion, String img,
            Usuario usuarios) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.img = img;
        this.usuarios = usuarios;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Productos [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", cantidad="
                + cantidad + ", descripcion=" + descripcion + ", img=" + img + ", usuarios=" + usuarios + "]";
    }

}
