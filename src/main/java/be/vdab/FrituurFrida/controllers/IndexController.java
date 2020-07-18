package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Adres;
import be.vdab.FrituurFrida.domain.Gemente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView index() {
        var welkdaag = LocalDate.now().getDayOfWeek()== DayOfWeek.MONDAY? "gesloten" : "open";
        var modelAndView= new ModelAndView("index","moment",welkdaag);
        modelAndView.addObject("MijnAdres",
                new Adres("Mgr. Van Waeyenberhlaan","40", new Gemente("Leuven",3000)));
        return modelAndView;
    }
}
