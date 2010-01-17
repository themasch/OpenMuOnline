/*
 * OpenMuOnline - a open source mu online server
 * Copyright (C) 2009 Mark Schmale
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package cosmos.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author masch
 */
public class ByteArrayTest {

    ByteArray instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        // setup
        byte[] in = this.getTestData();
        this.instance = new ByteArray(in);
    }

    private byte[] getTestData()
    {
        byte[] data = {2,4,8};
        return data;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of append method, of class ByteArray.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        byte value = 0x42;
        int  len_before = this.instance.size();
        this.instance.append(value);
        int  len_after  = this.instance.size();
        Assert.assertEquals("new size is incorrect", len_before+1, len_after);

        byte last = this.instance.get(len_after-1);
        Assert.assertEquals("last element is incorrect", value, last);
    }

    /**
     * Test of clear method, of class ByteArray.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        this.instance.clear();
        Assert.assertEquals("size isn't zero after clear", 0, this.instance.size());
        Assert.assertEquals("getBytes returns an non-empty bytearray", 0, this.instance.getBytes().length);
    }

    /**
     * Test of get method, of class ByteArray.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 2;
        byte expResult = 0x08;
        byte result = instance.get(index);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class ByteArray.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        int expResult = this.getTestData().length;
        int result = instance.size();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of getBytes method, of class ByteArray.
     */
    @Test
    public void testGetBytes() {
        System.out.println("getBytes");
        byte[] expResult = this.getTestData();
        byte[] result = instance.getBytes();
        Assert.assertEquals(expResult.length, result.length);
        Assert.assertArrayEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ByteArray.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "020408";
        String result = this.instance.toString();
        Assert.assertEquals(expResult, result);
        this.instance.clear();
        expResult = "";
        result = this.instance.toString();
        Assert.assertEquals(expResult, result);
    }

}