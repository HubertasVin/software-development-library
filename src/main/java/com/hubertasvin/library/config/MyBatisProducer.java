package com.hubertasvin.library.config;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
public class MyBatisProducer {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAlias("Book", Book.class);
        configuration.getTypeAliasRegistry().registerAlias("Publisher", Publisher.class);
        configuration.getTypeAliasRegistry().registerAlias("Author", Author.class);

        configuration.addMappers("com.hubertasvin.library.mapper");

        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
