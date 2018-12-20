package org.jboss.qa.msimka.testwebapp;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
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
        AtomicBoolean first = new AtomicBoolean(true);
        AtomicBoolean persist = new AtomicBoolean(true);
        for(int i = 0; i < 40; i++) {
            managedExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    if (first.getAndSet(!first.get())) {
                        if (persist.getAndSet(!persist.get())) {
                            testBean.persist();
                        } else {
                            testBean.select();
                        }
                    } else {
                        if (persist.getAndSet(!persist.get())) {
                            secondaryBean.persist();
                        } else {
                            secondaryBean.select();
                        }
                    }
                }
            });
        }
    }
}
