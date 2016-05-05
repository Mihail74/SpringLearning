package ru.mdkardaev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{

    public static void main(String[] args) throws ClassNotFoundException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("sprint-context.xml");
        PersonDAO dao = context.getBean("personDAO", PersonDAO.class);

        dao.insert(new Person("A", "A"));
        dao.insertWithExceptionAfterCommit(new Person("B", "B"));
        dao.insertWithInnerRequiredNew(new Person("C", "C"), new Person("D", "D"));
        dao.insertWithNested(new Person("E", "E"), new Person("F", "F"));
    }

}
