package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Saus;
import be.vdab.FrituurFrida.forms.SausRadenForm;
import be.vdab.FrituurFrida.services.SausService;
import be.vdab.FrituurFrida.sessions.SausRaden;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sauzen")
class SausController {
    private final String[] ingredienten = {"Melk", "groeten", "mustard", "youghurt", "venegar"};
    private final Saus[] sauzen = { new Saus(0,"cocktail", new String[] {"mayonaise", "ketchup", "cognac"}),
                new Saus(1,"mayonaise", new String[] {"ei", "mosterd"}),
                new Saus(2,"mosterd", new String[] {"mosterd", "azijn", "witte wijn"}),
                new Saus(3,"tartare", new String[] {"mayonaise", "augurk", "tabasco"}),
                new Saus(4,"vinaigrette", new String[] {"olijfolie","mosterd","azijn"})};

    private final SausService sausService;
    private final SausRaden sausRaden;
    SausController(SausService sausService, SausRaden sausRaden) {
        this.sausService = sausService;
        this.sausRaden = sausRaden;
    }

    @GetMapping
    public ModelAndView index(){
       // return new ModelAndView("saus","sauzen",sauzen);
        return new ModelAndView("saus","sauzen",sausService.findAll());
    }
    @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id) throws IOException {
        var modelAndView = new ModelAndView("sauzen");
        sausService.findById(id).ifPresent(modelAndView::addObject);
        //modelAndView.addObject("saus", sausService.findById(id));
        /*Arrays.stream(sauzen).filter(saus->saus.getNummer()==id).findFirst()
                .ifPresent(saus -> modelAndView.addObject("saus", saus));*/
        return modelAndView;
    }

    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    @GetMapping("alphabet")
    public ModelAndView alfabet() {
        return new ModelAndView("alphabet", "alphabet", alfabet);
    }
    private List<Saus> sauzenDieBeginnenMet(char letter) {
        return Arrays.stream(sauzen).filter(saus->saus.getNaam().charAt(0)==letter)
                .collect(Collectors.toList());
    }
    @GetMapping("alphabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("alphabet", "alphabet", alfabet)
                .addObject("sauzen", sausService.findByNaamBegintMet(letter));
    }
    private String randomSaus() {
        var sauzen = sausService.findAll();
        return sauzen.get(
                ThreadLocalRandom.current().nextInt(sauzen.size())).getNaam();
    }
    @GetMapping("raden")
    public ModelAndView radenForm() {
        sausRaden.reset(randomSaus());
        return new ModelAndView("sausRaden").addObject(sausRaden)
                .addObject(new SausRadenForm(null));
    }
    @PostMapping("raden/nieuwspel")
    public String radenNieuwSpel() {
        return "redirect:/sauzen/raden";
    }
    @PostMapping(value = "raden")
    public ModelAndView raden(@Valid SausRadenForm form, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("sausRaden").addObject(sausRaden);
        }
        sausRaden.gok(form.getLetter());
        return new ModelAndView("redirect:/sauzen/raden/volgendegok");
    }
    @GetMapping("raden/volgendegok")
    public ModelAndView volgendeGok() {
        return new ModelAndView("sausRaden").addObject(sausRaden)
                .addObject(new SausRadenForm(null));
    }
}
