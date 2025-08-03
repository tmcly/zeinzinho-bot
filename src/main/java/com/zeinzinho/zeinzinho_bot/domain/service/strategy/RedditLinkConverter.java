package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Reddit links to rxddit format for better embeds.
 * Applies Strategy Pattern and Single Responsibility Principle.
 */
@Component
public class RedditLinkConverter implements LinkConverterStrategy {

  private static final Pattern REDDIT_PATTERN = Pattern.compile(
      "https?://(www\\.)?(reddit\\.com|old\\.reddit\\.com)/r/[^/]+/comments/[a-zA-Z0-9]+.*");

  @Override
  public boolean canHandle(String url) {
    return REDDIT_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Reddit post link: " + originalUrl);
    }

    // Convert reddit.com to vxreddit.com for better video embeds
    String prefixedUrl = originalUrl
        .replace("https://www.reddit.com", "https://vxreddit.com")
        .replace("https://reddit.com", "https://vxreddit.com")
        .replace("https://old.reddit.com", "https://vxreddit.com");

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Reddit";
  }
}