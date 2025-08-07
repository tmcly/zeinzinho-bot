package com.zeinzinho.zeinzinho_bot.infrastructure.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting TikTok links to vxtiktok format for better embeds in Discord.
 * Uses vxtiktok.com which provides enhanced video previews for TikTok content.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 * Located in infrastructure layer as it contains framework-specific annotations.
 */
@Component
public class TikTokLinkConverter implements LinkConverterStrategy {

  private static final Pattern TIKTOK_PATTERN = Pattern.compile(
      "https?://(www\\.)?tiktok\\.com/@[^/]+/video/\\d+.*");

  @Override
  public boolean canHandle(String url) {
    return TIKTOK_PATTERN.matcher(url).matches();
  }

  @Override
  public ConvertedLink convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid TikTok video link: " + originalUrl);
    }

    String prefixedUrl = originalUrl
        .replace("https://www.tiktok.com", "https://vxtiktok.com")
        .replace("https://tiktok.com", "https://vxtiktok.com");

    return new ConvertedLink(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "TikTok";
  }
}