package be.vdab.FrituurFrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("taal")
public class TaalController {
    private static final String[] TAAL = {"en-GB","en-USA","en-Canada","nl"};

    @GetMapping
    public ModelAndView taal(@RequestHeader("Accept-Language") String acceptLanguage) {
        var modelAndView = new ModelAndView("taal");
        Arrays.stream(TAAL).filter(taal -> acceptLanguage.contains(taal))
                .findFirst().ifPresent(taal -> modelAndView.addObject("taal", taal));
        return modelAndView;
    }
}
