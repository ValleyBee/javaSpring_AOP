// @POINTCUTS, ADVICES
package aop.aspects;

import aop.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LoggingAspect {
    // call method in exact Class
    @Before("execution(public void aop.Library.getBook())")  // if type Class then full path getBook(aop.Library)
    public void beforeGetBookAdvice() {
        System.out.println("beforeGetBookAdvice:  attempt to get a book from Library.");
    }

    @Before("execution(public void getBook(String))")
    public void beforeGetBookAdvice2() {
        System.out.println("beforeGetBookAdvice2:  attempt to get a book from UniLibrary.");
    }


    // call any method begins get* scan whole package

//    @Before("execution(public void get*())")
//    public void anyGetMethodAdvice() {
//        System.out.println("anyGetMethodAdvice: calling before any get* methods");
//    }

    // any methods with one any arg

    //    @Before("execution(public void * (*))")  // (..)) any qtty args
//    public void anyMethodAnyArgAdvice() {
//        System.out.println("anyMethodAnyArgAdvice: calling before any methods with one any arg");
//
//    }
    @Order(6)
    @Before("execution(public void returnBook())")

    public void beforeReturnBookAdvice() {
        System.out.println("beforeReturnBookAdvice: attempt to return a book.");
        System.out.println("---------------------------------------------------");
    }



    //Pointcuts form Class MyPointcuts
    @Before("aop.aspects.MyPointcuts.allGetMethods()")
    public void beforeGetLoggingAdvice() {
        System.out.println("beforeGetLoggingAdvice: Logged attempt to do");
        System.out.println("---------------------------------------------------");
    }

    @Before("aop.aspects.MyPointcuts.allAddMethods()")
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodSignature.getName().equals("addBook")) {
            Object[] arg = joinPoint.getArgs();
            for (Object obj : arg) {
                if (obj instanceof Book) {
                    Book myBook = (Book) obj;
                    System.out.println("info about book" +
                            ", name : " + myBook.getName() +
                            ", author : " + myBook.getAuthor() +
                            ", YearOfIssue : " + myBook.getYearOfIssue());

                }
                else if (obj instanceof String){
                    System.out.println("name,how add the book : " + ((String) obj).toUpperCase());
                }
            }
        }

        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName() = " + methodSignature.getName());


        System.out.println("beforeAddLoggingAdvice: Logged attempt to do");
        System.out.println("---------------------------------------------------");
    }


    // POINTCUT II
    @Pointcut("execution(* aop.UniLibrary.get* (*))")
    public void allGetMethodsUniLib() {
    }

    @Before("allGetMethodsUniLib()")
    public void beforeGetLoggingAdviceUnilib() {

        System.out.println("beforeGetLoggingAdviceUnilib: Logged writing log #1");
    }

    // POINTCUT III
    @Pointcut("execution(* aop.UniLibrary.return* ())")
    public void allReturnMethodsUnilib() {
    }

    @Before("allReturnMethodsUnilib()")
    public void beforeReturnLoggingAdviceUnilib() {

        System.out.println("beforeReturnLoggingAdviceUnilib: writing log #2");
    }

// Combination of Pointcuts II and III
//
//    @Pointcut("allGetMethodsUniLib() || allReturnMethodsUnilib() ")
//    private void allGetAndReturnMethodsUniLib(){  }
//
//    @Before("allGetAndReturnMethodsUniLib()")
//    public void beforeGetAndReturnMethodsUniLib(){
//    System.out.println("beforeGetAndReturnMethodsUniLib: writing log #3");
//}


}
