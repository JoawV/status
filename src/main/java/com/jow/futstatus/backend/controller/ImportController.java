package com.jow.futstatus.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jow.futstatus.backend.service.DataImportService;

@RestController
@RequestMapping("/api/import")
public class ImportController {
    private final DataImportService importService;

    public ImportController(DataImportService importService) {
        this.importService = importService;
    }

    @GetMapping
    public String runImport() {
        try {
            String clubs = "C:\\Users\\João\\Desktop\\dev\\pessoal\\team_details_copia.csv";
            String players = "C:\\Users\\João\\Desktop\\dev\\pessoal\\player_profiles_copia.csv";
            
            importService.importFromTransfermarkt(clubs, players);
            return "Import Successful!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
