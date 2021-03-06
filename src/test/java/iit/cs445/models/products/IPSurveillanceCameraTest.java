package iit.cs445.models.products;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class IPSurveillanceCameraTest {

    private IPSurveillanceCamera ipSurveillanceCamera;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(IPSurveillanceCamera.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws Exception {
        ipSurveillanceCamera = new IPSurveillanceCamera();
    }

    @After
    public void tearDown() throws Exception {
        ipSurveillanceCamera = null;
    }

    @Test
    public void toStringTest() throws Exception {
        assertNotNull(ipSurveillanceCamera.toString());
    }

}
