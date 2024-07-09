package oop4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* oop4.Reader.*(*))")
    public void BeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getSignature().toString() +
                "\nArgs: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Pointcut("execution(* oop4.Reader.readFile(*))")
    public void readFilePointcut() {
    }

    @Pointcut("execution(* oop4.Reader.get*(..))")
    public void samplesPointcut() {
    }

    @Around("readFilePointcut() && args(file)")
    public Abiturient[] AroundAdvice(ProceedingJoinPoint pjp, File file) throws Throwable {
        Abiturient[] res;// = new Abiturient[0];
        Object[] args = {file};
        long startTime = System.currentTimeMillis();
        try {
            res = (Abiturient[]) pjp.proceed(args);
        } catch (FileNotFoundException e) {
            System.out.println("Around Before: file does not exist.");
            res = new Abiturient[0];
        } finally {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Around after: Time to read - " + totalTime + "ms");
        }
        return res;
    }

    @AfterReturning(value = "samplesPointcut()", returning = "size")
    public void writeSampleSize(JoinPoint jp, int size) {
        System.out.println("AfterReturning: method signature - " + jp.getSignature());
        System.out.println("AfterReturning: sample size - " + size);
    }

    @AfterThrowing(value = "samplesPointcut()", throwing = "e")
    public void afterThrowsException(JoinPoint jp, Exception e) {
        System.out.println("AfterThrowing: method signature - " + jp.getSignature());
        System.out.println("AfterThrowing: exception - " + e);
    }
}



