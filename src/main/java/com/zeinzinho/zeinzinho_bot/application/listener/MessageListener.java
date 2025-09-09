package com.zeinzinho.zeinzinho_bot.application.listener;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class MessageListener extends ListenerAdapter {

  private final List<String> truthResponses = Arrays.asList(
      "é verdade sim, confia",
      "creio que seja mentira",
      "meia noite te conto",
      "Mentira deslavada, confia no pai",
      "Se for mentira, eu pago o almoço. Mas é verdade",
      "Creio que sim, mas só porque eu disse",
      "Pergunta pro Zein, ele sabe melhor que eu",
      "Não sei, mas finja que é e vamos em frente",
      "Amanhã te conto, hoje tô de folga");

  private final Random random = new Random();

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
          "Um bot que nem o nosso amigo GabrielZein, faz de tudo um pouco, no momento converte links de vídeos das principais plataformas para versões Embed pra dentro do discord.")
          .queue();
    }

    if (messageContent.equalsIgnoreCase("é verdade isso?")
        || messageContent.equalsIgnoreCase("é verdade essa informação?")) {
      String randomResponse = truthResponses.get(random.nextInt(truthResponses.size()));
      String mentionUser = event.getAuthor().getAsMention();
      event.getChannel().sendMessage(mentionUser + " " + randomResponse).queue();
    }
  }
}
