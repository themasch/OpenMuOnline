/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cosmos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author masch
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({   cosmos.utils.ByteArrayTest.class,
                        cosmos.packages.WelcomePackageTest.class,
                        cosmos.packages.MessageTypeC1Test.class})
public class cosmostests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}