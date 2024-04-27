package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.ConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @GetMapping("/{key}")
    public ResponseEntity<String> getConfiguration(@PathVariable String key) {
        return ResponseEntity.of(configurationService.getConfiguration(key));
    }

    @GetMapping("/all")
    public Map<String, String> getAllConfiguration() {
        return configurationService.getAllConfiguration();
    }

    @SaCheckRole("ADMIN")
    @PutMapping("/{key}/{value}")
    public void setConfiguration(@PathVariable String key, @PathVariable String value) {
        configurationService.setConfiguration(key, value);
    }

}
