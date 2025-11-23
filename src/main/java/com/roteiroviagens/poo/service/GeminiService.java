package com.roteiroviagens.poo.service;

import com.roteiroviagens.poo.dto.gemini.CandidateDTO;
import com.roteiroviagens.poo.dto.gemini.ContentDTO;
import com.roteiroviagens.poo.dto.gemini.GeminiResponseDTO;
import com.roteiroviagens.poo.dto.gemini.PartDTO;
import com.roteiroviagens.poo.exception.GeminiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Service
public class GeminiService {


    private static final String API_KEY = "AIzaSyBWkgGrp1ChzzicbCukc5sjzHqrE-5JhSU";

    // Trocamos para a versão exata "001" na v1beta
    // Atualizado para a versão 2.5 que aparece na sua lista
    private static final String URL_GOOGLE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    public Mono<String> gerarTexto(String prompt) {
        return Mono.fromCallable(() -> {
            Map<String, Object> requestBody = Map.of(
                    "contents", new Object[]{
                            Map.of("parts", new Object[]{
                                    Map.of("text", prompt)
                            })
                    }
            );

            RestTemplate restTemplate = new RestTemplate();

            try {

                URI uri = URI.create(URL_GOOGLE);

                System.out.println("Chamando URL: " + uri.toString());

                GeminiResponseDTO response = restTemplate.postForObject(uri, requestBody, GeminiResponseDTO.class);

                if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
                    CandidateDTO candidate = response.getCandidates().get(0);
                    if (candidate != null && candidate.getContent() != null) {
                        return candidate.getContent().getParts().get(0).getText();
                    }
                }
                return "Gemini respondeu vazio.";

            } catch (HttpClientErrorException e) {

                System.err.println("ERRO DO GOOGLE: " + e.getResponseBodyAsString());
                throw new GeminiException("Erro na API do Google: " + e.getResponseBodyAsString());
            }
        });
    }
}