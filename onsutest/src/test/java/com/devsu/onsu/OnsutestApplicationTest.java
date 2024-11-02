package com.devsu.onsu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.devsu.onsu.controller.ClienteController;




@SpringBootTest
public class OnsutestApplicationTest {

    @Autowired
	private ClienteController clienteController;

    @Test
    void contextLoads() {
        assertNotNull(clienteController);
    }
}