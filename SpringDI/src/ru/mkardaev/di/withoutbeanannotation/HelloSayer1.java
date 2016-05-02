package ru.mkardaev.di.withoutbeanannotation;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import ru.mkardaev.di.HelloSayer;
import ru.mkardaev.di.HelloWordDao;

/**
 * ������ ��������� ������������
 * 
 *
 */
// @Component(value = "helloSayer")
@Named(value = "helloSayer")
public class HelloSayer1 implements HelloSayer
{

    @Inject
    public HelloSayer1(@Named("helloWordDaoImpl2") HelloWordDao daoFromConstructor)
    {
        this.daoFromConstrucror = daoFromConstructor;
    }

    /**
     * �.�. ���������� 2, �� ����� ������� ����� ���������� ���������.
     * 
     * @Qualifier � @Named - ������������, �.�. HelloWordDaoImpl1 ��������
     *            ���������� @Component(componentName)
     */
    @Inject
    @Qualifier("helloDao")
    // @Named("helloDao")
    private HelloWordDao dao;

    private HelloWordDao daoFromConstrucror;

    @Override
    public void sayHello()
    {
        System.out.println("Sayer1");
        System.out.println("Hello from dao from constructor: " + daoFromConstrucror.getHelloWord());
        System.out.println(dao.getHelloWord());
    }
}
