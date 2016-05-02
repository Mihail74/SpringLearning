package ru.mkardaev.di.withbeanannotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.mkardaev.di.HelloSayer;
import ru.mkardaev.di.HelloWordDao;

@Configuration
public class ConfigWithBean
{
    @Bean
    public HelloSayer helloSayer()
    {
        return new HelloSayer2(helloWordDao3());
    }

    @Bean
    public HelloWordDao helloWordDao3()
    {
        return new HelloWordDaoImpl3();
    }

    @Bean
    @Qualifier("helloDao4")
    public HelloWordDao helloWordDao4()
    {
        return new HelloWordDaoImpl4();
    }
}
