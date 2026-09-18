package org.contrato.model.persistence;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.valueobject.contrato.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    Optional<Contrato> findByIdAndUsuarioId(Long id, String usuarioId);

    @Query("""
           SELECT c
           FROM Contrato c
           WHERE c.usuario.id = :usuarioId
           AND (:empresa IS NULL
                OR LOWER(c.empresa) LIKE LOWER(CONCAT('%', :empresa, '%')))
           AND (:estado IS NULL
                OR c.estado = :estado)
           """)
    List<Contrato> consultarFiltrados(
            @Param("usuarioId") String usuarioId,
            @Param("empresa") String empresa,
            @Param("estado") Estado estado
    );

    List<Contrato> findByUsuarioId(String usuarioId);
}