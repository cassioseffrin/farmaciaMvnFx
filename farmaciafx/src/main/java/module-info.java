module br.unc.rener.farmaciafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.unc.rener.farmaciafx.controller to javafx.fxml;
    exports br.unc.rener.farmaciafx;
}
