package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Pizza;
import be.vdab.FrituurFrida.domain.Saus;
import be.vdab.FrituurFrida.domain.Snack;
import be.vdab.FrituurFrida.exceptions.SnackNietGevondenException;
import be.vdab.FrituurFrida.forms.SnackBeginMet;
import be.vdab.FrituurFrida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("snacks")
class SnackController {
    private final SnackService snackService;

    SnackController(SnackService snackService) {
        this.snackService = snackService;
    }
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @GetMapping("alphabet")
    public ModelAndView alfabet() {

        return new ModelAndView("alphabetSnack", "alphabet", alfabet);
    }
    @GetMapping("alphabet/{letter}")
    public ModelAndView snacksBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("alphabetSnack", "alphabet", alfabet)
                .addObject("snacks", snackService.findByBeginNaam(String.valueOf(letter)));
    }
    @GetMapping("beginMetLetter/form")
    public ModelAndView snackBeginMet(){
        return new ModelAndView("snackBeginMet")
                .addObject(new SnackBeginMet(null));
    }
    @GetMapping("beginMetLetter")
    public ModelAndView snackBeginMetLetter(@Valid SnackBeginMet snackBeginMet, Errors errors){
        var modelAndView = new ModelAndView("snackBeginMet");
        if(errors.hasErrors()){
            return modelAndView;
        }
        return modelAndView.addObject("snacks",
                snackService.findByBeginNaam(snackBeginMet.getBeginLetter()));
    }
    @GetMapping("{id}/toevoegen/form")
    public ModelAndView wijzigenForm(@PathVariable long id) {
        var modelAndView = new ModelAndView("toevoegen");
        snackService.findById(id).ifPresent(snack -> modelAndView.addObject(snack));
        return modelAndView;
    }
    /*@GetMapping("toevoegen/form")
    public ModelAndView toevoegen(){
        return new ModelAndView("toevoegen")
                .addObject(new Snack(0,"Spicy Ketchup", BigDecimal.ONE));
    }*/
    @PostMapping("toevoegen")
    public String toevoegen(@Valid Snack snack, Errors errors,RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            return "toevoegen";
        }
        try {
            snackService.update(snack);
            return "redirect:/";
        } catch (SnackNietGevondenException ex) {
            redirect.addAttribute("snackNietGevonden", snack.getId());
            return "redirect:/";
        }
    }
}
