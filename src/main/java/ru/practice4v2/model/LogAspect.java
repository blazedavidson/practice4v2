package ru.practice4v2.model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.*;

@Aspect
@Component
public class LogAspect {
    @Value("${app.log}")
    String logspth;

    @Around("@annotation(ru.practice4v2.model.LogTransformation)")

    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {

        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        Instant startInstant = Instant.now();
        String startTimeS = DTF.format(startInstant);
        Object[] args = joinPoint.getArgs();
        String argsStr = Arrays.toString(joinPoint.getArgs());

        Object obj = joinPoint.proceed(args);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogTransformation annotation = method.getAnnotation(LogTransformation.class);
        String fileName = annotation.filename();

        Handler filehandlers = new FileHandler(logspth + fileName);
        filehandlers.setFormatter(new SimpleFormatter());
        Logger llogs = Logger.getLogger(this.getClass().getName());
        llogs.addHandler(filehandlers);
        llogs.setLevel(Level.INFO);
        Instant endInstant = Instant.now();
        String endTimeS = DTF.format(endInstant);

        String message = String.format("start: %s. end: %s. %s. %s. %s\n", startTimeS, endTimeS, joinPoint.getSourceLocation().getWithinType().getName(), argsStr, obj);
        llogs.info(message);

        filehandlers.close();

        return obj;
    }
}
