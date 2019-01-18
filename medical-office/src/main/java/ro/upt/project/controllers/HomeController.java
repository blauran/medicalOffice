package ro.upt.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.upt.project.services.UserService;
import ro.upt.project.utils.AppointmentDto;
import ro.upt.project.utils.RegisterDto;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        setAuthorities(model, principal);
        model.addAttribute("doctors", userService.getDoctors());
        return "index";
    }

    @GetMapping("/appointment")
    public String appointment(Model model) {
        model.addAttribute("doctors", userService.getDoctors());
        model.addAttribute("appointment", new AppointmentDto());
        return "appointment";
    }

    @PostMapping("/appointment")
    public String newAppointment(@ModelAttribute AppointmentDto appointmentDto, Model model) {
        userService.createAppointment(appointmentDto);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute RegisterDto registerDto) {
        userService.createUser(registerDto);
        return "redirect:/login";
    }

    @GetMapping("/my-appointments")
    public String myAppointments(Model model, Principal principal) {
        setAuthorities(model, principal);
        model.addAttribute("appointments", userService.getAppointments());
        return "appointment-list";
    }

    @GetMapping("/appointment/{id}/delete")
    public String myAppointments(@PathVariable long id) {
        userService.removeAppointment(id);
        return "redirect:/my-appointments";
    }

    private void setAuthorities(Model model, Principal principal) {
        boolean isPatient = isPatient();
        model.addAttribute("isAnonymous", principal == null);
        model.addAttribute("isPatient", isPatient);
        model.addAttribute("isDoctor", principal != null && !isPatient);
    }

    private boolean isPatient() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return false;
        }

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .parallelStream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(x -> x.equalsIgnoreCase("role_patient"));
    }
}
