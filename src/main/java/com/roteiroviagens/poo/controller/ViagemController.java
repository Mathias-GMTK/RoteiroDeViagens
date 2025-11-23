package com.roteiroviagens.poo.controller;

import com.roteiroviagens.poo.dto.HistoricoDTO;
import com.roteiroviagens.poo.dto.RoteiroResponseDTO;
import com.roteiroviagens.poo.dto.ViagemRequestDTO;
import com.roteiroviagens.poo.repository.RoteiroRepository; // <--- Importante
import com.roteiroviagens.poo.service.RoteiroService;
import com.roteiroviagens.poo.service.ViagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ViagemController {


    private final ViagemService viagemService;
    private final RoteiroService roteiroService;
    private final RoteiroRepository roteiroRepository;


    public ViagemController(ViagemService viagemService,
                            RoteiroService roteiroService,
                            RoteiroRepository roteiroRepository) {
        this.viagemService = viagemService;
        this.roteiroService = roteiroService;
        this.roteiroRepository = roteiroRepository;
    }

    @PostMapping("/Gerar")
    public Mono<ResponseEntity<RoteiroResponseDTO>> gerarRoteiro(@Valid @RequestBody ViagemRequestDTO dto) {
        return roteiroService.gerarRoteiro(dto)
                .map(ResponseEntity::ok);
    }


    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoDTO>> listarHistorico() {
        List<HistoricoDTO> lista = roteiroRepository.findAll().stream()
                .map(r -> new HistoricoDTO(
                        r.getId(),
                        r.getViagem().getDestino(),
                        r.getViagem().getDataCadastro(),
                        r.getViagem().getEstilo(),
                        r.getViagem().getDias()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/historico/{id}")
    public ResponseEntity<RoteiroResponseDTO> buscarPorId(@PathVariable Long id) {
        // Agora o 'roteiroRepository' existe e funciona
        return roteiroRepository.findById(id)
                .map(r -> new RoteiroResponseDTO(r.getId(), r.getTextoCompleto()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/historico/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!roteiroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        roteiroRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}