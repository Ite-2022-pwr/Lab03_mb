module pwr.ite.bedrylo.lab03.server {
    requires spring.web;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires spring.boot.autoconfigure;
    requires lombok;
    requires spring.boot;
    requires spring.context;
    requires jakarta.persistence;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;

    exports pwr.ite.bedrylo.lab03.server;
    exports pwr.ite.bedrylo.lab03.server.config;
    exports pwr.ite.bedrylo.lab03.server.dto;
    exports pwr.ite.bedrylo.lab03.server.model.enums;


    opens pwr.ite.bedrylo.lab03.server.model to spring.core, org.hibernate.orm.core, spring.data.jpa.repository;
    opens pwr.ite.bedrylo.lab03.server.config to spring.core;
    opens pwr.ite.bedrylo.lab03.server to spring.core;
    opens pwr.ite.bedrylo.lab03.server.services to spring.beans;
    opens pwr.ite.bedrylo.lab03.server.controller to spring.beans, spring.web;
    opens pwr.ite.bedrylo.lab03.server.model.enums to com.fasterxml.jackson.databind;

    uses org.springframework.boot.autoconfigure.SpringBootApplication;
}
