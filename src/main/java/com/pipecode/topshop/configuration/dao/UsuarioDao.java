/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dao;

import com.pipecode.topshop.configuration.dto.LoginDto;
import com.pipecode.topshop.configuration.entity.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UsuarioDao extends BaseDAO1{
        
    public Usuario traerUsuarios() {
        List<Usuario> salida = em.createQuery(
                "SELECT c "
                + " FROM Usuario c     ", Usuario.class)
                .getResultList();
        return salida.isEmpty() ? null : salida.get(0);
    }
    
    public Usuario loginUser(LoginDto login) {
        List<Usuario> salida = em.createQuery(
                "SELECT u "
                + " FROM Usuario u  "
                + " WHERE u.nombreApodo =:apodo   and u.estado='A' and  u.pass=:pass   ",
                Usuario.class)
                .setParameter("apodo", login.getNombreApodo().toUpperCase())
                .setParameter("pass", login.getPass())
                .getResultList();
        return salida.isEmpty() ? null : salida.get(0);
    }

    public Usuario traerUsuarioById(Long idUsuarioJwt) {
        Usuario salida = em.find(Usuario.class, idUsuarioJwt);
        return salida;
    }
}
