package com.zeinzinho.zeinzinho_bot.infrastructure.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Twitter/X links to fxtwitter format for better embeds
 * in Discord.
 * Uses fxtwitter.com which provides enhanced video and image previews.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 * Located in infrastructure layer as it contains framework-specific
 * annotations.
 */
@Component
public class TwitterLinkConverter implements LinkConverterStrategy {

  private static final Pattern TWITTER_PATTERN = Pattern.compile(
      "https?://(www\\.)?(twitter\\.com|x\\.com)/[^/]+/status/\\d+.*");

  @Override
  public boolean canHandle(String url) {
    return TWITTER_PATTERN.matcher(url).matches();
  }

  @Override
  public ConvertedLink convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Twitter/X status link: " + originalUrl);
    }

    String prefixedUrl = originalUrl
        .replace("https://twitter.com", "https://fxtwitter.com")
        .replace("https://www.twitter.com", "https://fxtwitter.com")
        .replace("https://x.com", "https://fxtwitter.com")
        .replace("https://www.x.com", "https://fxtwitter.com");

    return new ConvertedLink(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Twitter/X";
  }
}