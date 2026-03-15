package com.jow.futstatus.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jow.futstatus.backend.service.DataImportService;

@CrossOrigin(origins = "http://localhost:4200")
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
            String clubs = "C:/Users/João/Desktop/dev/pessoal/futstatus/csv/england-premier-league-teams-2018-to-2019-stats.csv";
            String players = "C:/Users/João/Desktop/dev/pessoal/futstatus/csv/england-premier-league-players-2018-to-2019-stats.csv";

            importService.importFromTransfermarkt(clubs, players);
            return "Import Successful!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
