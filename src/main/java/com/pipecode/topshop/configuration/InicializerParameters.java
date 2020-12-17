/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration;

import com.pipecode.topshop.configuration.service.UtilsService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;

/**
 *
 * @author deadlock
 */
public class InicializerParameters implements WebApplicationInitializer {

 
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
//        this.utilsService.cargarUrlsExternas();
    }

}
