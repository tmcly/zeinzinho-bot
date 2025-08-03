package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;

/**
 * Strategy interface for link conversion implementations.
 * Follows Strategy Pattern for different social media platforms.
 */
public interface LinkConverterStrategy {

  /**
   * Checks if this strategy can handle the given URL.
   * 
   * @param url the URL to check
   * @return true if this strategy can process the URL
   */
  boolean canHandle(String url);

  /**
   * Converts the URL to embed-friendly format.
   * 
   * @param originalUrl the original URL to convert
   * @return LinkPrefixModel with original and converted URLs
   * @throws IllegalArgumentException if URL cannot be converted
   */
  LinkPrefixModel convert(String originalUrl);

  /**
   * Gets the platform name this strategy handles.
   * 
   * @return platform name (e.g., "Twitter", "Reddit")
   */
  String getPlatformName();
}