package no.westerdals.westbook.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class PerformanceTester
{
    public Object testPerformance(ProceedingJoinPoint point) throws Throwable
    {
        StopWatch stopWatch = new StopWatch();
        try
        {
            stopWatch.start(point.toShortString());
            return point.proceed();
        }
        finally
        {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
