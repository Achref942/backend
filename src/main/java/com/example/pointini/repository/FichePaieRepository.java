package com.example.pointini.repository;

import com.example.pointini.entities.FichePaie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FichePaieRepository extends JpaRepository<FichePaie,Long> {

   FichePaie findFichePaieById(Long id);
}
