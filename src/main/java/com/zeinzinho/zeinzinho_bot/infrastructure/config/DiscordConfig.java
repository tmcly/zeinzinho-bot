package com.zeinzinho.zeinzinho_bot.infrastructure.config;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zeinzinho.zeinzinho_bot.application.listener.MessageListener;
import com.zeinzinho.zeinzinho_bot.application.listener.SlashCommandListener;
import com.zeinzinho.zeinzinho_bot.application.service.LinkPrefixServiceImpl;
import com.zeinzinho.zeinzinho_bot.domain.service.LinkPrefixService;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

@Configuration
public class DiscordConfig {

  @Value("${discord.bot.token}")
  private String botToken;

  @Bean
  JDA discordJdaClient(MessageListener messageListener, SlashCommandListener slashCommandListener)
      throws LoginException {
    JDA discordJdaClient = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .build();

    discordJdaClient.addEventListener(messageListener, slashCommandListener);

    try {
      discordJdaClient.awaitReady();
      discordJdaClient.getGuildById("377206458699481090")
          .updateCommands()
          .addCommands(
              Commands.slash("prefix", "Converte link do X/Twitter para fxtwitter embed")
                  .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "url", "Tweet URL", true)
                  .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "mentions",
                      "Mentions (@user, @everyone, etc...)", false))
          .queue();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return discordJdaClient;
  }

  @Bean
  LinkPrefixService linkPrefixService() {
    return new LinkPrefixServiceImpl();
  }

}
