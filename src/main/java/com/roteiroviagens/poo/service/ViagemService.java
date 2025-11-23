package com.roteiroviagens.poo.service;

import com.roteiroviagens.poo.model.Viagem;
import com.roteiroviagens.poo.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    // Dependency Injection via Constructor
    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    // Corrected method: Uses the instance 'viagemRepository' instead of the class
    public Viagem salvar(Viagem v){
        // Sets the registration date if it's null (logic seems fine)
        if (v.getDataCadastro() == null) v.setDataCadastro(LocalDate.now());
        // *** CORRECTION HERE ***
        return viagemRepository.save(v);
    }

    public Optional<Viagem> buscarPorId(Long id){
        return viagemRepository.findById(id);
    }

    // Assumes ViagemRepository extends JpaRepository, using standard findAll()
    public List<Viagem> listarTodos(){
        // *** CORRECTION HERE: changed from findByAll() to standard findAll() ***
        return viagemRepository.findAll();
    }
}
