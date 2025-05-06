package br.com.helpdesk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entidade JPA que representa um chamado (ticket) no sistema de Helpdesk.
 */
@Entity
@Data  // Lombok gera getters, setters, toString, equals e hashCode automaticamente
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // Identificador único do ticket

    private String titulo;        // Título resumido do chamado

    private String descricao;     // Descrição detalhada do problema

    private String status = "ABERTO"; // Status do chamado (ABERTO, EM_ATENDIMENTO, FECHADO, etc.)

    // Se precisar de campos adicionais, como data de criação ou prioridade, adicione aqui:
    // private LocalDateTime dataCriacao = LocalDateTime.now();
    // private String prioridade;
}
