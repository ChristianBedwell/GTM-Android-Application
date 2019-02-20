package com.example.navigationdrawer.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerRequestTest {

    ServerRequest serverRequestTest;

    @Before
    public void setUp() {
        serverRequestTest = new ServerRequest();
    }

    @After
    public void tearDown() {
        serverRequestTest = null;
    }

    @Test
    public void testGettersAndSetters() {
        serverRequestTest.setOperation("logout");
        assertEquals("logout", serverRequestTest.getOperation());
    }
}