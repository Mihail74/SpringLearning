package ru.mkardaev.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.mkardaev.di.withbeanannotation.ConfigWithBean;
import ru.mkardaev.di.withoutbeanannotation.ConfigWithoutBean;

public class Main
{

    public static void configWithoutBean()
    {
        /**
         * Пример конфигурации без использования @Bean аннотации. Конфигурация
         * только на аннотациях
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigWithoutBean.class);
        HelloSayer sayer = (HelloSayer) context.getBean("helloSayer");
        sayer.sayHello();
    }

    public static void configWithBean()
    {
        /**
         * Пример конфигурации с использованием @Bean аннотации.
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigWithBean.class);
        HelloSayer sayer = context.getBean(HelloSayer.class);
        sayer.sayHello();
    }

    public static void main(String[] args)
    {
        configWithoutBean();
    }

}
