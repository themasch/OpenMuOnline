/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.packages;

import cosmos.utils.ByteArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author masch
 */
public class MessageTypeC1Test {

    public MessageTypeC1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class MessageTypeC1.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        MessageTypeC1 instance = new MessageTypeC1();
        byte[] defaultValue = {(byte)0xC1, (byte)0x04, (byte)0x00, (byte)0x00};
        byte[] data         = {(byte)0x0A, (byte)0x0B, (byte)0x0C, (byte)0x0D};
        byte[] dataValue    = {(byte)0xC1, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x0A, (byte)0x0B, (byte)0x0C, (byte)0x0D};
        ByteArray expResult = new ByteArray(defaultValue);
        ByteArray result = instance.get();
        assertTrue(expResult.equals(result));
        expResult = new ByteArray(dataValue);
        instance.setData(data);
        result = instance.get();

        assertTrue(instance.toString(), expResult.equals(result));
    }

    /**
     * Test of getLenght method, of class MessageTypeC1.
     */
    @Test
    public void testGetLenght() {
        System.out.println("getLenght");
        MessageTypeC1 instance = new MessageTypeC1();
        int expResult = 4;
        int result = instance.getLenght();
        assertEquals(expResult, result);
    }

}