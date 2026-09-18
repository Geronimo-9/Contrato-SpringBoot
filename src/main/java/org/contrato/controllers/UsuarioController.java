package org.contrato.controllers;

import org.contrato.model.domain.entity.Usuario;
import org.contrato.services.UsuarioServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @GetMapping
    public String listarUsuarios(
            @RequestParam(required = false) String paramId,
            @RequestParam(required = false) String paramNombre,
            Model model) {

        if ((paramId != null && !paramId.trim().isEmpty())
                || (paramNombre != null && !paramNombre.trim().isEmpty())) {

            model.addAttribute(
                    "usuarios",
                    usuarioServices.filtrarUsuarios(paramId, paramNombre)
            );

        } else {

            try {
                model.addAttribute(
                        "usuarios",
                        usuarioServices.listarUsuarios()
                );
            } catch (Exception e) {
                model.addAttribute("usuarios",
                        java.util.Collections.emptyList());
            }
        }

        return "usuario/indexUsuario";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {

        model.addAttribute("usuario", new Usuario());

        return "usuario/agregar";
    }

    @PostMapping
    public String guardarUsuario(
            @ModelAttribute Usuario usuario) throws Exception {

        usuarioServices.guardarUsuario(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormularioModificar(
            @PathVariable String id,
            Model model) throws Exception {

        Usuario usuario = usuarioServices.consultarUsuario(id);

        model.addAttribute("usuario", usuario);

        return "usuario/modificar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarUsuario(@PathVariable String id,
                                   @ModelAttribute Usuario usuario) {
        try {
            usuario.setId(id);
            usuarioServices.modificarUsuario(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/usuarios/modificar/" + id;
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(
            @PathVariable String id) throws Exception {

        usuarioServices.eliminarUsuario(id);

        return "redirect:/usuarios";
    }
}