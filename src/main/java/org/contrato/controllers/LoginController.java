package org.contrato.controllers;

import jakarta.servlet.http.HttpSession;
import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.exception.DomainException;
import org.contrato.services.UsuarioServices; // O el nombre exacto de tu servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServices usuarioServices; // El servicio que valida en la BD

    // 1. Muestra la vista login.html cuando entras a /login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Busca templates/login.html
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String id,
                                @RequestParam String contrasena,
                                HttpSession session,
                                Model model) {
        try {
            Usuario usuario = usuarioServices.iniciarSesion(id, contrasena);
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/index";

        } catch (DomainException e) {
            model.addAttribute("error", e.getMessage());
            return "login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // 3. Ruta para el menú principal
    @GetMapping("/index")
    public String mostrarIndex(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login"; // Si no hay sesión, patea al login
        }
        return "index"; // Busca templates/index.html
    }

    // 4. Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}