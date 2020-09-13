package br.com.webscraping.b3.domain;

import org.springframework.stereotype.Repository;

import br.com.webscraping.b3.model.AtivoBovespa;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AtivoBovespaRepository extends JpaRepository<AtivoBovespa, String> {

}
