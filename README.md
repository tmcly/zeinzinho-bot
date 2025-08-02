# ZeinzinhoBot 🤖

Um bot para Discord desenvolvido em Java que converte links do X (Twitter) para formato embed usando fxtwitter.com, permitindo que vídeos sejam exibidos corretamente no Discord.

## 🚀 Funcionalidades

- Converte automaticamente links do X/Twitter para fxtwitter.com
- Suporte a slash commands (`/prefix`)
- Permite mencionar usuários específicos ou @everyone junto com o link
- Comando de teste `!ping` para verificar se o bot está online
- Validação de URLs do Twitter/X
- Interface limpa com respostas diretas

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** - Framework principal
- **JDA (Java Discord API)** - Integração com Discord
- **Maven** - Gerenciamento de dependências

## 📋 Pré-requisitos

- Java21
- Maven 3.6+
- Bot do Discord criado no [Discord Developer Portal](https://discord.com/developers/applications)
- Token do bot configurado

## ⚙️ Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/zeinzinho-bot.git
cd zeinzinho-bot
```

### 2. Configure o token do bot

Crie um arquivo `application.properties` em `src/main/resources/`:

```properties
discord.bot.token=SEU_TOKEN_AQUI
```

### 3. Configure o ID da Guild (Servidor)

No arquivo `DiscordConfig.java`, substitua `"ID_DA_GUILD"` pelo ID do seu servidor Discord:

```java
discordJdaClient.getGuildById("SEU_GUILD_ID_AQUI")
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

## 🎯 Como Usar

### Comando `/prefix`

Converte links do X/Twitter para formato embed:

```
/prefix url:https://x.com/usuario/status/123456789
```

Com menções:

```
/prefix url:https://x.com/usuario/status/123456789 marcacoes:@usuario @everyone
```

### Comando `!ping`

Testa se o bot está respondendo:

```
!ping
```

Resposta: `Pong!`

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/zeinzinho/zeinzinho_bot/
│   │       ├── application/
│   │       │   ├── listener/
│   │       │   │   ├── MessageListener.java      # Listener para mensagens (!ping)
│   │       │   │   └── SlashCommandListener.java # Listener para slash commands
│   │       │   └── service/
│   │       │       └── LinkPrefixServiceImpl.java # Implementação do serviço
│   │       ├── domain/
│   │       │   ├── model/
│   │       │   │   └── LinkPrefixModel.java       # Modelo de dados
│   │       │   ├── service/
│   │       │   │   └── LinkPrefixService.java     # Interface do serviço
│   │       │   └── util/
│   │       │       └── UrlUtils.java              # Utilitários para URLs
│   │       ├── infrastructure/
│   │       │   └── config/
│   │       │       └── DiscordConfig.java         # Configuração do JDA
│   │       └── ZeinzinhoBotApplication.java       # Classe principal
│   └── resources/
│       └── application.properties                 # Configurações da aplicação
```

## 🔧 Arquitetura

O projeto segue uma arquitetura em camadas:

- **Application Layer**: Listeners e serviços de aplicação
- **Domain Layer**: Regras de negócio, modelos e interfaces
- **Infrastructure Layer**: Configurações e integrações externas

### Fluxo de Funcionamento

1. **Recepção**: `SlashCommandListener` recebe o comando `/prefix`
2. **Processamento**: `LinkPrefixService` valida e converte a URL
3. **Validação**: `UrlUtils` verifica se é um link válido do Twitter/X
4. **Conversão**: Substitui domínio para `fxtwitter.com`
5. **Resposta**: Bot envia o link convertido com menções (se houver)

## 🌐 URLs Suportadas

- `https://twitter.com/usuario/status/123456789`
- `https://x.com/usuario/status/123456789`

Convertidas para:

- `https://fxtwitter.com/usuario/status/123456789`

## 📝 Exemplo de Uso

**Entrada:**

```
/prefix url:https://x.com/usuario/status/123456789 marcacoes:@amigo
```

**Saída:**

```
@amigo https://fxtwitter.com/usuario/status/123456789
```

O Discord automaticamente exibirá o vídeo como embed usando o link fxtwitter.
