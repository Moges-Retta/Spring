package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("sauzen")
class SausController {
    private final String[] ingredienten = {"Melk", "groeten", "mustard", "youghurt", "venegar"};
    private final Saus[] sauzen = { new Saus(0,"cocktail", new String[] {"mayonaise", "ketchup", "cognac"}),
                new Saus(1,"mayonaise", new String[] {"ei", "mosterd"}),
                new Saus(2,"mosterd", new String[] {"mosterd", "azijn", "witte wijn"}),
                new Saus(3,"tartare", new String[] {"mayonaise", "augurk", "tabasco"}),
                new Saus(4,"vinaigrette", new String[] {"olijfolie","mosterd","azijn"})};

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("saus","sauzen",sauzen);
    }
   @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id){
        var modelAndView = new ModelAndView("sauzen");
        Arrays.stream(sauzen).filter(saus->saus.getNummer()==id).findFirst()
                .ifPresent(saus -> modelAndView.addObject("saus", saus));
        return modelAndView;
    }
}
