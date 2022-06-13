package com.example.pointini.repository;
import com.example.pointini.entities.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
     Pack findPackById(Long id);
     Pack findByLibelle(String s);

}
