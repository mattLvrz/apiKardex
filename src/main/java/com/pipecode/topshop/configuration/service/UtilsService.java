/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.service;

import com.pipecode.topshop.configuration.dao.BaseDAO1;
import com.pipecode.topshop.configuration.dao.MovimientoDao;
import com.pipecode.topshop.configuration.dao.ProductoDao;
import com.pipecode.topshop.configuration.dto.MovimientoDto;
import com.pipecode.topshop.configuration.dto.ProductoDto;
import com.pipecode.topshop.configuration.dto.KardexDto;
import com.pipecode.topshop.configuration.entity.Movimiento;
//import com.pipecode.topshop.configuration.domain.Acceso;
//import com.pipecode.topshop.configuration.domain.Comunidad;
//import com.pipecode.topshop.configuration.domain.Login;
//import com.pipecode.topshop.configuration.domain.Usuario;
//import com.pipecode.topshop.configuration.domain.UsuarioDTO;
//import com.pipecode.topshop.configuration.domain.WsysUrlExternas;
import com.pipecode.topshop.configuration.entity.Producto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilsService extends BaseDAO1 {

    @Autowired
    private ProductoDao productoDao;

    //por razones de tiempo se utiliza un solo servicio para contronlar los DAO
    @Autowired
    private MovimientoDao movimientoDao;

    public void guardarProducto(Producto producto) {
        producto.setFechaCreacion(Calendar.getInstance());
        producto.setEstado("A");
        this.productoDao.update(producto);
    }

    public List<Producto> traerProductos() {
        return this.productoDao.traerProductos();
    }

    public List<ProductoDto> traerProductosDto() {
        return this.productoDao.traerProductosDto();
    }

    public void actualizarProducto(Producto producto) {
        this.productoDao.actualizarProducto(producto);
    }

    public Producto traerProductoById(Long idProducto) {
        return this.productoDao.traerUsuarioById(idProducto);
    }

    public void guardarMovimiento(Movimiento movimiento) {
        movimiento.setFechaCreacion(Calendar.getInstance());
        movimiento.setFechaModificacion(Calendar.getInstance());
        movimiento.setEstado("A");
        Producto producto = this.traerProductoById(movimiento.getIdProducto());
        if ("E".equals(movimiento.getTipo())) {
            producto.setStockActual(producto.getStockActual() + movimiento.getCantidadUnidad());
        } else {
            producto.setStockActual(producto.getStockActual() - movimiento.getCantidadUnidad());
        }
        // esto se podria ahcer en una sola linea si se escribe en annotations  una relacion entre movimiento y producto 
        this.movimientoDao.insert(movimiento);
        this.productoDao.update(producto);
    }

    public List<MovimientoDto> traerMovimientosDto() {
        return this.movimientoDao.traerMovimientosDto();
    }

    public Long actualizarMovilidad(Movimiento movimiento) {
        Movimiento movimientoOld = this.traerMovimientoById(movimiento.getIdMovimiento());
        Producto producto = this.estadoAnteriorProducto(movimiento, movimientoOld);
        movimiento.setFechaCreacion(movimientoOld.getFechaCreacion());
        movimiento.setFechaModificacion(Calendar.getInstance());
        movimiento.setEstado("A");
        if ("E".equals(movimiento.getTipo())) {
            producto.setStockActual(producto.getStockActual() + movimiento.getCantidadUnidad());
        } else {
            producto.setStockActual(producto.getStockActual() - movimiento.getCantidadUnidad());
        }
        // esto se podria hacer en una sola linea si se escribe en annotations  una relacion entre movimiento y producto 
        this.movimientoDao.actualizarMovimiento(movimiento);
        this.productoDao.update(producto);
        return producto.getStockActual();
    }

    public Long eliminarMovilidad(Movimiento movimiento) {
        Producto producto = this.estadoAnteriorProducto(null, movimiento);
        movimiento.setEstado("B");
        this.movimientoDao.actualizarMovimiento(movimiento);
        return producto.getStockActual();
    }

    public Movimiento traerMovimientoById(Long idMovimiento) {
        return this.movimientoDao.traerMovimientoById(idMovimiento);
    }

    private Producto estadoAnteriorProducto(Movimiento movimiento, Movimiento movimientoOld) {
        Producto producto = this.traerProductoById(movimiento != null ? movimiento.getIdProducto() : movimientoOld.getIdProducto());
        //esto se prodria poner todo en una misma cuenta para que se mas claro se a desplegado en varias cuentas e if*
        if ("E".equals(movimientoOld.getTipo())) {
            producto.setStockActual(producto.getStockActual() - movimientoOld.getCantidadUnidad());
        } else {
            producto.setStockActual(producto.getStockActual() + movimientoOld.getCantidadUnidad());
        }
        return producto;
    }

    public List<MovimientoDto> traerMovimientosDtoByIdProducto(Long idProducto) {
        return this.movimientoDao.traerMovimientosDtoByIdProducto(idProducto);
    }

    public List<KardexDto> traerKardexDtoByIdProducto(Long idProducto) {
        // el dinero se debe manejar en Java tiene la clase BigDecimal en un aplicativo de gran nivel , en modo de prueba se usara doble , si bien no es recomendable a rasgos de la prueba llevara bien su papel.
        Producto producto = this.traerProductoById(idProducto);
        List<KardexDto> salida = new ArrayList<>();
        KardexDto kardex = null;
        List<MovimientoDto> movimientosList = this.traerMovimientosDtoByIdProducto(idProducto);
    
        Double precioTotalPromedio = 0D;
        Double precioTotal = 0D;
        Long totalCantidadStock = 0L;
        Double precioUnitario = 100D;
         for (MovimientoDto aux : movimientosList) {
            kardex = new KardexDto();
            kardex.setFecha(aux.getFechaCreacionString());
            kardex.setDescripcion(aux.getDescripcion());
            if ("E".equals(aux.getTipo())) {
                totalCantidadStock = totalCantidadStock + aux.getCantidadUnidad();
                precioTotal = precioTotal + (aux.getPrecioUnitario() * aux.getCantidadUnidad());
                precioTotalPromedio = precioTotal / totalCantidadStock;
                //entrada
                kardex.setCantidadEntrada(aux.getCantidadUnidad());
                kardex.setPrecioEntrada(aux.getPrecioUnitario());
                //resultado
                kardex.setCantidadStock(totalCantidadStock);
                kardex.setPrecioTotal(precioTotal);
                kardex.setPrecioUnitario(precioTotalPromedio);
            } else {
                totalCantidadStock = totalCantidadStock - aux.getCantidadUnidad();
                precioTotal = precioTotal - (precioTotalPromedio * aux.getCantidadUnidad());
                //precion unitario de salida es precioTotalPromedio
                //salida
                kardex.setCantidadSalidad(aux.getCantidadUnidad());
                kardex.setPrecioSalidad(precioTotalPromedio);
                // resultado
                kardex.setCantidadStock(totalCantidadStock);
                kardex.setPrecioTotal(precioTotal);
                kardex.setPrecioUnitario(precioTotalPromedio);
            }
            salida.add(kardex);
        }
        return salida;
    }

}
