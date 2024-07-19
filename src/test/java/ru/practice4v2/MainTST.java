package ru.practice4v2;

import ru.practice4v2.model.*;
import ru.practice4v2.beans.AppControl;
import ru.practice4v2.beans.DateControl;
import ru.practice4v2.beans.FullNameControl;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.io.File;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(
        classes = TestConfigDB.class,
        loader = AnnotationConfigContextLoader.class)

@SpringBootTest( classes = Main.class)
public class MainTST {
    @Autowired
    private FilesReadData fileRD;
    @Autowired
    private Users uUsers;
    @Autowired
    private Login lLogins;
    @Autowired
    private Import iImport;
    @Autowired
    private Export dExport;
    @Autowired
    private AppControl appCntrl;
    @Autowired
    private DateControl dateCntrl;
    @Autowired
    private FullNameControl FNC;
    @Value("${app.log}")
    String pathTST;
    @Test
    public void tst(){
        iImport.reading();
        FNC.validate();
        appCntrl.validate();
        dateCntrl.validate();
        dExport.save();
        File dir = new File(pathTST);
        int cntLogFiles = Objects.requireNonNull(dir.listFiles()).length;
        assertThat(cntLogFiles).isEqualTo(4);
    }
}
