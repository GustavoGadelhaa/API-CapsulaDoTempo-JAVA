package com.capsula.capsula_tempo.Service;

import com.capsula.capsula_tempo.Entity.Mensagem;
import com.capsula.capsula_tempo.Repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemScheduler {

    private final MensagemRepository mensagemRepository;

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void verificarMensagens() {
        List<Mensagem> MsgsPendentes = mensagemRepository.findByEnviadaFalse();
        LocalDate hoje = LocalDate.now();
        for (Mensagem p : MsgsPendentes) {
            if (p.getDataEnvio().isBefore(hoje) && !p.isEnviada()) {
                System.out.println("\n\nMensagem de Hoje: " + p.getDataEnvio() +
                " para " + p.getNome() + ", a Mensagem é: " + p.getMensagem().toUpperCase());
                p.setEnviada(true);
                mensagemRepository.save(p);
            }
        }
    }
}
