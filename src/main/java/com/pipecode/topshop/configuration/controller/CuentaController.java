/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipecode.topshop.configuration.dto.LoginDto;
import com.pipecode.topshop.configuration.entity.Usuario;
import com.pipecode.topshop.configuration.service.BaseService;
import com.pipecode.topshop.configuration.service.SeguridadService;
import com.pipecode.topshop.configuration.service.UsuarioService;
import io.jsonwebtoken.JwtBuilder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author deadlock
 */
@CrossOrigin
@RestController
public class CuentaController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String login(HttpServletRequest request, @RequestBody LoginDto login) throws JsonProcessingException {
        Map<String, String> salida = new HashMap<String, String>();
        Usuario usuario = this.baseService.getUsuarioService().loginUser(login);
        if (usuario != null) {
            JwtBuilder token = this.baseService.getSeguridadService().crearTokenUsuario(usuario);
            this.baseService.getUsuarioService().crearAcceso(usuario, token.compact(),  request.getRemoteAddr());
            salida.put("jwt", token.compact());
        }
        // code  401 falta agregar.
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(salida);
        return salida.isEmpty() ? null : json;
    }

    /// falta metodo deslogeear.  
    
    
}
