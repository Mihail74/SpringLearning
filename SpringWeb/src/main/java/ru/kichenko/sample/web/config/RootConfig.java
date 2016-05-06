/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.config;

import com.googlecode.jmapper.JMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kichenko.sample.web.model.Student;
import ru.kichenko.sample.web.model.dto.StudentDto;

/**
 * RootConfig
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Configuration
@ComponentScan("ru.kichenko")
public class RootConfig {

    @Bean
    public JMapper<StudentDto, Student> mapper() {
        return new JMapper(StudentDto.class, Student.class);
    }
}
