package ru.ligaintenship.prerevolutionarytinder.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ligaintenship.prerevolutionarytinder.SpringJdbcConnectionProvider;
import ru.ligaintenship.prerevolutionarytinder.domain.UserCreator;
import ru.ligaintenship.prerevolutionarytinder.domain.UserDeleter;
import ru.ligaintenship.prerevolutionarytinder.domain.UserFinder;
import ru.ligaintenship.prerevolutionarytinder.domain.UserUpdater;
import ru.ligaintenship.prerevolutionarytinder.rest.Controller;

@Configuration
public class ServerConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/tinder_db");
        ds.setUsername("admin");
        ds.setPassword("qwertyJ4");
        return ds;
    }

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:/db/changelog-master.yaml");
        return liquibase;
    }

    @Bean
    SpringJdbcConnectionProvider springJdbcConnectionProvider(JdbcTemplate jdbcTemplate) {
        return new SpringJdbcConnectionProvider(jdbcTemplate);
    }

    @Bean
    UserCreator userCreator(SpringJdbcConnectionProvider provider) {
        return new UserCreator(provider);
    }

    @Bean
    UserDeleter userDeleter(SpringJdbcConnectionProvider provider) {
        return new UserDeleter(provider);
    }

    @Bean
    UserFinder userFinder(SpringJdbcConnectionProvider provider) {
        return new UserFinder(provider);
    }

    @Bean
    UserUpdater userUpdater(SpringJdbcConnectionProvider provider) {
        return new UserUpdater(provider);
    }

}
