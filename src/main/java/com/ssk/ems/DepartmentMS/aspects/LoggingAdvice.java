package com.ssk.ems.DepartmentMS.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution(* com.ssk.ems.DepartmentMS.*.*.*(..) )")
    public void myPointCut() {

    }
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName().toString();
        Object[] objArry = proceedingJoinPoint.getArgs();
        logger.info("Method Invoked: " + className + " Method Name : " + methodName +
                " Arguments: " + objectMapper.writeValueAsString(objArry));
        Object object = proceedingJoinPoint.proceed();
        logger.info("Class Name: " + className + " Response : " +
                objectMapper.writeValueAsString(objArry));

        return object;
    }


}
