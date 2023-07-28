
package com.Tienda_1.service;

import com.Tienda_1.domain.Producto;
import java.util.List;

public interface ProductoService {
    
 // Se obtiene un listado de Productos en un List
    public List<Producto> getProductos(boolean activos);
    
   // Se obtiene un Producto, a partir del id de un Producto
    public Producto getProducto(Producto producto);
    
    // Se inserta un nuevo Producto si el id del Producto esta vacío
    // Se actualiza un Producto si el id del Producto NO esta vacío
    public void save(Producto producto);
    
    // Se elimina el Producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
    
    //Ejemplo de método utilizando Métodos de Query
    public List<Producto> getByPrecio(double precioInf, double precioSup);

    //Lista de productos utilizando consultas con JPQL    
    public List<Producto> consultaJPQL(double precioInf, double precioSup);

    //Lista de productos utilizando consultas con SQL Nativo
    public List<Producto> consultaNativo(double precioInf, double precioSup);
    
}
