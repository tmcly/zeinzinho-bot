package com.zeinzinho.zeinzinho_bot.infrastructure.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting YouTube links to enhanced format for better embeds in Discord.
 * Currently returns the original URL as YouTube embeds work well natively in Discord.
 * This strategy exists for consistency and future enhancements.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 * Located in infrastructure layer as it contains framework-specific annotations.
 */
@Component
public class YouTubeLinkConverter implements LinkConverterStrategy {

  private static final Pattern YOUTUBE_PATTERN = Pattern.compile(
      "https?://(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)[A-Za-z0-9_-]+.*");

  @Override
  public boolean canHandle(String url) {
    return YOUTUBE_PATTERN.matcher(url).matches();
  }

  @Override
  public ConvertedLink convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid YouTube video link: " + originalUrl);
    }

    // YouTube embeds work well natively in Discord, so we return the original URL
    // This could be enhanced in the future with timestamp extraction or other features
    return new ConvertedLink(originalUrl, originalUrl);
  }

  @Override
  public String getPlatformName() {
    return "YouTube";
  }
}