package br.com.mercadolivre.apiismutant.controller;

import br.com.mercadolivre.apiismutant.dto.Human;
import br.com.mercadolivre.apiismutant.dto.StatsDTO;
import br.com.mercadolivre.apiismutant.service.MutantService;
import br.com.mercadolivre.apiismutant.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    private final MutantService analyzeService;
    private final StatsService statsService;

    @Autowired
    public MutantController(MutantService analyzeService, StatsService statsService) {
        this.analyzeService = analyzeService;
        this.statsService = statsService;
    }

    @PostMapping
    @RequestMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody Human human) {
        if (analyzeService.isMutant(human.getDna())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping
    @RequestMapping("/stats")
    public ResponseEntity<StatsDTO> getStats() {
        return new ResponseEntity<>(statsService.stats(), HttpStatus.OK);
    }

}
