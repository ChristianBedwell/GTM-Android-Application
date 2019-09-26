package com.example.navigationdrawer.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerResponseTest {

    ServerResponse serverResponseTest;

    @Before
    public void setUp() {
        serverResponseTest = new ServerResponse();
    }

    @After
    public void tearDown() {
        serverResponseTest = null;
    }

    @Test
    public void testGettersAndSetters() {

        serverResponseTest.setResult("success");
        serverResponseTest.setMessage("Login Successful!");
        assertEquals("success", serverResponseTest.getResult());
        assertEquals("Login Successful!", serverResponseTest.getMessage());
    }
}