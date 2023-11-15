package pwr.ite.bedrylo.lab03.client.enums;

import lombok.Getter;

@Getter
public enum Role {
    BOSS("Kierownik"),
    EMPLOYEE("Kontroler"),
    CLIENT("Klient");
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}