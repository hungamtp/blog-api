package mroki.api.com.blog.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = {"mroki.api.com.blog.repository",
        "mroki.api.com.blog.security.jwt",
        "mroki.api.com.blog.repository.impl",
        "mroki.api.com.blog.repository.projection"},
    entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@PropertySource({"classpath:hibernate.properties"})
public class DataBaseTestConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("mroki.api.com.blog.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        DataSource dataSource = builder.setType(EmbeddedDatabaseType.H2).ignoreFailedDrops(true)
            .addScript("drop.sql").addScript("create.sql").addScript("insert.sql")
            .build();

        HikariConfig config = new HikariConfig();
        config.setDataSource(dataSource);
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(0);
        config.setIdleTimeout(60000);

        return new HikariDataSource(config);
    }

    private final Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = 1L;

            {
//                setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//                setProperty("hibernate.show_sql", "true");
//                setProperty("hibernate.format_sql", "true");
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
            }
        };
    }
}
