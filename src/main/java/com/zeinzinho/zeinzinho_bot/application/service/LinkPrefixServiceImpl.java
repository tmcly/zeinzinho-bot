package com.zeinzinho.zeinzinho_bot.application.service;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import com.zeinzinho.zeinzinho_bot.domain.service.LinkPrefixService;
import com.zeinzinho.zeinzinho_bot.utils.UrlUtils;

public class LinkPrefixServiceImpl implements LinkPrefixService {

  @Override
  public LinkPrefixModel generatePrefixedLink(String originalUrl) {
    // Validar se é um link do Twitter válido
    if (!UrlUtils.isTwitterStatusUrl(originalUrl)) {
      throw new IllegalArgumentException("URL is not a valid Twitter status link: " + originalUrl);
    }

    // Converter para fxtwitter
    String prefixedUrl = UrlUtils.convertToFxTwitter(originalUrl);

    if (prefixedUrl == null) {
      throw new IllegalArgumentException("Could not convert URL: " + originalUrl);
    }

    return new LinkPrefixModel(originalUrl, prefixedUrl);
  }
}
