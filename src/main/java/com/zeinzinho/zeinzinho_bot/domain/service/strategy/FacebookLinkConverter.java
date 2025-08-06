package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Facebook links to facebookez format for better
 * embeds in Discord. Uses facebookez.com which is part of the EmbedEZ service.
 * 
 * IMPORTANT: Due to Facebook's strict embed policies, most services can only
 * provide static thumbnails/previews rather than fully playable videos.
 * This is a limitation of Facebook's platform, not our implementation.
 * 
 * Applies Strategy Pattern and Single Responsibility Principle.
 */
@Component
public class FacebookLinkConverter implements LinkConverterStrategy {

  private static final Pattern FACEBOOK_PATTERN = Pattern.compile(
      "https?://(www\\.)?(facebook\\.com|fb\\.com)/(watch/|reel/|share/|[^/]+/(videos|posts)/|[^/]+/videos/)[^\\s]*");

  @Override
  public boolean canHandle(String url) {
    return FACEBOOK_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Facebook video/reel link: " + originalUrl);
    }

    String prefixedUrl = originalUrl
        .replace("https://www.facebook.com", "https://facebookez.com")
        .replace("https://facebook.com", "https://facebookez.com")
        .replace("https://www.fb.com", "https://facebookez.com")
        .replace("https://fb.com", "https://facebookez.com");

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Facebook";
  }
}
