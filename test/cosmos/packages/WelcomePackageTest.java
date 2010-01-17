/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos.packages;

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
public class WelcomePackageTest {

    public WelcomePackageTest() {
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

    @Test
    public void testGet() {
        IMessage msg     = new WelcomePackage();
        byte[] is        = msg.get().getBytes();
        byte[] expected  = { (byte)0xC1, (byte)0x04, (byte)0x00, (byte)0x01 };
        Assert.assertArrayEquals(expected, is);
    }

    @Test
    public void testGetData() {
        IMessage msg = new WelcomePackage();
        byte[]   is  = msg.getData().getBytes();
        Assert.assertEquals(0, is.length);
    }
}