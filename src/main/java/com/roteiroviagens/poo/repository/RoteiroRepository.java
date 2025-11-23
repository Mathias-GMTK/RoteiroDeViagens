package com.roteiroviagens.poo.repository;

import com.roteiroviagens.poo.model.Roteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoteiroRepository extends JpaRepository<Roteiro,Long> {
}
