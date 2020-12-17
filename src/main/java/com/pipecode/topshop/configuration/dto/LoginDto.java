/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dto;

import java.io.Serializable;

/**
 *
 * @author deadlock
 */
public class LoginDto implements Serializable{
    
    
    private String nombreApodo;
    private String pass ;

    public String getNombreApodo() {
        return nombreApodo;
    }

    public void setNombreApodo(String nombreApodo) {
        this.nombreApodo = nombreApodo;
    }



    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
    
    
    
}
