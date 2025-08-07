package com.zeinzinho.zeinzinho_bot.infrastructure.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Reddit links to rxddit format for better embeds in Discord.
 * Uses rxddit.com which provides enhanced previews for Reddit posts.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 * Located in infrastructure layer as it contains framework-specific annotations.
 */
@Component
public class RedditLinkConverter implements LinkConverterStrategy {

  private static final Pattern REDDIT_PATTERN = Pattern.compile(
      "https?://(www\\.)?reddit\\.com/r/[^/]+/comments/[^/]+/.*");

  @Override
  public boolean canHandle(String url) {
    return REDDIT_PATTERN.matcher(url).matches();
  }

  @Override
  public ConvertedLink convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Reddit post link: " + originalUrl);
    }

    String prefixedUrl = originalUrl
        .replace("https://www.reddit.com", "https://rxddit.com")
        .replace("https://reddit.com", "https://rxddit.com");

    return new ConvertedLink(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Reddit";
  }
}