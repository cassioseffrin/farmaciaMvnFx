module br.edu.cassio.farmaciamvnfx {
    requires javafx.controls;
    requires javafx.fxml;  
//    
    requires java.sql;
  	requires jasperreports;
//does not export br.edu.cassio.farmaciamvnfx.controller to module javafx.fxml
    opens br.edu.cassio.farmaciamvnfx.controller to javafx.fxml;

    exports br.edu.cassio.farmaciamvnfx;
    exports br.edu.cassio.farmaciamvnfx.beans;
}