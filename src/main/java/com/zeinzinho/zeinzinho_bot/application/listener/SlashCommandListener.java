package com.zeinzinho.zeinzinho_bot.application.listener;

import org.springframework.stereotype.Component;
import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import com.zeinzinho.zeinzinho_bot.domain.service.LinkPrefixService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class SlashCommandListener extends ListenerAdapter {

  private final LinkPrefixService linkPrefixService;

  public SlashCommandListener(LinkPrefixService linkPrefixService) {
    this.linkPrefixService = linkPrefixService;
  }

  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    if (event.getName().equals("prefix")) {
      String url = event.getOption("url").getAsString();
      try {
        LinkPrefixModel prefixedLink = linkPrefixService.generatePrefixedLink(url);
        String response = prefixedLink.getPrefixedUrl();

        // Adiciona menções se houver
        if (event.getOption("marcacoes") != null) {
          String mentions = event.getOption("marcacoes").getAsString();
          response = mentions + " " + response;
        }

        event.reply(response).queue();
      } catch (IllegalArgumentException e) {
        event.reply("URL inválida ou não suportada.").setEphemeral(true).queue();
      }
    }
  }
}
