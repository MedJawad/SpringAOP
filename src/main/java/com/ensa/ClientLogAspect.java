package com.ensa;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
public class ClientLogAspect {
    static Logger log = Logger.getLogger(ClientLogAspect.class.getName());

    //	@Pointcut("execution(* *..Client.*(*)) || execution(* *..Client.*(*)) ) && target(clt)")
//	public void operationBancaire(Client clt){}

    @Around("@annotation(com.ensa.LoggableClientOperation)")
    void logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("----------------------------------------------------------------");
        Client clt = (Client) joinPoint.getTarget();
		log.info("CLIENT "+clt.getNom()+" A FAIT L OPERATION << "+joinPoint.getSignature().getName() +" >> AVEC UN MONTANT DE "+joinPoint.getArgs()[0].toString());
		log.info("SOLDE AVANT "+clt.getCp().getSolde()+" ");
        double start = System.nanoTime();
		joinPoint.proceed(new Client[]{clt});
        double end = System.nanoTime();
        double duration = (end - start) / 1000000;
		log.info("SOLDE APRES "+clt.getCp().getSolde()+" ");
        log.info("L'operation a pris " + duration + "ms pour s'executer");
        log.info("----------------------------------------------------------------");

    }
}