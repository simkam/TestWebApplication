package org.jboss.qa.msimka.test.bz1132214;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Martin Simka
 */
@RunWith(Arquillian.class)
public class EjbNewTestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(org.jboss.qa.msimka.testwebapp.Part.class)
                .addClass(org.jboss.qa.msimka.testwebapp.Product.class)
                .addClass(org.jboss.qa.msimka.testwebapp.TestBean.class)
                .addAsResource("META-INF/persistence-new.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return war;
    }

    @RunAsClient
    @Test
    public void test() throws Exception {
    }

}
