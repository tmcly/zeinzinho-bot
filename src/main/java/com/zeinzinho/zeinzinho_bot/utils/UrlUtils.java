package com.zeinzinho.zeinzinho_bot.utils;

/**
 * General URL utilities that don't belong to specific strategies.
 * Keeps only platform-agnostic URL operations.
 */
public class UrlUtils {

  /**
   * Validates if a string is a valid URL format.
   * 
   * @param url the URL to validate
   * @return true if valid URL format
   */
  public static boolean isValidUrl(String url) {
    if (url == null || url.trim().isEmpty()) {
      return false;
    }
    return url.startsWith("http://") || url.startsWith("https://");
  }

  /**
   * Extracts domain from URL.
   * 
   * @param url the URL to extract domain from
   * @return domain or null if invalid
   */
  public static String extractDomain(String url) {
    if (!isValidUrl(url)) {
      return null;
    }

    try {
      String domain = url.replace("https://", "").replace("http://", "");
      if (domain.contains("/")) {
        domain = domain.substring(0, domain.indexOf("/"));
      }
      return domain;
    } catch (Exception e) {
      return null;
    }
  }
}