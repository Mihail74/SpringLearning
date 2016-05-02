package ru.mkardaev.di.withoutbeanannotation;

import org.springframework.stereotype.Component;

import ru.mkardaev.di.HelloWordDao;

@Component("helloDao")
public class HelloWordDaoImpl1 implements HelloWordDao
{
    @Override
    public String getHelloWord()
    {
        return "hello from dao1";
    }

}
