package com.ensa;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Trigger implements
        ApplicationListener<ContextRefreshedEvent> {

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("APPLICATION STARTED");
        Client clt = new Client();
        clt.setNom("JAWAD");
        Compte cpt = new Compte();
        clt.setCp(cpt);
        clt.verser(10000.0);
        clt.verser(5000.0);
        clt.retirer(9000.0);
        System.out.println("CLIENT "+clt.getNom() + " " +clt.getCp().getSolde());

        System.out.println("APPLICATION FINISHED");

    }
}