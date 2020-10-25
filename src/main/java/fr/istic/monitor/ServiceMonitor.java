package fr.istic.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

	@Before("execution(* fr.istic.service.*.*.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		
		System.out.println("\nClass Name = " + joinPoint.getTarget().getClass().getName());
		System.out.println("Called method = " + joinPoint.getSignature().getName());
	}

//	@AfterReturning("execution(* fr.istic.service.store.Store.*(..))")
//	public void logServiceAccess(JoinPoint joinPoint) {
//		System.out.println("Completed: " + joinPoint);
//	}
}