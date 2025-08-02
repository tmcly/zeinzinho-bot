package com.zeinzinho.zeinzinho_bot.domain.service;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;

public interface LinkPrefixService {
  LinkPrefixModel generatePrefixedLink(String originalUrl);
}
