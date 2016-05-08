package ru.mdkardaev.tt;

import java.sql.Types;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.mdkardaev.Person;

public class PersonDAO2
{
    @Inject
    private JdbcTemplate jdbcTemplate;
    @Inject
    private TransactionTemplate transactionTemplate;

    public void insert(Person person)
    {
        String result = transactionTemplate.execute(new TransactionCallback<String>()
        {
            @Override
            public String doInTransaction(TransactionStatus status)
            {
                String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
                Object[] params = new Object[] { person.firstName, person.lastName };
                int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
                jdbcTemplate.update(inserQuery, params, types);

                // if (true)
                // throw new RuntimeException();

                // status.setRollbackOnly();

                return "Person inserted";

            }
        });
        System.out.println(result);
    }

    public void insertWithInnerRequiredNew(Person person1, Person person2)
    {
        transactionTemplate.execute(new TransactionCallbackWithoutResult()
        {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status)
            {
                String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
                Object[] params = new Object[] { person1.firstName, person1.lastName };
                int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
                jdbcTemplate.update(inserQuery, params, types);
                System.out.println("Person1 inserted to the table");

                insertRequiredNewWithRuntimeException(person2);
            }
        });
    }

    public void insertWithNested(Person person1, Person person2)
    {
        transactionTemplate.execute(new TransactionCallbackWithoutResult()
        {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status)
            {
                String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
                Object[] params = new Object[] { person1.firstName, person1.lastName };
                int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
                jdbcTemplate.update(inserQuery, params, types);
                System.out.println("Person1 inserted to the table");

                insertNestedWithRuntimeException(person2);

            }
        });

    }

    private void insertNestedWithRuntimeException(Person person)
    {
        try
        {
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
            transactionTemplate.execute(new TransactionCallbackWithoutResult()
            {

                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status)
                {

                    String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
                    Object[] params = new Object[] { person.firstName, person.lastName };
                    int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
                    jdbcTemplate.update(inserQuery, params, types);

                    System.out.println("Nesdet transaction commit");
                }
            });
            throw new RuntimeException();
        }
        finally
        {
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        }
    }

    private void insertRequiredNewWithRuntimeException(Person person)
    {
        try
        {
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionTemplate.execute(new TransactionCallbackWithoutResult()
            {

                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status)
                {

                    String inserQuery = "INSERT INTO person(firstname, lastname) VALUES(?,?)";
                    Object[] params = new Object[] { person.firstName, person.lastName };
                    int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
                    jdbcTemplate.update(inserQuery, params, types);

                    System.out.println("Nesdet transaction commit");
                }
            });
            throw new RuntimeException();
        }
        finally
        {
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        }
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TransactionTemplate getTransactionTemplate()
    {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate)
    {
        this.transactionTemplate = transactionTemplate;
    }
}
