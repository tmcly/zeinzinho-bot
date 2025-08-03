package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting Twitter/X links to fxtwitter format.
 * Applies Strategy Pattern and Single Responsibility Principle.
 */
@Component
public class TwitterLinkConverter implements LinkConverterStrategy {

  private static final Pattern TWITTER_URL_PATTERN = Pattern.compile(
      "https?://(?:www\\.)?(?:twitter\\.com|x\\.com)/([\\w]+)/status/(\\d+).*");

  @Override
  public boolean canHandle(String url) {
    return TWITTER_URL_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Twitter status link: " + originalUrl);
    }

    String prefixedUrl = convertToFxTwitter(originalUrl);

    if (prefixedUrl == null) {
      throw new IllegalArgumentException("Could not convert Twitter URL: " + originalUrl);
    }

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "Twitter";
  }

  /**
   * Converts Twitter/X URLs to fxtwitter format for better embeds.
   * 
   * @param url the original Twitter/X URL
   * @return converted fxtwitter URL or null if conversion fails
   */
  private String convertToFxTwitter(String url) {
    if (url == null) {
      return null;
    }

    if (url.contains("twitter.com/")) {
      return url.replace("twitter.com/", "fxtwitter.com/");
    }

    if (url.contains("x.com/")) {
      return url.replace("x.com/", "fxtwitter.com/");
    }

    return null;
  }
}