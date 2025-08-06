package ra.com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra.com.controller", "ra.com.service.imp", "ra.com.repository.imp"})
@EnableTransactionManagement
public class AppConfig {
    //1. Cấu hình ViewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    //2. Cấu hình datasource kết nối với CSDL
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //DRIVER
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //URL
        dataSource.setUrl("jdbc:mysql://localhost:3306/Hibernate_demo");
        dataSource.setUsername("root");
        dataSource.setPassword("1234$");
        return dataSource;
    }

    //3. Cấu hình hibernate properties
    public Properties addionalProperties() {
        Properties properties = new Properties();
        //Cơ chế (mode) làm việc với CSDL: create, create-drop, update, validate
        /*
         * create: Khi ứng dụng chạy, xóa tất cả các bảng trong db và tạo lại các bảng mới theo entity --> dữ liệu sẽ mất hết khi ứng dụng chạy
         * create-drop: khi ứng dụng chạy, xóa hết các bảng và tạo mới, ứng dụng tắt xóa tất cả các bảng
         * update: ứng dụng chạy kiểm tra các bảng trong db so với các entity, nếu thiếu entity hoặc thuộc tính entity thì sẽ tạo thêm, thừa không làm gì cả
         * validate: kiểm tra cấu trúc các bảng trong db có giống với các entity không
         * */
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8InnoDBDialect");
        //Lần đầu tiên sử dụng Hibernate thì nó thường sinh SAN database --> khi sinh bảng nó sẽ lock bảng đó
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

    //Hibernate: quản lý các entity --> entityManager --> entitymanagerFactory
    //entityManger --> session --> hql
    //entity : table
    //4. Cấu hình EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ra.com.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(addionalProperties());
        return entityManagerFactory;
    }

    //5. Cấu hình EntityManager
    //@Qualifier tương tự @Autowird
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    //6. Cấu hình transaction
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //7. Cấu hình bean upload file
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //Hạn chế dung lượng của file truyền trong request: 5Mb
        multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        return multipartResolver;
    }
}
