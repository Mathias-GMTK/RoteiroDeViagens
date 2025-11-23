package com.roteiroviagens.poo.repository;

import com.roteiroviagens.poo.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem,Long> {
}
