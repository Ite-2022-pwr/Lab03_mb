module pwr.ite.bedrylo.lab03.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires reactor.core;
    requires spring.webflux;
    requires lombok;


    opens pwr.ite.bedrylo.lab03.client to javafx.fxml;
    exports pwr.ite.bedrylo.lab03.client;
    exports pwr.ite.bedrylo.lab03.client.service;
    opens pwr.ite.bedrylo.lab03.client.service to javafx.fxml;
}