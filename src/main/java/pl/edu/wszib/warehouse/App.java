package pl.edu.wszib.warehouse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.warehouse.GUI.IGUI;

public class App {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfiguration.class).getBean(IGUI.class).showMainMenu();
    }
}
