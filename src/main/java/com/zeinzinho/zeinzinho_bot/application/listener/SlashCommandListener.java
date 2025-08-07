package com.zeinzinho.zeinzinho_bot.application.listener;

import org.springframework.stereotype.Component;
import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.application.usecase.ConvertLinkUseCase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.concurrent.CompletableFuture;

@Component
public class SlashCommandListener extends ListenerAdapter {

  private final ConvertLinkUseCase convertLinkUseCase;

  public SlashCommandListener(ConvertLinkUseCase convertLinkUseCase) {
    this.convertLinkUseCase = convertLinkUseCase;
  }

  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    if (event.getName().equals("prefix")) {
      // Processar de forma assíncrona para evitar qualquer bloqueio
      CompletableFuture.runAsync(() -> {
        try {
          // Defer imediatamente - isso DEVE ser a primeira coisa
          event.deferReply().complete();
          System.out.println("✅ Resposta deferida com sucesso para interação: " + event.getId());

          String url = event.getOption("url").getAsString();
          System.out.println("🔄 Processando URL: " + url);

          ConvertedLink convertedLink = convertLinkUseCase.execute(url);

          String response;
          if (event.getOption("mentions") != null) {
            String mentions = event.getOption("mentions").getAsString();
            response = convertedLink.formatForDiscordWithMentions(mentions);
          } else {
            response = convertedLink.formatForDiscord();
          }

          System.out.println("📤 Enviando resposta: " + response);
          event.getHook().editOriginal(response).complete();
          System.out.println("✅ Resposta enviada com sucesso!");

        } catch (IllegalArgumentException e) {
          System.err.println("❌ Erro de URL inválida: " + e.getMessage());
          try {
            event.getHook().editOriginal("URL inválida ou não suportada.").complete();
          } catch (Exception hookError) {
            System.err.println("💥 Erro crítico ao enviar mensagem de erro de URL: " + hookError.getMessage());
          }
        } catch (Exception e) {
          System.err.println("💥 Erro crítico no processamento: " + e.getMessage());
          e.printStackTrace();
          try {
            event.getHook().editOriginal("Erro interno do servidor. Tente novamente.").complete();
          } catch (Exception hookError) {
            System.err.println("💥 Erro crítico ao enviar mensagem de erro geral: " + hookError.getMessage());
          }
        }
      }).exceptionally(throwable -> {
        System.err.println("💥 Erro na execução assíncrona: " + throwable.getMessage());
        throwable.printStackTrace();
        return null;
      });
    }
  }
}