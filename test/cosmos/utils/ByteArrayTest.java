/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        byte[] in = new byte[3];
        in[0] = 0x02;
        in[1] = 0x04;
        in[2] = 0x08;
        return in;
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
        int expResult = 3;
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