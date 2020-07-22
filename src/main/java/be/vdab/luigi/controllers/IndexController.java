package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Adres;
import be.vdab.luigi.domain.Persoon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/")
class IndexController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    private final AtomicInteger aantalKeerBekeken = new AtomicInteger();
    @GetMapping
    public ModelAndView index(@CookieValue(name="reedsBezocht", required = false) String reedsBezocht,
                              HttpServletResponse response) {
        var morgenMiddag = LocalTime.now().getHour() < 12 ? "morgen" : "middag";
        var modelAndView = new ModelAndView("index","moment", morgenMiddag);
        modelAndView.addObject("zaakvoerder",
        new Persoon("Luigi", "Peperone", 7, true, LocalDate.of(1966, 1, 31),
        new Adres("Grote markt", "3", 9700, "Oudenaarde")));

        var cookie = new Cookie("reedsBezocht", "Ja");
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);
        if (reedsBezocht != null) {
            modelAndView.addObject("reedsBezocht", true);
        }
        modelAndView.addObject("aantalKeerBekeken", aantalKeerBekeken.incrementAndGet());
        return modelAndView;
    }
}

