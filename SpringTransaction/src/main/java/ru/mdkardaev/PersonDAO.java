package ru.mdkardaev;

import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class PersonDAO
{

    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager platformTransactionManager;

    public void insert(Person person)
    {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person.firstName, person.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            platformTransactionManager.commit(ts);
            System.out.println("Person inserted to the table");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            platformTransactionManager.rollback(ts);
        }
    }

    public void insertWithExceptionAfterCommit(Person person)
    {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person.firstName, person.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            platformTransactionManager.commit(ts);
            System.out.println("Person inserted to the table");
            throw new RuntimeException();
        }
        catch (Exception e)
        {
            platformTransactionManager.rollback(ts);
        }
    }

    /**
     * Внутри транзации вызывается другая транзация с PROPAGATION_NESTED. Т.е.
     * внутренняя транзация цепляется к внешней
     */
    public void insertWithNested(Person person1, Person person2)
    {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person1.firstName, person1.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            insertNestedWithRuntimeException(person2);

            platformTransactionManager.commit(ts);
            System.out.println("Person1 inserted to the table");
        }
        catch (Exception e)
        {
            platformTransactionManager.rollback(ts);
        }
    }

    /**
     * Внутри транзации вызывается другая транзация с PROPAGATION_REQUIRES_NEW.
     * Т.е. внутренняя транзация работает полностью независимо от внешней
     */
    public void insertWithInnerRequiredNew(Person person1, Person person2)
    {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person1.firstName, person1.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            insertInnerRequiredNewWithRuntimeException(person2);

            platformTransactionManager.commit(ts);
            System.out.println("Person1 inserted to the table");
        }
        catch (Exception e)
        {
            platformTransactionManager.rollback(ts);
        }
    }

    private void insertNestedWithRuntimeException(Person person)
    {
        DefaultTransactionDefinition td = new DefaultTransactionDefinition();
        td.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person.firstName, person.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            platformTransactionManager.commit(ts);
            System.out.println("Nesdet transaction commit");
        }
        catch (DataAccessException e)
        {
            platformTransactionManager.rollback(ts);
        }
        throw new RuntimeException();
    }

    private void insertInnerRequiredNewWithRuntimeException(Person person)
    {
        DefaultTransactionDefinition td = new DefaultTransactionDefinition();
        td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus ts = platformTransactionManager.getTransaction(td);

        try
        {
            String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
            Object[] params = new Object[] { person.firstName, person.lastName };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(inserQuery, params, types);

            platformTransactionManager.commit(ts);
            System.out.println("Nesdet transaction commit");
        }
        catch (DataAccessException e)
        {
            platformTransactionManager.rollback(ts);
        }
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

    public PlatformTransactionManager getPlatformTransactionManager()
    {
        return platformTransactionManager;
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager)
    {
        this.platformTransactionManager = platformTransactionManager;
    }

}
