package com.zeinzinho.zeinzinho_bot.domain.model;

public class LinkPrefixModel {
  private String originalUrl;
  private String prefixedUrl;

  public LinkPrefixModel(String originalUrl, String prefixedUrl) {
    this.originalUrl = originalUrl;
    this.prefixedUrl = prefixedUrl;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public String getPrefixedUrl() {
    return prefixedUrl;
  }

}
