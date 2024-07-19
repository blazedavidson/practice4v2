package ru.practice4v2.model;


import org.springframework.stereotype.Component;
import java.util.List;
import lombok.ToString;

@Component
@ToString
public class FilesReadData {
    List<FilesReadStructure> FRS;
    public FilesReadData(List<FilesReadStructure> LFSR) {
        this.FRS = LFSR;
    }
    public void add(FilesReadStructure FSRRow) {
        FRS.add(FSRRow);
    }
    public int getCount() {
        return FRS.size();
    }
    public FilesReadStructure getID(int i) {
        return FRS.get(i);
    }
    public List<FilesReadStructure> getUnicUsers() {
        return FRS.stream()
                .filter(FilesReadStructure::isBCheck)
                .distinct()
                .toList();
    }
    public List<FilesReadStructure> getLoginbySLogin(String SName) {
            return FRS.stream()
                    .filter(FilesReadStructure::isBCheck)
                    .filter(u ->u.getSLogin().equals(SName))
                    .toList();
    }
}
