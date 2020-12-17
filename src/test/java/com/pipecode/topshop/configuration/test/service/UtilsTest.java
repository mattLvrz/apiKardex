/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration.test.service;

import com.pipecode.topshop.configuration.SpringInit;
import com.pipecode.topshop.configuration.util.ClienteForm;
import com.pipecode.topshop.configuration.util.ClienteModel;
import com.pipecode.topshop.configuration.util.UApp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringInit.class})
public class UtilsTest {

    public UtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test01() {
        ClienteForm c = new ClienteForm();
        c.setIdCliente("1");
        c.setNombre("Juan");
        ClienteModel model = UApp.parseDtoToEnty(c, ClienteModel.class);
        assertEquals(c.getNombre(), model.getNombre());
        assertEquals(c.getIdCliente(), model.getIdCliente());
    }
}
