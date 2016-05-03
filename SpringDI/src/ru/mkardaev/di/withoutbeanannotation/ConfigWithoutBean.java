package ru.mkardaev.di.withoutbeanannotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
// or @ComponentScan(basePackages = "ru.mkardaev.di.withoutbeanannotation")
// при отсутствии пакета скан начинается с пакета, в котором найден этот класс
@Configuration
public class ConfigWithoutBean
{

}
