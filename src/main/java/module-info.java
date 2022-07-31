module it.enricod.math {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens it.enricod.math to javafx.fxml;
    exports it.enricod.math;
}
