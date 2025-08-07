package com.zeinzinho.zeinzinho_bot.infrastructure.config;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zeinzinho.zeinzinho_bot.application.listener.MessageListener;
import com.zeinzinho.zeinzinho_bot.application.listener.SlashCommandListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

@Configuration
public class DiscordConfig {

  @Value("${discord.bot.token}")
  private String botToken;

  @Bean
  JDA discordJdaClient(MessageListener messageListener, SlashCommandListener slashCommandListener)
      throws LoginException {
    JDA discordJdaClient = JDABuilder.createDefault(botToken)
        .enableIntents(
            GatewayIntent.GUILD_MEMBERS, // Para acessar membros
            GatewayIntent.MESSAGE_CONTENT, // Para ler mensagens
            GatewayIntent.GUILD_MESSAGES // Para receber mensagens
        )
        .build();

    discordJdaClient.addEventListener(messageListener, slashCommandListener);

    try {
      discordJdaClient.awaitReady();
      discordJdaClient.getGuildById("764636649506078730")
          .updateCommands()
          .addCommands(
              Commands.slash("prefix", "Convert links to embed format")
                  .addOption(OptionType.STRING, "url", "URL from post/video",
                      true)
                  .addOption(OptionType.STRING, "mentions",
                      "Mentions (@user, @everyone, etc...)", false))
          .queue();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return discordJdaClient;
  }
}
