package com.zeinzinho.zeinzinho_bot.domain.model;

/**
 * Value Object representing a link conversion result.
 * Follows DDD principles and Object Calisthenics.
 */
public class ConvertedLink {
  private final String originalUrl;
  private final String prefixedUrl;

  public ConvertedLink(String originalUrl, String prefixedUrl) {
    if (originalUrl == null || originalUrl.trim().isEmpty()) {
      throw new IllegalArgumentException("Original URL cannot be null or empty");
    }
    if (prefixedUrl == null || prefixedUrl.trim().isEmpty()) {
      throw new IllegalArgumentException("Prefixed URL cannot be null or empty");
    }

    this.originalUrl = originalUrl;
    this.prefixedUrl = prefixedUrl;
  }

  /**
   * Tells if this conversion was successful by comparing URLs.
   * Follows Object Calisthenics - behavior over getters.
   */
  public boolean wasConverted() {
    return !originalUrl.equals(prefixedUrl);
  }

  /**
   * Formats the result for Discord response.
   * Encapsulates the formatting logic within the domain.
   */
  public String formatForDiscord() {
    return prefixedUrl;
  }

  /**
   * Formats the result with mentions for Discord response.
   * Encapsulates the formatting logic within the domain.
   */
  public String formatForDiscordWithMentions(String mentions) {
    if (mentions == null || mentions.trim().isEmpty()) {
      return formatForDiscord();
    }
    return mentions + " " + prefixedUrl;
  }

  /**
   * Creates a summary of the conversion.
   * Useful for logging or debugging.
   */
  public String summarizeConversion() {
    return String.format("Converted '%s' to '%s'", originalUrl, prefixedUrl);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;

    ConvertedLink that = (ConvertedLink) obj;
    return originalUrl.equals(that.originalUrl) &&
        prefixedUrl.equals(that.prefixedUrl);
  }

  @Override
  public int hashCode() {
    return originalUrl.hashCode() * 31 + prefixedUrl.hashCode();
  }

  @Override
  public String toString() {
    return summarizeConversion();
  }
}