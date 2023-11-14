module pwr.ite.bedrylo.lab03.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens pwr.ite.bedrylo.lab03.client to javafx.fxml;
    exports pwr.ite.bedrylo.lab03.client;
}