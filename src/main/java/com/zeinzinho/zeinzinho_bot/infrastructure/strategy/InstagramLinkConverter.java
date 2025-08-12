package com.zeinzinho.zeinzinho_bot.infrastructure.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Instagram links to ddinstagram format for better
 * embeds in Discord.
 * Uses ddinstagram.com which provides enhanced previews for Instagram posts.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 * Located in infrastructure layer as it contains framework-specific
 * annotations.
 */
@Component
public class InstagramLinkConverter implements LinkConverterStrategy {

  private static final Pattern INSTAGRAM_PATTERN = Pattern.compile(
      "https?://(www\\.)?instagram\\.com/(p|reels?|tv)/[A-Za-z0-9_-]+/?.*");

  @Override
  public boolean canHandle(String url) {
    return INSTAGRAM_PATTERN.matcher(url).matches();
  }

  @Override
  public ConvertedLink convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Instagram post/reel link: " + originalUrl);
    }

    String prefixedUrl = originalUrl
        .replace("https://www.instagram.com", "https://ddinstagram.com")
        .replace("https://instagram.com", "https://ddinstagram.com");

    return new ConvertedLink(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Instagram";
  }
}