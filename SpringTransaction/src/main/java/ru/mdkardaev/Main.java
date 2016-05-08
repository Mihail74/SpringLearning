package ru.mdkardaev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.mdkardaev.anno.AppConfig2;
import ru.mdkardaev.anno.PersonDAO3;
import ru.mdkardaev.tt.AppConfig;
import ru.mdkardaev.tt.PersonDAO2;

public class Main
{

    public static void transacionManager()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("sprint-context.xml");
        PersonDAO dao = context.getBean("personDAO", PersonDAO.class);

        dao.insert(new Person("A", "A"));
        dao.insertWithExceptionAfterCommit(new Person("B", "B"));
        dao.insertWithInnerRequiredNew(new Person("C", "C"), new Person("D", "D"));
        dao.insertWithNested(new Person("E", "E"), new Person("F", "F"));
    }

    public static void transacionTemplate()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonDAO2 dao = context.getBean(PersonDAO2.class);
        dao.insert(new Person("A", "A"));
        dao.insertWithInnerRequiredNew(new Person("C", "C"), new Person("D", "D"));
        dao.insertWithNested(new Person("E", "E"), new Person("F", "F"));
    }

    public static void annotations()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        PersonDAO3 dao = context.getBean(PersonDAO3.class);
        dao.insert(new Person("A", "A"));
        dao.insertWithException(new Person("B", "B"));
        dao.insertWithInnerRequiredNew(new Person("C", "C"), new Person("D", "D"));
        dao.insertWithNested(new Person("E", "E"), new Person("F", "F"));
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        // transacionManager();
        // transacionTemplate();
        // annotations();
    }

}
