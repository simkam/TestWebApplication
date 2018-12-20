package org.jboss.qa.msimka.testwebapp;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CDIBean implements Serializable {

    @Inject
    private TestBean testBean;

    @Inject
    private SecondaryBean secondaryBean;

    @Resource
    ManagedExecutorService managedExecutorService;

    public void test() {
        System.out.println("test");
        for(int i = 0; i < 1000; i++) {
            System.out.println("test: " + i);
            managedExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    testBean.test();
                    secondaryBean.test();
                }
            });
        }
    }
}
