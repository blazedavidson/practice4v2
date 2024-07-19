package ru.practice4v2.model;

import java.util.Objects;
import java.sql.Timestamp;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FilesReadStructure {

    private String SFile;
    private String SLogin;
    private String SSurname;
    private String SName;
    private String SPatronymic;
    private Timestamp TLogin;
    private String SApp;
    private boolean BCheck;

    @Override
    public int hashCode() {return Objects.hash(SLogin);}

    @Override
    public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            FilesReadStructure fileDataRow = (FilesReadStructure) obj;

            return Objects.equals(SLogin, fileDataRow.SLogin);
    }



}