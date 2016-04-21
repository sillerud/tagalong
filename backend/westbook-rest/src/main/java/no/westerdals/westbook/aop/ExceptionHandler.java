package no.westerdals.westbook.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.UUID;

public class ExceptionHandler
{

    public String handleException(Throwable ex)
    {
        String uuid = UUID.randomUUID().toString();
        System.out.println("An error occurred with the id " + uuid);
        ex.printStackTrace();
        return uuid;
    }

    public Object runUnsafe(ProceedingJoinPoint joinPoint)
    {
        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable t)
        {
            return Arrays.asList(handleException(t));
        }
    }
}
