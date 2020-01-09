package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@SpringBootApplication
public class PalTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public TimeEntryRepository timeEntryRepository() {
        return new JdbcTimeEntryRepository(dataSource);
    }
    @Bean
    public Person person() {
        return new Person();
    }
}
