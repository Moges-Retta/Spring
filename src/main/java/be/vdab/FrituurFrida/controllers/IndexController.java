package be.vdab.FrituurFrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView index() {
        var welkdaag = LocalDate.now().getDayOfWeek()== DayOfWeek.MONDAY? "gesloten" : "open";
        return new ModelAndView("index","moment",welkdaag);
    }
}
