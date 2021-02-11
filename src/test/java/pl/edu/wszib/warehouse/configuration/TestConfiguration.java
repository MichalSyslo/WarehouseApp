package pl.edu.wszib.warehouse.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"pl.edu.wszib.warehouse.controllers",
        "pl.edu.wszib.warehouse.services.impl",
        "pl.edu.wszib.warehouse.session"})
public class TestConfiguration {

}

