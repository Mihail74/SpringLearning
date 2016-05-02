package ru.mkardaev.di.withbeanannotation;

import ru.mkardaev.di.HelloWordDao;

public class HelloWordDaoImpl3 implements HelloWordDao
{
    @Override
    public String getHelloWord()
    {
        return "hello from dao3";
    }

}
