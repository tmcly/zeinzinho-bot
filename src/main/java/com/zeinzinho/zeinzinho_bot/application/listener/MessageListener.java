package com.zeinzinho.zeinzinho_bot.application.listener;

import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class MessageListener extends ListenerAdapter {

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    if (event.getAuthor().isBot())
      return;

    String messageContent = event.getMessage().getContentRaw();

    if (messageContent.equalsIgnoreCase("!ping")) {
      event.getChannel().sendMessage("Pong!").queue();
    }

    if (messageContent.equalsIgnoreCase("!help")) {
      event.getChannel().sendMessage(
          "Um bot que nem o GabrielZein, linguiçudo e faz de tudo um pouco, no momento converte links de vídeos das principais plataformas para versões Embed pra dentro do discord, use e abuse a vontade como se fosse o GabrielZein.")
          .queue();
    }

    if (messageContent.contains("normal")) {
      String[] responses = {
          "Mas e o gay?",
          "Entendi, mas e o gay?",
          "Legal, mas e o gay?",
          "Interessante, mas e o gay?",
          "Ok, mas e o gay?",
          "Certo certo, mas e o gay?",
          "Sei, mas e o gay?"
      };
      int idx = ThreadLocalRandom.current().nextInt(responses.length);
      event.getChannel().sendMessage(responses[idx]).queue();
    }
  }
}
