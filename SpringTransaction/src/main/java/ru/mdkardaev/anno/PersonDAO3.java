package ru.mdkardaev.anno;

import java.sql.Types;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.mdkardaev.Person;

public class PersonDAO3
{
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(Person person)
    {
        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person.firstName, person.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            getJdbcTemplate().update(inserQuery, params, types);

            System.out.println("Person inserted to the table");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Transactional
    public void insertWithException(Person person)
    {
        String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
        Object[] params = new Object[] { person.firstName, person.lastName };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        getJdbcTemplate().update(inserQuery, params, types);

        System.out.println("Person inserted to the table");
        throw new RuntimeException();
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertWithInnerRequiredNew(Person person1, Person person2)
    {

        String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
        Object[] params = new Object[] { person1.firstName, person1.lastName };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        jdbcTemplate.update(inserQuery, params, types);
        System.out.println("Person1 inserted to the table");

        insertRequiredNewWithRuntimeException(person2);

    }

    @Transactional
    public void insertWithNested(Person person1, Person person2)
    {
        String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
        Object[] params = new Object[] { person1.firstName, person1.lastName };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        jdbcTemplate.update(inserQuery, params, types);
        System.out.println("Person1 inserted to the table");

        insertNested(person2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    private void insertNested(Person person)
    {
        String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
        Object[] params = new Object[] { person.firstName, person.lastName };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        jdbcTemplate.update(inserQuery, params, types);

        System.out.println("Nesdet transaction commit");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void insertRequiredNewWithRuntimeException(Person person)
    {
        String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
        Object[] params = new Object[] { person.firstName, person.lastName };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        jdbcTemplate.update(inserQuery, params, types);

        System.out.println("Nested transaction commit");
        throw new RuntimeException();
    }

}
