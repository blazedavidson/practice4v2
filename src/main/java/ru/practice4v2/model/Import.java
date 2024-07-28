package ru.practice4v2.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Import {
    private final FilesReadData FRD;
    public Import(FilesReadData FD) {
        this.FRD = FD;
    }
    @Value("${app.folder}")
    String spath;
    public void reading() {
        Path folder = Paths.get(spath);

        if (!(Files.exists(folder) && Files.isDirectory(folder))) {throw new RuntimeException("path error " + spath);}
        try (
            DirectoryStream<Path> fileList = Files.newDirectoryStream(folder)) {
            for (Path file : fileList) {
                if (Files.isRegularFile(file)) {
                    String fName = file.getFileName().toString();
                    try {
                        CSVReader rdr = new CSVReader(new FileReader(file.getParent() + "\\" + fName));
                        List<String[]> records = rdr.readAll();
                        Iterator<String[]> iter = records.iterator();
                        while (iter.hasNext()) {
                            String[] rec = iter.next();
                            FilesReadStructure fileDataRow = new FilesReadStructure();
                            fileDataRow.setSFile(fName);
                            fileDataRow.setSLogin(rec[0]);
                            fileDataRow.setSSurname(rec[1]);
                            fileDataRow.setSName(rec[2]);
                            fileDataRow.setSPatronymic(rec[3]);
                            if (!rec[4].isEmpty()) {fileDataRow.setTLogin(Timestamp.valueOf(rec[4]));}
                            fileDataRow.setSApp(rec[5]);
                            fileDataRow.setBCheck(true);
                            FRD.add(fileDataRow);
                        }
                        rdr.close();
                    } catch (IOException e) {throw new RuntimeException(e);}
                }
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}