package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Saus;
import be.vdab.FrituurFrida.domain.Snack;
import be.vdab.FrituurFrida.forms.SnackBeginMet;
import be.vdab.FrituurFrida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView snackBeginMetLetter(SnackBeginMet snackBeginMet, Errors errors){
        var modelAndView = new ModelAndView("snackBeginMet");
        if(errors.hasErrors()){
            return modelAndView;
        }
        return modelAndView.addObject("snacks",
                snackService.findByBeginNaam(snackBeginMet.getBeginLetter()));
    }
}
