/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.service;

import com.pipecode.topshop.configuration.dao.UsuarioDao;
import com.pipecode.topshop.configuration.dto.LoginDto;
import com.pipecode.topshop.configuration.entity.Acceso;
import com.pipecode.topshop.configuration.entity.Producto;
import com.pipecode.topshop.configuration.entity.Usuario;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao aO1;

    public Usuario traerUsuarios() {
        return aO1.traerUsuarios();
    }

    public Usuario loginUser(LoginDto login) {
        return aO1.loginUser(login);
    }

    public Acceso crearAcceso(Usuario usuario, String jwts, String ipAcceso) {
        Acceso acceso = new Acceso();
        acceso.setFechaInicio(Calendar.getInstance());
        acceso.setIdUsuario(usuario.getId());
        acceso.setJwts(jwts);
        acceso.setIpAsociada(ipAcceso);
        this.aO1.insert(acceso);
        return acceso;

    }

    public Usuario traerUsuarioById(Long idUsuarioJwt) {
         return aO1.traerUsuarioById(idUsuarioJwt);
    }

    
    
    

    
    
    
    
}
