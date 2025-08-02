package com.zeinzinho.zeinzinho_bot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

  // Pattern para detectar links do Twitter/X
  private static final Pattern TWITTER_URL_PATTERN = Pattern.compile(
      "https?://(?:www\\.)?(?:twitter\\.com|x\\.com)/([\\w]+)/status/(\\d+)");

  /**
   * Converte um link do Twitter/X para o formato fxtwitter
   * 
   * @param originalUrl URL original do Twitter/X
   * @return URL convertida para fxtwitter, ou null se não for um link válido
   */
  public static String convertToFxTwitter(String url) {
    if (url == null)
      return null;
    if (url.startsWith("https://twitter.com/")) {
      return url.replace("https://twitter.com/", "https://fxtwitter.com/");
    }
    if (url.startsWith("https://x.com/")) {
      return url.replace("https://x.com/", "https://fxtwitter.com/");
    }
    return null;
  }

  /**
   * Verifica se uma URL é de um tweet do Twitter/X
   * 
   * @param url URL para verificar
   * @return true se for um link de tweet válido
   */
  public static boolean isTwitterStatusUrl(String url) {
    return TWITTER_URL_PATTERN.matcher(url).matches();
  }
}