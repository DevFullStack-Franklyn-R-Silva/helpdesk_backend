package br.com.helpdesk.service;

import br.com.helpdesk.model.Ticket;
import br.com.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço que encapsula a lógica de negócios de tickets.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Cria um novo ticket.
     */
    public Ticket criarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Retorna todos os tickets.
     */
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Busca um ticket por ID.
     * @throws RuntimeException se não encontrar.
     */
    public Ticket buscarPorId(Long id) {
        Optional<Ticket> opt = ticketRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Ticket não encontrado: " + id));
    }

    /**
     * Atualiza um ticket existente.
     */
    public Ticket atualizarTicket(Long id, Ticket dados) {
        Ticket existente = buscarPorId(id);
        existente.setTitulo(dados.getTitulo());
        existente.setDescricao(dados.getDescricao());
        existente.setStatus(dados.getStatus());
        return ticketRepository.save(existente);
    }

    /**
     * Deleta um ticket por ID.
     */
    public void deletarTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
