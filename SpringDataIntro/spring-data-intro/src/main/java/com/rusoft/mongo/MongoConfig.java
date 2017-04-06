package com.rusoft.mongo;

import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "com.rusoft.mongo")
public class MongoConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongodForTestsFactory testsFactory = mongodForTestsFactory();
        Mongo mongo = testsFactory.newMongo();
        String databaseName = testsFactory.newDB(mongo).getName();

        return new SimpleMongoDbFactory(mongo, databaseName);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean(destroyMethod = "shutdown")
    public MongodForTestsFactory mongodForTestsFactory() throws Exception {
        return MongodForTestsFactory.with(Version.Main.PRODUCTION);
    }

    @Autowired
    private CrudEmployeeRepository employees;

    @Bean
    InitializingBean init() {
        return () -> {
            employees.save(Arrays.asList(
                    new Employee("Ivan", "Ivanov", LocalDate.of(1950, 1,1)),
                    new Employee("Petr", "Petrov", LocalDate.of(1960, 2,2)),
                    new Employee("Nikolay", "Sidorov", LocalDate.of(1970, 3,3)),
                    new Employee("Vasiliy", "Vasiliev", LocalDate.of(1980, 4,4)),
                    new Employee("Anton", "Antonov", LocalDate.of(1990, 5,5)),
                    new Employee("Ivan", "Petrov", LocalDate.of(1965, 2,2)),
                    new Employee("Ivan", "Sidorov", LocalDate.of(1975, 3,3))
            ));
        };
    }
}
