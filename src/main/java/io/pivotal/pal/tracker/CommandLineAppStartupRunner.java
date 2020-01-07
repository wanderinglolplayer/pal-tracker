package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private Person person;

    @Override
    public void run(String...args) throws Exception {
        System.out.println("Age: " + person.age + ", Name: " + person.name);
    }

}
