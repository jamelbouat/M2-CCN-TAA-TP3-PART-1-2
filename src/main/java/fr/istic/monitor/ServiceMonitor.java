package fr.istic.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
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
	
	@Around("execution(* fr.istic.service.store.Store.pay(..))")
	public boolean checkForNullAccountIds(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		
		for (Object arg : args) {
			if (arg == null)
				return false;
		}
		
		proceedingJoinPoint.proceed();
		return true;
	}

//	@AfterReturning("execution(* fr.istic.service.store.Store.*(..))")
//	public void logServiceAccess(JoinPoint joinPoint) {
//		System.out.println("Completed: " + joinPoint);
//	}
}