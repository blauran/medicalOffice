package ro.upt.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ro.upt.project.models.Role;
import ro.upt.project.models.Specialization;
import ro.upt.project.models.User;
import ro.upt.project.repositories.UserRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}

