/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.service;

import com.pipecode.topshop.configuration.entity.Usuario;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeguridadService {
   
    @Autowired
    private UsuarioService usuarioServiceImpl;
    private final String clavePrivada = "clave_Propia";

    public JwtBuilder crearTokenUsuario(Usuario usuario) {
        JwtBuilder token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "clave_Propia")
                .setIssuedAt(Calendar.getInstance().getTime())
                .setSubject(usuario.getId().toString())
                .setExpiration(new Date(Calendar.getInstance().getTimeInMillis() + 604800000))
                .claim("route", "admin")
                .claim("usuario", usuario.getId())
                .claim("entorno", "US")
                .claim("rol", "US");
        return token;
    }
    
}
