module com.configfx.student_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires animatefx;
    requires com.github.librepdf.openpdf;

    opens com.configfx.student_manager to javafx.fxml;
    exports com.configfx.student_manager;
    exports com.configfx.student_manager.Controllers;
    exports com.configfx.student_manager.Models;
    exports com.configfx.student_manager.Utils;

    opens com.configfx.student_manager.Controllers to javafx.fxml;
    opens com.configfx.student_manager.Models to javafx.base;
    opens com.configfx.student_manager.Utils to java.base;
}