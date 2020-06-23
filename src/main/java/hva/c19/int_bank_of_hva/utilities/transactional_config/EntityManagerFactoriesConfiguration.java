//package hva.c19.int_bank_of_hva.utilities.transactional_config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//@Configuration
//public class EntityManagerFactoriesConfiguration {
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean emf() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setPackagesToScan(new String[] {"hva.c19.int_bank_of_hva.service"});
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return emf;
//    }
//}