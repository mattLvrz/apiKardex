/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dao;

import com.pipecode.topshop.configuration.dto.MovimientoDto;
import com.pipecode.topshop.configuration.entity.Movimiento;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deadlock
 */


@Repository
@Transactional
public class MovimientoDao  extends BaseDAO1 {

    public List<MovimientoDto> traerMovimientosDto() {
        TypedQuery<MovimientoDto> q = em.createQuery(
                "SELECT new com.pipecode.topshop.configuration.dto.MovimientoDto( b.id,b.descripcion,b.tipo, b.idProducto, b.cantidadUnidad, b.fechaCreacion ) FROM Movimiento b WHERE b.estado='A' ",
                MovimientoDto.class);    
        List<MovimientoDto> list = q.getResultList();
        return list;
    }

    public void actualizarMovimiento(Movimiento movimiento) {
        this.update(movimiento);
    }

    public Movimiento traerMovimientoById(Long idMovimiento) {
        Movimiento salida = em.find(Movimiento.class, idMovimiento);
        return salida;
    }

    public List<MovimientoDto> traerMovimientosDtoByIdProducto(Long idProducto) {
       TypedQuery<MovimientoDto> q = em.createQuery(
                "SELECT new com.pipecode.topshop.configuration.dto.MovimientoDto( b.id,b.descripcion,b.tipo, b.idProducto, b.cantidadUnidad, b.fechaCreacion , b.precioUnitario) FROM Movimiento b WHERE b.estado='A' and b.idProducto=:idProducto",
                MovimientoDto.class)  .setParameter("idProducto", idProducto);    
        List<MovimientoDto> list = q.getResultList();
        return list;
    }
    
    
    
    
    
    
}
