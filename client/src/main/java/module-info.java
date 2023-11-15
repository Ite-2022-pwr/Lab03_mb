module pwr.ite.bedrylo.lab03.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires reactor.core;
    requires spring.webflux;
    requires lombok;
    requires com.fasterxml.jackson.databind;


    opens pwr.ite.bedrylo.lab03.client to javafx.fxml, javafx.graphics;
    exports pwr.ite.bedrylo.lab03.client;
    exports pwr.ite.bedrylo.lab03.client.service;
    opens pwr.ite.bedrylo.lab03.client.service to javafx.fxml;
    opens pwr.ite.bedrylo.lab03.client.dto to com.fasterxml.jackson.databind, java.base, javafx.fxml;
    opens pwr.ite.bedrylo.lab03.client.enums to com.fasterxml.jackson.databind, javafx.fxml;
    exports pwr.ite.bedrylo.lab03.client.controller;
    opens pwr.ite.bedrylo.lab03.client.controller to javafx.fxml, javafx.graphics;

}