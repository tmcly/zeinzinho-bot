package com.zeinzinho.zeinzinho_bot.application.service;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;
import com.zeinzinho.zeinzinho_bot.domain.service.LinkPrefixService;
import com.zeinzinho.zeinzinho_bot.domain.registry.LinkConverterRegistry;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementation of LinkPrefixService using Strategy Pattern.
 * Delegates to appropriate converter strategy based on URL type.
 * Follows Open/Closed Principle - open for extension, closed for modification.
 */
@Service
public class LinkPrefixServiceImpl implements LinkPrefixService {

  private final LinkConverterRegistry registry;

  public LinkPrefixServiceImpl(LinkConverterRegistry registry) {
    this.registry = registry;
  }

  @Override
  public LinkPrefixModel generatePrefixedLink(String originalUrl) {
    Optional<LinkConverterStrategy> strategy = registry.findStrategy(originalUrl);

    if (strategy.isEmpty()) {
      throw new IllegalArgumentException("No converter available for URL: " + originalUrl);
    }

    return strategy.get().convert(originalUrl);
  }
}
