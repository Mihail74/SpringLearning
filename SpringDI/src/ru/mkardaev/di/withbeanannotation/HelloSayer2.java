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
     * �.�. ���������� 2, �� ����� ������� ����� ���������� ���������.
     * 
     * @Qualifier � @Named - �� ������������, ����� ������������ �� ���������, �
     *            ������� ������� ��������������� ��� � java config'e. � ������
     *            ������ - @Qualifier
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
