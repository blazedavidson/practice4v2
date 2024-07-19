package ru.practice4v2;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.practice4v2.model.SQLUsers;
import ru.practice4v2.model.Users;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class usrTST {
        @Autowired
        private TestEntityManager entityManager;
        @Autowired
        private Users uUsers;
        @Test
        void whenSaveUser_thenFoundUser() {
            SQLUsers usr = new SQLUsers();
            usr.setUserName("semen");
            usr.setFirstName("семен");
            usr.setLastName("семенович");
            usr.setPatronymic("семенович");
            this.entityManager.persist(usr);
            SQLUsers checkUsr = this.uUsers.findByUserName("семен");
            assertThat(checkUsr.getUserName()).isEqualTo("semen");
            assertThat(checkUsr.getFirstName()).isEqualTo("семен");
        }

}

