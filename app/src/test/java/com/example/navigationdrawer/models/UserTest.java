package com.example.navigationdrawer.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testGettersAndSetters() {
        user.setName("John Smith");
        user.setEmail("jsmith@gmail.com");
        user.setPassword("ab4dyu3d");
        user.setOld_password("jri40fjs");
        user.setNew_password("mkrheof9");

        assertEquals("John Smith", user.getName());
        assertEquals("jsmith@gmail.com", user.getEmail());
    }
}