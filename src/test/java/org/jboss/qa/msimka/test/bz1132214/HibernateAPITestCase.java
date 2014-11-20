package org.jboss.qa.msimka.test.bz1132214;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.qa.msimka.test.HttpUtils;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

/**
 * @author Martin Simka
 */
@RunWith(Arquillian.class)
public class HibernateAPITestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(org.jboss.qa.msimka.testwebapp.Part.class)
                .addClass(org.jboss.qa.msimka.testwebapp.Product.class)
                .addClass(org.jboss.qa.msimka.testwebapp.TestServlet.class)
                .addAsResource("hibernate.cfg.xml", "hibernate.cfg.xml")
                .addAsManifestResource(new StringAsset("Dependencies: org.hibernate\n"), "MANIFEST.MF")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return war;
    }

    @RunAsClient
    @Test
    public void testOld() throws Exception {
        String stringUrl = "http://localhost:8080/test/TestServlet?delegator=old";
        System.out.println(stringUrl);
        HttpUtils.makeCallWithHttpClient(stringUrl, 200);
    }

    @Test
    @RunAsClient
    public void testNew() throws Exception {
        String stringUrl = "http://localhost:8080/test/TestServlet?delegator=new";
        System.out.println(stringUrl);
        HttpUtils.makeCallWithHttpClient(stringUrl, 200);
    }
}
