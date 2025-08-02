package com.zeinzinho.zeinzinho_bot.application.listener;

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
  }
}
