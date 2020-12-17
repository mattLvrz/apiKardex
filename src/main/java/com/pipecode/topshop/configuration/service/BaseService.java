package com.pipecode.topshop.configuration.service;

import com.pipecode.topshop.configuration.dao.BaseDAO1;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BaseService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private UtilsService utilsService;

    public UtilsService getUtilsService() {
        return utilsService;
    }

    public void setUtilsService(UtilsService utilsService) {
        this.utilsService = utilsService;
    }
    
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public SeguridadService getSeguridadService() {
        return seguridadService;
    }

    public void setSeguridadService(SeguridadService seguridadService) {
        this.seguridadService = seguridadService;
    }
    

}
