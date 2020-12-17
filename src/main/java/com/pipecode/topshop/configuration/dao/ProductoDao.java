/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.dao;

import com.pipecode.topshop.configuration.dto.ProductoDto;
import com.pipecode.topshop.configuration.entity.Producto;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "transactionManagerOracle")
public class ProductoDao extends BaseDAO1 {

    public List<Producto> traerProductos() {
        List<Producto> listProducto = em.createQuery(
                "SELECT c "
                + " FROM Producto c  WHERE c.estado='A' ", Producto.class)
                .getResultList();
        return listProducto;
    }

    
    ///  se prodria  hacer con transform (setResultTransformer) por motivos de tiempo se uso proyeccion(Proyecciones DTO en JPQL )
    public List<ProductoDto> traerProductosDto() {
        TypedQuery<ProductoDto> q = em.createQuery(
                "SELECT new com.pipecode.topshop.configuration.dto.ProductoDto( b.id,  b.nombre,  b.descripcion,  b.stockMinimo,  b.stockMaximo , b.stockActual) FROM Producto b WHERE b.estado='A' ",
                ProductoDto.class);    
        List<ProductoDto> list = q.getResultList();
        return list;
    }

    public void actualizarProducto(Producto producto) {
        this.update(producto);
    }

    public Producto traerUsuarioById(Long idProducto) {
        Producto salida = em.find(Producto.class, idProducto);
        return salida;
    }

}
