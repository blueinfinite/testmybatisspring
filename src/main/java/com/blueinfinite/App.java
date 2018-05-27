package com.blueinfinite;

import com.blueinfinite.config.ConfigDBInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) {
        System.out.println("App.main");
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);

        printer.pringMessage();
        System.out.println(context.getBean(ConfigDBInfo.class));
    }
}
