package ru.mkardaev.di.withoutbeanannotation;

import org.springframework.stereotype.Component;

import ru.mkardaev.di.HelloWordDao;

@Component
public class HelloWordDaoImpl2 implements HelloWordDao
{

    @Override
    public String getHelloWord()
    {
        return "hello from dao2";
    }

}
