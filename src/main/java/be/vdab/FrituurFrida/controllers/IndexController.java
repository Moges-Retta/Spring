package be.vdab.FrituurFrida.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index() {
        var moment = LocalDate.now().getDayOfWeek()== DayOfWeek.MONDAY? "Gesloten" : "open";
        return "<!doctype html><html><title>Hallo</title><body>Vandag is "
                + moment +
                "</body></html>";
    }
}
