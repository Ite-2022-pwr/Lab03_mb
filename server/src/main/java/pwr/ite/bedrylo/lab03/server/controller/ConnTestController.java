package pwr.ite.bedrylo.lab03.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conn")
public class ConnTestController {
    
    @GetMapping("/test")
    public ResponseEntity<Boolean> testConnection() {
        return ResponseEntity.ok(true);
    }
    
}
