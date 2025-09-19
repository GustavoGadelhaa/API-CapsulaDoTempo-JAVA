# ⏳ PROJETO CÁPSULA DO TEMPO DE MENSAGENS

Este projeto tem como objetivo **criar e gerenciar mensagens temporais**,  
permitindo que o usuário registre mensagens com uma **data de envio futura**.  
O sistema agenda automaticamente a verificação das mensagens e, quando a data chega, **dispara a mensagem** (impressa no console) e marca como **enviada**.  

## 🚀 Funcionalidades

- ✅ **Criação de mensagens** com data de envio futura
- ✅ **Listagem completa** de todas as mensagens
- ✅ **Filtro por mensagens futuras** (ainda não enviadas)
- ✅ **Filtro por mensagens passadas** (já processadas)
- ✅ **Processamento automático** a cada 5 segundos
- ✅ **Marca como enviada** automaticamente quando a data é atingida
- ✅ **Logs detalhados** no console do processamento
- ✅ **Método Assíncrono** na classe Scheduler


> ⚠️ **Atenção:** As mensagens são persistidas no banco de dados H2 e verificadas a cada **5 segundos** via agendamento.

--------------------------

## 1️⃣ MENSAGENS

### Endpoints disponíveis:

- **Criar nova mensagem:**  
`POST http://localhost:8080/api/mensagens`

- **Listar todas as mensagens:**  
`GET http://localhost:8080/api/mensagens`

- **Listar mensagens futuras (data maior que hoje):**  
`GET http://localhost:8080/api/mensagens/futuras`

- **Listar mensagens passadas (data menor que hoje):**  
`GET http://localhost:8080/api/mensagens/passadas`

---

### Exemplo de requisição para criar mensagem `( DataEnvio:yyyy-MM-dd )` :
`POST http://localhost:8080/api/mensagens`  
Body (JSON):
```json
{
  "mensagem": "Mensagem para o futuro!",
  "dataEnvio": "2025-09-18",
  "nome": "Gustavo"
}
```
## Resposta esperada:
```json
{
  "id": 1,
  "mensagem": "Mensagem para o futuro!",
  "dataEnvio": "2025-09-18",
  "enviada": false,
  "nome": "Gustavo"
}
```
## Agendamento com output no terminal:

O sistema possui uma tarefa agendada que roda a cada 5 segundos:

Busca todas as mensagens não enviadas.

Verifica se a dataEnvio já passou em relação ao dia atual.

Caso positivo✅:

* Imprime a mensagem no console no formato: *
```
Mensagem de Hoje: 2025-09-18 para Gustavo, a Mensagem é: MENSAGEM PARA O FUTURO!
```

## Método que verifica as Menssagens e envia pro console:

```java
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
```
