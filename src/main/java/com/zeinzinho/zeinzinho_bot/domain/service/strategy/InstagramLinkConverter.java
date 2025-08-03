package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Instagram links to ddinstagram format for better
 * embeds.
 * Applies Strategy Pattern and Single Responsibility Principle.
 */
@Component
public class InstagramLinkConverter implements LinkConverterStrategy {

  private static final Pattern INSTAGRAM_PATTERN = Pattern.compile(
      "https?://(www\\.)?instagram\\.com/(p|reels?)/[a-zA-Z0-9_-]+.*");

  @Override
  public boolean canHandle(String url) {
    return INSTAGRAM_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Instagram post/reel link: " + originalUrl);
    }

    // Convert instagram.com to ddinstagram.com for better video embeds
    String prefixedUrl = originalUrl
        .replace("https://www.instagram.com", "https://ddinstagram.com")
        .replace("https://instagram.com", "https://ddinstagram.com");

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Instagram";
  }
}