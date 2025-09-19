package com.capsula.capsula_tempo.Controller;

import com.capsula.capsula_tempo.Entity.Mensagem;
import com.capsula.capsula_tempo.Service.MensagemService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {
    private final MensagemService mensagemService;

@PostMapping
    public ResponseEntity<Mensagem>criarMensagem(@RequestBody Mensagem mensagem){
    return ResponseEntity.ok(mensagemService.criarMensagem(mensagem));
}

@GetMapping
    public ResponseEntity<List<Mensagem>>listarMensagens(){
    return ResponseEntity.ok(mensagemService.listarMensagens());
}
    @GetMapping("/futuras")
    public ResponseEntity<List<Mensagem>>listarMensagensFuturas(){
        return ResponseEntity.ok(mensagemService.listarMensagensFuturas());
    }

    @GetMapping("/passadas")
    public ResponseEntity<List<Mensagem>>listarMensagensPassadas(){
        return ResponseEntity.ok(mensagemService.listarMensagensPassadas());
    }

}
