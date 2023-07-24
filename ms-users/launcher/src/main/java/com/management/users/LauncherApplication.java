package com.management.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.management.users")
@EntityScan("com.management.users.infrastructure.entity")
public class LauncherApplication {
  public static void main(String[] args) {
    SpringApplication.run(LauncherApplication.class, args);
  }
  
}
