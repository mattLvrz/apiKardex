package com.pipecode.topshop.configuration.test.service;

import com.pipecode.topshop.configuration.SpringInit;
import com.pipecode.topshop.configuration.dao.BaseDAO1;
import com.pipecode.topshop.configuration.dao.MovimientoDao;
import com.pipecode.topshop.configuration.dao.ProductoDao;
import com.pipecode.topshop.configuration.dto.MovimientoDto;
import com.pipecode.topshop.configuration.dto.KardexDto;
import com.pipecode.topshop.configuration.service.UtilsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringInit.class})
//@RunWith(MockitoJUnitRunner.class)
public class ServiceJUnitTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @InjectMocks
    private UtilsService utilsService;
    @Mock
    private BaseDAO1 aO1;
    @Mock
    private ProductoDao productoDao;
    @Mock
    private MovimientoDao movimientoDao;
    private List<MovimientoDto> movimientosList = new ArrayList<>();
    private MovimientoDto movi1;

    public ServiceJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.movimientosList = new ArrayList<>();
        this.movi1 = new MovimientoDto();
        this.movi1.setCantidadUnidad(29L);
        this.movi1.setPrecioUnitario(2100D);
        this.movi1.setDescripcion("movil 1");
        this.movi1.setTipo("E");
        this.movimientosList.add(movi1);
        this.movi1 = new MovimientoDto();
        this.movi1.setCantidadUnidad(15L);
        this.movi1.setPrecioUnitario(2400D);
        this.movi1.setDescripcion("movil 2");
        this.movi1.setTipo("E");
        this.movimientosList.add(movi1);
        this.movi1 = new MovimientoDto();
        this.movi1.setCantidadUnidad(16L);
        this.movi1.setPrecioUnitario(1900D);
        this.movi1.setDescripcion("movil 3");
        this.movi1.setTipo("E");
        this.movimientosList.add(movi1);
        this.movi1 = new MovimientoDto();
        this.movi1.setCantidadUnidad(12L);
        this.movi1.setDescripcion("movil 2");
        this.movi1.setTipo("S");
           this.movimientosList.add(movi1);
        when(this.utilsService.traerMovimientosDtoByIdProducto(26L)).thenReturn(this.movimientosList);

    }

    @After
    public void tearDown() {
    }

//    @Test(expected = ValidationException.class)
    @Test
    public void testIncrementa() {
        Double precioTotal = 101840.0D;
        List<KardexDto> comn = this.utilsService.traerKardexDtoByIdProducto(26L);
        assertEquals(comn.get(3).getPrecioUnitario(), comn.get(3).getPrecioSalidad());
        assertEquals(comn.get(3).getPrecioTotal(), precioTotal);
    }

    public MockitoRule getRule() {
        return rule;
    }

    public void setRule(MockitoRule rule) {
        this.rule = rule;
    }

    public UtilsService getUtilsService() {
        return utilsService;
    }

    public void setUtilsService(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    public BaseDAO1 getaO1() {
        return aO1;
    }

    public void setaO1(BaseDAO1 aO1) {
        this.aO1 = aO1;
    }

    public ProductoDao getProductoDao() {
        return productoDao;
    }

    public void setProductoDao(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    public MovimientoDao getMovimientoDao() {
        return movimientoDao;
    }

    public void setMovimientoDao(MovimientoDao movimientoDao) {
        this.movimientoDao = movimientoDao;
    }

    public List<MovimientoDto> getMovimientosList() {
        return movimientosList;
    }

    public void setMovimientosList(List<MovimientoDto> movimientosList) {
        this.movimientosList = movimientosList;
    }

    public MovimientoDto getMovi1() {
        return movi1;
    }

    public void setMovi1(MovimientoDto movi1) {
        this.movi1 = movi1;
    }

}
