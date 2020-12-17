package com.pipecode.topshop.configuration;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Generador HIBA
 */
@Configuration
@EnableTransactionManagement
public class PersistenciaConfiguracion {

    private static final String ENTITIES_PACKAGE = "com.pipecode.topshop.configuration.*";
    private static final String PERSISTENCE_UNIT_NAME = "OracleUnit";

//    private static final String JDBCPOOL = "jdbc/topshop";

    private static Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.cache.use_second_level_cache", "false");
        return props;
    }

    public DataSource dataSource() throws NamingException {
//        DataSource dataSource = InitialContext.<DataSource>doLookup(JDBCPOOL);
// en caso que se desee no configurar un pool
        DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.95.1.20:3306/TAD_SHOP");
        dataSource.setUsername("gc");
        dataSource.setPassword("desarrollo");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
            throws NamingException {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setPersistenceUnitName("persistenceUnit");
        lcemfb.setDataSource(dataSource());
        lcemfb.setPackagesToScan(ENTITIES_PACKAGE);
        lcemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        lcemfb.setJpaProperties(jpaProperties());
        return lcemfb;
    }

    @Bean
    public PlatformTransactionManager transactionManagerOracle()
            throws NamingException {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jtm;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
