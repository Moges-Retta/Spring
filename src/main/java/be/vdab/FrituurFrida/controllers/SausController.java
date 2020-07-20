package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sauzen")
class SausController {
    private final String[] ingredienten = {"Melk", "groeten", "mustard", "youghurt", "venegar"};
    private final Saus[] sauzen = { new Saus(1234567890,"cocktail", ingredienten),
                new Saus(1234567891,"mayonaise", ingredienten),
                new Saus(1234567892,"mosterd", ingredienten),
                new Saus(1234567893,"tartare", ingredienten),
                new Saus(1234567894,"vinaigrette", ingredienten)};

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("saus","sauzen",sauzen);
    }
}
