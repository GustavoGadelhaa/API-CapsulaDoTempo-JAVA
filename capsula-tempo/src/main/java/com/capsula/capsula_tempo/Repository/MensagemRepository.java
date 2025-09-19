package com.capsula.capsula_tempo.Repository;

import com.capsula.capsula_tempo.Entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findByEnviadaFalse();
    List<Mensagem> findByDataEnvioAfter(LocalDate data);
    List<Mensagem> findByDataEnvioBefore(LocalDate data);



}
