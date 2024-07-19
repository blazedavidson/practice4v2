package ru.practice4v2.beans;

import ru.practice4v2.model.FilesReadStructure;
import ru.practice4v2.model.FilesReadData;
import ru.practice4v2.model.LogTransformation;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DateControl implements App {

    private final FilesReadData filesData;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public DateControl(FilesReadData data) {
        this.filesData = data;
    }

    @Override
    @LogTransformation(filename = "DateControl.log")

    public void validate() {
        for (int i = 0; i < filesData.getCount(); i++) {

            FilesReadStructure row = filesData.getID(i);

            if (row.getTLogin() == null) {

                row.setBCheck(false);
                log.info(row.getSFile() + ": " + row);

            }

        }
    }
}
