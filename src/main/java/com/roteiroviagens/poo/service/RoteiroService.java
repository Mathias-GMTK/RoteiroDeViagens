package com.roteiroviagens.poo.service;

import com.roteiroviagens.poo.model.Viagem;
import com.roteiroviagens.poo.model.Roteiro;
import com.roteiroviagens.poo.repository.ViagemRepository;
import com.roteiroviagens.poo.repository.RoteiroRepository;
import com.roteiroviagens.poo.dto.RoteiroResponseDTO;
import com.roteiroviagens.poo.dto.ViagemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class RoteiroService {

    private final GeminiService geminiService;
    private final ViagemRepository viagemRepository;
    private final RoteiroRepository roteiroRepository;

    @Autowired
    public RoteiroService(
            GeminiService geminiService,
            ViagemRepository viagemRepository,
            RoteiroRepository roteiroRepository) {

        this.geminiService = geminiService;
        this.viagemRepository = viagemRepository;
        this.roteiroRepository = roteiroRepository;

    }

    public Mono<RoteiroResponseDTO> gerarRoteiro(ViagemRequestDTO dto){
        final Viagem viagem = new Viagem();
        viagem.setDestino(dto.getDestino());
        viagem.setDias(dto.getDias());
        viagem.setOrcamento(dto.getOrcamento());
        viagem.setEstilo(dto.getEstilo());

        final Mono<Viagem> savedViagemMono = Mono.fromCallable(() -> viagemRepository.save(viagem))
                .subscribeOn(Schedulers.boundedElastic());

        final String prompt = montarPrompt(dto);
        final Mono<String> geminiMono = geminiService.gerarTexto(prompt);

        return Mono.zip(savedViagemMono, geminiMono)
                .flatMap(data -> {
                    Viagem savedViagem = data.getT1();
                    String geminiResponse = data.getT2();

                    Roteiro roteiro = new Roteiro();
                    roteiro.setTextoCompleto(geminiResponse);
                    roteiro.setViagem(savedViagem);
                    
                    return Mono.fromCallable(() -> roteiroRepository.save(roteiro))
                            .subscribeOn(Schedulers.boundedElastic());
                })
                .map(savedRoteiro -> new RoteiroResponseDTO(savedRoteiro.getId(), savedRoteiro.getTextoCompleto()));
    }

    private String montarPrompt(ViagemRequestDTO dto) {
        return String.format(
                "Crie um roteiro de viagem para %s, %d dias, orçamento R$ %.2f, estilo %s. " +
                        "Responda APENAS com um JSON válido (sem markdown ```json). " +
                        "Siga estritamente esta estrutura de chaves: " +
                        "{ " +
                        "  \"dias\": [ " +
                        "    { " +
                        "      \"dia\": 1, " +
                        "      \"tema\": \"Nome do dia\", " +
                        "      \"manhã\": \"Texto descritivo do que fazer de manhã\", " +
                        "      \"tarde\": \"Texto descritivo do que fazer a tarde\", " +
                        "      \"noite\": \"Texto descritivo do que fazer a noite\", " +
                        "      \"refeições\": \"Sugestão de onde comer\", " +
                        "      \"transporte\": \"Dica de deslocamento\" " +
                        "    } " +
                        "  ] " +
                        "}",
                dto.getDestino(), dto.getDias(), dto.getOrcamento(), dto.getEstilo()
        );
    }
}