package org.contrato.controllers;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.entity.Usuario;
import org.contrato.services.ContratoServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoServices contratoServices;

    public ContratoController(ContratoServices contratoServices) {
        this.contratoServices = contratoServices;
    }


    // =========================
    // LISTAR / FILTRAR
    // =========================

    @GetMapping
    public String listarContratos(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String estado,
            @SessionAttribute("usuarioLogueado") Usuario usuario,
            Model model) {

        String usuarioId = usuario.getId();

        try {

            boolean tieneFiltros =
                    (empresa != null && !empresa.trim().isEmpty())
                            || (estado != null && !estado.trim().isEmpty());

            if (tieneFiltros) {

                model.addAttribute(
                        "contratos",
                        contratoServices.filtrarContratos(
                                usuarioId,
                                empresa,
                                estado
                        )
                );

            } else {

                model.addAttribute(
                        "contratos",
                        contratoServices.listarPorUsuario(usuarioId)
                );
            }

            model.addAttribute("paramEmpresa", empresa);
            model.addAttribute("paramEstado", estado);

            return "contrato/indexContrato";

        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

            model.addAttribute(
                    "contratos",
                    contratoServices.listarPorUsuario(usuarioId)
            );

            return "contrato/indexContrato";
        }
    }


    // =========================
    // NUEVO
    // =========================

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(
            Model model) {

        model.addAttribute(
                "contrato",
                new Contrato()
        );

        return "contrato/agregarContrato";
    }


    // =========================
    // GUARDAR
    // =========================

    @PostMapping
    public String guardarContrato(
            @ModelAttribute Contrato contrato,
            @SessionAttribute("usuarioLogueado") Usuario usuario) {

        String usuarioId = usuario.getId();

        try {

            contratoServices.guardarContrato(
                    contrato,
                    usuarioId
            );

            return "redirect:/contratos";

        } catch (Exception e) {

            return "redirect:/contratos";
        }
    }


    // =========================
    // MODIFICAR - FORMULARIO
    // =========================

    @GetMapping("/modificar/{id}")
    public String mostrarFormularioModificar(
            @PathVariable Long id,
            @SessionAttribute("usuarioLogueado") Usuario usuario,
            Model model) {

        String usuarioId = usuario.getId();

        try {

            Contrato contrato =
                    contratoServices.buscarPorId(
                            id,
                            usuarioId
                    );

            model.addAttribute(
                    "contrato",
                    contrato
            );

            return "contrato/modificarContrato";

        } catch (Exception e) {

            return "redirect:/contratos";
        }
    }


    // =========================
    // MODIFICAR
    // =========================

    @PostMapping("/modificar/{id}")
    public String modificarContrato(
            @PathVariable Long id,
            @ModelAttribute Contrato contrato,
            @SessionAttribute("usuarioLogueado") Usuario usuario) {

        String usuarioId = usuario.getId();

        try {

            contratoServices.modificarContrato(
                    id,
                    contrato,
                    usuarioId
            );

            return "redirect:/contratos";

        } catch (Exception e) {

            return "redirect:/contratos";
        }
    }


    // =========================
    // ELIMINAR
    // =========================

    @PostMapping("/eliminar/{id}")
    public String eliminarContrato(
            @PathVariable Long id,
            @SessionAttribute("usuarioLogueado") Usuario usuario) {

        String usuarioId = usuario.getId();

        try {

            contratoServices.eliminarContrato(
                    id,
                    usuarioId
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/contratos";
    }
}