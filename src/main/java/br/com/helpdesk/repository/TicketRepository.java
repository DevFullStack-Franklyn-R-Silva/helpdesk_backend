package br.com.helpdesk.repository;

import br.com.helpdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Ticket.
 * Fornece operações CRUD e pode receber consultas personalizadas.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Exemplo de método de consulta personalizada: busca tickets pelo status.
     *
     * @param status Status do ticket (ex: "ABERTO", "FECHADO").
     * @return Lista de tickets com o status informado.
     */
    List<Ticket> findByStatus(String status);

}
