package iit.cs445.models.services;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CustomServiceTest {

    private CustomService customService;

    @Before
    public void setUp() throws Exception {
        customService = new CustomService();
    }

    @After
    public void tearDown() throws Exception {
        customService = null;
    }

    @Test
    public void toStringTest() throws Exception {
        assertNotNull(customService.toString());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CustomService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
