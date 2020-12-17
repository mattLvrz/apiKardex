/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dto;

import java.io.Serializable;


public class UsuarioDTO implements Serializable {

    private String nombre;
    private String nombreApodo;
    private Long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreApodo() {
        return nombreApodo;
    }

    public void setNombreApodo(String nombreApodo) {
        this.nombreApodo = nombreApodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}
