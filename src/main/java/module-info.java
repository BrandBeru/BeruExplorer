module org.beru.ide.beruide {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.bootstrapfx.core;
    requires org.jfxtras.styles.jmetro;
    requires jsch;
    requires MaterialFX;

    opens org.beru.ide.beruide.application to javafx.fxml;
    opens org.beru.ide.beruide.application.sectionsController to javafx.fxml;
    opens org.beru.ide.beruide.test to javafx.fxml;
    exports org.beru.ide.beruide;
}