/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration;

import com.pipecode.topshop.configuration.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author deadlock
 */
@Component
public class DoOnStart {
    
    @Autowired
    private UtilsService utilsService;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent e) {
//        this.utilsService.cargarUrlsExternas();
    }
    
    
}
