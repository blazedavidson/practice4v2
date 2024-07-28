package ru.practice4v2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import ru.practice4v2.Main;
import ru.practice4v2.beans.App;
import ru.practice4v2.beans.AppControl;
import ru.practice4v2.beans.DateControl;
import ru.practice4v2.beans.FullNameControl;
import ru.practice4v2.model.*;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = "ru.practice4v2")
@EnableJpaRepositories( basePackageClasses = {Users.class, Login.class})

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private  ApplicationContext app;
    @Autowired
    private List<App> apps;

    @Override
    public void run(ApplicationArguments args) throws Exception {
 //       System.out.println("AppRunner");
 //       System.out.println(Arrays.asList(app.getBeanDefinitionNames()));
		Import dataImport = app.getBean(Import.class);
		Export dataExport = app.getBean(Export.class);
		FilesReadData FD = app.getBean(FilesReadData.class);

        dataImport.reading();
        for(int i = 0; i < apps.size() ; i++) {
            apps.get(i).validate();
        }
		dataExport.save();
    }
}
