package ru.practice4v2.beans;

import org.springframework.core.annotation.Order;
import ru.practice4v2.model.LogTransformation;
import ru.practice4v2.model.FilesReadStructure;
import ru.practice4v2.model.FilesReadData;
import org.springframework.stereotype.Component;

@Order(2)
@Component

public class AppControl implements App {
    private final FilesReadData filesReadData;
    public AppControl(FilesReadData filesData) {
        this.filesReadData = filesData;
    }
    @Override
    @LogTransformation(filename = "AppControl.log")



    public void validate() {
        System.out.println("AppControl");
        for (int i = 0; i < filesReadData.getCount(); i++) {

                    FilesReadStructure row = filesReadData.getID(i);

                    if (AppTypes.checkName(row.getSApp()) == null)
                    {
                        row.setSApp(row.getSApp());
                    }
        }
    }


}
