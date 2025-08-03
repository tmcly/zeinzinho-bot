package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting TikTok links to vxtiktok format for better embeds.
 */
@Component
public class TikTokLinkConverter implements LinkConverterStrategy {

  private static final Pattern TIKTOK_PATTERN = Pattern.compile(
      "https?://(www\\.)?(tiktok\\.com|vm\\.tiktok\\.com).*");

  @Override
  public boolean canHandle(String url) {
    return TIKTOK_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid TikTok link: " + originalUrl);
    }

    // Convert tiktok.com to tnktok.com
    String prefixedUrl = originalUrl
        .replace("https://www.tiktok.com", "https://tnktok.com")
        .replace("https://tiktok.com", "https://tnktok.com")
        .replace("https://vm.tiktok.com", "https://tnktok.com");

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "TikTok";
  }
}