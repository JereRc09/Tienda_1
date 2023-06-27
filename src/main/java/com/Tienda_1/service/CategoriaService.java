
package com.Tienda_1.service;

import com.Tienda_1.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    public List<Categoria> getCategorias(boolean activos);
    
}