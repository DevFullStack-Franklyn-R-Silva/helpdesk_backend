package br.com.helpdesk.controller;

import br.com.helpdesk.model.Ticket;
import br.com.helpdesk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações de Ticket.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Lista todos os tickets.
     */
    @GetMapping
    public ResponseEntity<List<Ticket>> listarTickets() {
        List<Ticket> tickets = ticketService.listarTickets();
        return ResponseEntity.ok(tickets);
    }

    /**
     * Cria um novo ticket.
     */
    @PostMapping
    public ResponseEntity<Ticket> criarTicket(@RequestBody Ticket ticket) {
        Ticket criado = ticketService.criarTicket(ticket);
        return ResponseEntity.ok(criado);
    }

    /**
     * Busca um ticket por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id) {
        Ticket ticket = ticketService.buscarPorId(id);
        return ResponseEntity.ok(ticket);
    }

    /**
     * Atualiza um ticket por ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> atualizarTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket
    ) {
        Ticket atualizado = ticketService.atualizarTicket(id, ticket);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Deleta um ticket por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicket(@PathVariable Long id) {
        ticketService.deletarTicket(id);
        return ResponseEntity.noContent().build();
    }
}
