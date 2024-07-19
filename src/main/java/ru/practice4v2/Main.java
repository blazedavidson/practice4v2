package ru.practice4v2;

import ru.practice4v2.model.*;
import ru.practice4v2.beans.AppControl;
import ru.practice4v2.beans.DateControl;
import ru.practice4v2.beans.FullNameControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ru.practice4v2")
@EnableJpaRepositories( basePackageClasses = {Users.class, Login.class})

public class Main {
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(Main.class, args);
		Import dataImport = app.getBean(Import.class);
		Export dataExport = app.getBean(Export.class);
		FullNameControl nameCheck = app.getBean(FullNameControl.class);
		AppControl appCheck = app.getBean(AppControl.class);
		DateControl dateCheck = app.getBean(DateControl.class);
		FilesReadData FD = app.getBean(FilesReadData.class);
		dataImport.reading();
		nameCheck.validate();
		appCheck.validate();
		dateCheck.validate();
		dataExport.save();
	}
}
