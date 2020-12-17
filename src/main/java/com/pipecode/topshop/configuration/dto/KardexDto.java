/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dto;

import java.util.Calendar;

public class KardexDto {

    private Long id;
    private String  fecha;
  
    private String Descripcion;
    
    private Double precioEntrada;
    private Long cantidadEntrada;
    private Double totalEntrada;
    
      private Double precioSalidad;
    private Long cantidadSalidad;  
    private Double totalSalida;
    
    private Long cantidadStock;
    private Double precioUnitario;
    private Double PrecioTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(Double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public Long getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Long cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Double getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(Double totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public Double getPrecioSalidad() {
        return precioSalidad;
    }

    public void setPrecioSalidad(Double precioSalidad) {
        this.precioSalidad = precioSalidad;
    }

    public Long getCantidadSalidad() {
        return cantidadSalidad;
    }

    public void setCantidadSalidad(Long cantidadSalidad) {
        this.cantidadSalidad = cantidadSalidad;
    }

    public Double getTotalSalida() {
        return totalSalida;
    }

    public void setTotalSalida(Double totalSalida) {
        this.totalSalida = totalSalida;
    }

    public Long getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Long cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return PrecioTotal;
    }

    public void setPrecioTotal(Double PrecioTotal) {
        this.PrecioTotal = PrecioTotal;
    }


}
