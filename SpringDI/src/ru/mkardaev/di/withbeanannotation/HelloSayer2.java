package ru.mkardaev.di.withbeanannotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import ru.mkardaev.di.HelloSayer;
import ru.mkardaev.di.HelloWordDao;

public class HelloSayer2 implements HelloSayer
{
    @Inject
    public HelloSayer2(HelloWordDao daoFromConstructor)
    {
        this.daoFromConstrucror = daoFromConstructor;
    }

    /**
     * Т.к. реализации 2, то нужно указать какую реализацию инжектить.
     * 
     * @Qualifier и @Named - НЕ эквивалентны, нужно использовать ту аннотацию, с
     *            помощью которой устанавливалось имя в java config'e. В данном
     *            случае - @Qualifier
     * 
     */
    @Inject
    @Qualifier("helloDao4")
    // @Named("helloDao4")
    private HelloWordDao dao;

    private HelloWordDao daoFromConstrucror;

    @Override
    public void sayHello()
    {
        System.out.println("Sayer2");
        System.out.println("Hello from dao from constructor: " + daoFromConstrucror.getHelloWord());
        System.out.println(dao.getHelloWord());
    }
}
