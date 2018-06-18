package StuProjAllocation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig {
    @Bean
    public DriverManagerDataSource dataSource() {       
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/project");
		ds.setUsername("root");
		ds.setPassword("miewuefi");
    	return ds;
    }
}


