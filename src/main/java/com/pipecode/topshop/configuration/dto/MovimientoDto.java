/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Calendar;

public class MovimientoDto implements Serializable{

    private Long idMovimiento;
    private String descripcion;
    private String tipo;
    private Long idProducto;
    private Long cantidadUnidad;
    private String fechaCreacionString;
    private Double precioUnitario;
    @JsonIgnore
    private Calendar fechaCreacion;

    public MovimientoDto(Long idMovimiento, String descripcion, String tipo, Long idProducto, Long cantidadUnidad, Calendar fechaCreacion , Double precioUnitario) {
        this.idMovimiento=idMovimiento;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.idProducto = idProducto;
        this.cantidadUnidad = cantidadUnidad;
        this.fechaCreacionString = String.valueOf(fechaCreacion.getTimeInMillis());
        this.precioUnitario = precioUnitario;
    }

    public MovimientoDto() {
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    
    
    
    
    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getFechaCreacionString() {
        return fechaCreacionString;
    }

    public void setFechaCreacionString(String fechaCreacionString) {
        this.fechaCreacionString = fechaCreacionString;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getCantidadUnidad() {
        return cantidadUnidad;
    }

    public void setCantidadUnidad(Long cantidadUnidad) {
        this.cantidadUnidad = cantidadUnidad;
    }

}
