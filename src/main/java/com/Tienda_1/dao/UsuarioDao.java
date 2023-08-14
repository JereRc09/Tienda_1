
package com.Tienda_1.dao;

import com.Tienda_1.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    Usuario finByUsername(String username);
}

