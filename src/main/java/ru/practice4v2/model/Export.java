package ru.practice4v2.model;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class Export {
    private final FilesReadData FRS;
    private final Users users;

    private final Login login;

    public Export(FilesReadData FRdata, Users user, Login login) {
        this.FRS = FRdata;
        this.users = user;
        this.login = login;
    }

    public void save() {if (FRS.getCount()==0) {return;}

    List<FilesReadStructure> userslist = FRS.getUnicUsers();
    List<SQLLogins> logins = new ArrayList<>();
    List<SQLUsers>  users  = new ArrayList<>();
    int size = 5;

    for (FilesReadStructure FRStructure : userslist) {

        if (users.size() % size == 0) {
            this.users.saveAll(users);
            users.clear();
            login.saveAll(logins);
        }
            SQLUsers user = new SQLUsers();
            user.setUserName(FRStructure.getSLogin());user.setLastName(FRStructure.getSSurname());
            user.setFirstName(FRStructure.getSName());user.setPatronymic(FRStructure.getSPatronymic());
            List<FilesReadStructure> loginsList = FRS.getLoginbySLogin(user.getUserName());
            for (FilesReadStructure STRLogins : loginsList) {
                SQLLogins login = new SQLLogins();
                login.setApplication(STRLogins.getSApp());
                login.setAccessDate(STRLogins.getTLogin());
                login.setUser(user);
                logins.add(login);
            }
            user.setLogins(logins);
            users.add(user);
        }

        if (!users.isEmpty()) {this.users.saveAll(users); login.saveAll(logins);}
    }
}
