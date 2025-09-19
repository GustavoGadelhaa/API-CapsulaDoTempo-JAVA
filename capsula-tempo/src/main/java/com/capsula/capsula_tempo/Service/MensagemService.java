package com.capsula.capsula_tempo.Service;

import com.capsula.capsula_tempo.Entity.Mensagem;
import com.capsula.capsula_tempo.Repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;


    LocalDate hoje=LocalDate.now();

    public Mensagem criarMensagem(Mensagem mensagem){
        return mensagemRepository.save(mensagem);
    }
    public List<Mensagem> listarMensagens(){
        return mensagemRepository.findAll();
    }
    public List<Mensagem>listarMensagensFuturas(){
        List<Mensagem>mensagemFutura=mensagemRepository.findByDataEnvioAfter(hoje);
        return mensagemFutura;

    }
    public List<Mensagem>listarMensagensPassadas(){
        List<Mensagem>mensagensPassadas=mensagemRepository.findByDataEnvioBefore(hoje);
        return mensagensPassadas;
    }




}
