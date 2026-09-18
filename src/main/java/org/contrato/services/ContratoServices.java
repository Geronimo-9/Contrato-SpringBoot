package org.contrato.services;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.valueobject.contrato.Estado;
import org.contrato.model.persistence.ContratoRepository;
import org.contrato.model.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContratoServices {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // =========================
    // LISTAR CONTRATOS DEL USUARIO
    // =========================

    public List<Contrato> listarPorUsuario(String usuarioId) {
        return contratoRepository.findByUsuarioId(usuarioId);
    }


    // =========================
    // BUSCAR CONTRATO DEL USUARIO
    // =========================

    public Contrato buscarPorId(Long id, String usuarioId) throws Exception {

        if (id == null) {
            throw new Exception("El ID del contrato es necesario.");
        }

        return contratoRepository.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() ->
                        new Exception("Contrato no encontrado.")
                );
    }


    // =========================
    // AGREGAR
    // =========================

    public void guardarContrato(
            Contrato contrato,
            String usuarioId
    ) throws Exception {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new Exception("Usuario no encontrado.")
                );

        contrato.setUsuario(usuario);

        contrato.validarFechas();

        contratoRepository.save(contrato);
    }


    // =========================
    // MODIFICAR
    // =========================

    public void modificarContrato(
            Long id,
            Contrato datosNuevos,
            String usuarioId
    ) throws Exception {

        Contrato contratoExistente =
                contratoRepository.findByIdAndUsuarioId(id, usuarioId)
                        .orElseThrow(() ->
                                new Exception(
                                        "Contrato no encontrado."
                                )
                        );

        contratoExistente.setEmpresa(datosNuevos.getEmpresa());
        contratoExistente.setEmpleado(datosNuevos.getEmpleado());
        contratoExistente.setFunciones(datosNuevos.getFunciones());
        contratoExistente.setMonto(datosNuevos.getMonto());
        contratoExistente.setFrecuenciaPago(datosNuevos.getFrecuenciaPago());
        contratoExistente.setFechaFirma(datosNuevos.getFechaFirma());
        contratoExistente.setFechaInicio(datosNuevos.getFechaInicio());
        contratoExistente.setFechaFin(datosNuevos.getFechaFin());
        contratoExistente.setEstado(datosNuevos.getEstado());

        contratoExistente.validarFechas();

        contratoRepository.save(contratoExistente);
    }


    // =========================
    // ELIMINAR
    // =========================

    public void eliminarContrato(
            Long id,
            String usuarioId
    ) throws Exception {

        Contrato contrato =
                contratoRepository.findByIdAndUsuarioId(id, usuarioId)
                        .orElseThrow(() ->
                                new Exception(
                                        "Contrato no encontrado."
                                )
                        );

        contratoRepository.delete(contrato);
    }


    // =========================
    // FILTRAR
    // =========================

    public List<Contrato> filtrarContratos(
            String usuarioId,
            String empresa,
            String estado
    ) throws Exception {

        String filtroEmpresa =
                (empresa != null && !empresa.trim().isEmpty())
                        ? empresa.trim()
                        : null;

        Estado filtroEstado = null;

        if (estado != null
                && !estado.trim().isEmpty()
                && !estado.equalsIgnoreCase("TODOS")) {

            try {
                filtroEstado = Estado.valueOf(
                        estado.toUpperCase()
                );

            } catch (IllegalArgumentException e) {

                throw new Exception(
                        "Estado de contrato inválido."
                );
            }
        }

        return contratoRepository.consultarFiltrados(
                usuarioId,
                filtroEmpresa,
                filtroEstado
        );
    }
}