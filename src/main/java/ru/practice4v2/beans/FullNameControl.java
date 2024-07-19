package ru.practice4v2.beans;

import ru.practice4v2.model.FilesReadStructure;
import ru.practice4v2.model.FilesReadData;
import ru.practice4v2.model.LogTransformation;
import org.springframework.stereotype.Component;

@Component
public class FullNameControl implements App {
    private final FilesReadData filesReadData;

    public FullNameControl(FilesReadData fContent) {
        this.filesReadData = fContent;
    }

    @LogTransformation(filename = "FullNameControl.log")
    @Override

    public void validate() {
        for (int i = 0; i < filesReadData.getCount(); i++) {

                FilesReadStructure row = filesReadData.getID(i);

                if (row.getSSurname() == null || row.getSSurname().isEmpty()) {
                    row.setSSurname(row.getSSurname());
                }
                else {
                    row.setSSurname(row.getSSurname().substring(0, 1).toUpperCase() + row.getSSurname().substring(1));
                }

                if (row.getSName() == null || row.getSName().isEmpty()) {
                    row.setSName(row.getSName());
                }
                else {
                    row.setSName(row.getSName().substring(0, 1).toUpperCase() + row.getSName().substring(1));
                }

                if (row.getSPatronymic() == null || row.getSPatronymic().isEmpty()) {
                    row.setSPatronymic(row.getSPatronymic());
                }
                else {
                    row.setSPatronymic(row.getSPatronymic().substring(0, 1).toUpperCase() + row.getSPatronymic().substring(1));
                }

        }
    }

}
