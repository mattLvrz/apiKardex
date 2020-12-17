/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipecode.topshop.configuration.dto.MovimientoDto;
import com.pipecode.topshop.configuration.dto.ProductoDto;
import com.pipecode.topshop.configuration.dto.KardexDto;
import com.pipecode.topshop.configuration.entity.Movimiento;
import com.pipecode.topshop.configuration.entity.Producto;
import com.pipecode.topshop.configuration.service.BaseService;
import com.pipecode.topshop.configuration.util.UApp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ControlApiRest {

    @Autowired
    private BaseService baseService;

    @CrossOrigin
    @RequestMapping("/")
    public String inicio(HttpServletRequest request) throws JsonProcessingException {

        
              
        ObjectMapper mapper = new ObjectMapper();
        String salida = "bienvenido.. sevice rest TAD..";
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(salida);
        return json;
    }

    @RequestMapping(value = "producto", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ProductoDto crearProducto(HttpServletRequest request, @RequestBody ProductoDto producto) throws JsonProcessingException {
        Long idUsuarioJwt = Long.parseLong((String) request.getAttribute("customAttribute"));
        Producto productoEntty = UApp.parseDtoToEnty(producto, Producto.class);
        productoEntty.setUsuarioCreador(this.baseService.getUsuarioService().traerUsuarioById(idUsuarioJwt));
        this.baseService.getUtilsService().guardarProducto(productoEntty);
        producto.setId(productoEntty.getId());
        return producto;
    }

    @RequestMapping(value = "producto", produces = "application/json", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<ProductoDto> buscarProductos(HttpServletRequest request) {
        List<ProductoDto> salida = this.baseService.getUtilsService().traerProductosDto();
        return salida;
    }

    @RequestMapping(value = "producto/{id}", produces = "application/json", method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public void actualizarOrden(HttpServletRequest request, @PathVariable() String id, @RequestBody String estado) throws JsonProcessingException {
        Producto producto = this.baseService.getUtilsService().traerProductoById(Long.parseLong(id));
        producto.setEstado(estado);
        this.baseService.getUtilsService().actualizarProducto(producto);
    }

    @RequestMapping(value = "movimiento", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public MovimientoDto crearMovimiento(HttpServletRequest request, @RequestBody MovimientoDto movimiento) throws JsonProcessingException {
        Movimiento movimientoEntty = UApp.parseDtoToEnty(movimiento, Movimiento.class);
        this.baseService.getUtilsService().guardarMovimiento(movimientoEntty);
        return movimiento;
    }

    @RequestMapping(value = "movimiento", produces = "application/json", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<MovimientoDto> buscarMovimientos(HttpServletRequest request) {
        List<MovimientoDto> salida = this.baseService.getUtilsService().traerMovimientosDto();
        return salida;
    }

    @RequestMapping(value = "movimiento/producto/{idProducto}", produces = "application/json", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<MovimientoDto> buscarMovimientosByIdProducto(HttpServletRequest request, @PathVariable() String idProducto) {
        List<MovimientoDto> salida = this.baseService.getUtilsService().traerMovimientosDtoByIdProducto(Long.parseLong(idProducto));
        return salida;
    }

    @RequestMapping(value = "movimiento/{id}", produces = "application/json", method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Long eliminaMovimientoEstado(HttpServletRequest request, @PathVariable() String id, @RequestBody String estado) throws JsonProcessingException {
        Long idUsuarioJwt = Long.parseLong((String) request.getAttribute("customAttribute"));
        Movimiento movimiento = this.baseService.getUtilsService().traerMovimientoById(Long.parseLong(id));
        Long salida = this.baseService.getUtilsService().eliminarMovilidad(movimiento);
        return salida;
    }

    @RequestMapping(value = "movimiento/", produces = "application/json", method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Long actualizarMovimiento(HttpServletRequest request, @RequestBody MovimientoDto movimientoDto) throws JsonProcessingException {
        Movimiento movimientoEntty = UApp.parseDtoToEnty(movimientoDto, Movimiento.class);
        Long stockActual = this.baseService.getUtilsService().actualizarMovilidad(movimientoEntty);
//        movimientoDto = UApp.parseEntyToDto(movimientoEntty, MovimientoDto.class);
        return stockActual;
    }

    
    
    
    
    @RequestMapping(value = "kardex/{idProducto}", produces = "application/json", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public   List<KardexDto> traerKardex(HttpServletRequest request, @PathVariable() String idProducto) {
        //servicio que hara todas las cuentas  y registros matematicos simples. tener en cuenta que no esta pensado para ser eficiente. sino como una demo. 
        List<KardexDto> salida = this.baseService.getUtilsService().traerKardexDtoByIdProducto(Long.parseLong(idProducto));
        return salida;
    }

    
    
}
