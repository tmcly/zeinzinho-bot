package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Strategy for converting YouTube links to better embed format.
 * Applies Strategy Pattern and Single Responsibility Principle.
 */
@Component
public class YouTubeLinkConverter implements LinkConverterStrategy {

  private static final Pattern YOUTUBE_PATTERN = Pattern.compile(
      "https?://(www\\.)?(youtube\\.com/(watch\\?v=|shorts/)|youtu\\.be/)[a-zA-Z0-9_-]+.*");

  @Override
  public boolean canHandle(String url) {
    return YOUTUBE_PATTERN.matcher(url).matches();
  }

  @Override
  public LinkPrefixModel convert(String originalUrl) {
    if (!canHandle(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid YouTube video link: " + originalUrl);
    }

    // YouTube convertido para koutube.com para melhor embed
    String prefixedUrl = normalizeYouTubeUrl(originalUrl);

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }

  @Override
  public String getPlatformName() {
    return "YouTube";
  }

  /**
   * Converts YouTube URLs to koutube.com format for better embeds.
   * Handles youtube.com, youtu.be and shorts formats.
   */
  private String normalizeYouTubeUrl(String url) {
    // Convert youtu.be to koutube.com format
    if (url.contains("youtu.be/")) {
      String videoId = url.substring(url.indexOf("youtu.be/") + 9);
      // Remove additional parameters after video ID
      if (videoId.contains("?")) {
        videoId = videoId.substring(0, videoId.indexOf("?"));
      }
      if (videoId.contains("&")) {
        videoId = videoId.substring(0, videoId.indexOf("&"));
      }
      return "https://koutube.com/watch?v=" + videoId;
    }

    // Convert YouTube Shorts to koutube.com format
    if (url.contains("youtube.com/shorts/")) {
      String videoId = url.substring(url.indexOf("youtube.com/shorts/") + 19);
      // Remove additional parameters after video ID
      if (videoId.contains("?")) {
        videoId = videoId.substring(0, videoId.indexOf("?"));
      }
      if (videoId.contains("&")) {
        videoId = videoId.substring(0, videoId.indexOf("&"));
      }
      return "https://koutube.com/watch?v=" + videoId;
    }

    // Convert youtube.com to koutube.com format (regular videos)
    if (url.contains("youtube.com/")) {
      return url.replace("youtube.com/", "koutube.com/");
    }

    return url;
  }
}