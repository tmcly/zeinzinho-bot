package com.zeinzinho.zeinzinho_bot.infrastructure.registry;

import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Registry for managing link converter strategies.
 * Implements Registry Pattern for strategy lookup.
 * Located in infrastructure layer as it handles framework-specific concerns.
 */
@Component
public class LinkConverterRegistry {

  private final List<LinkConverterStrategy> strategies;

  public LinkConverterRegistry(List<LinkConverterStrategy> strategies) {
    this.strategies = strategies;
  }

  /**
   * Finds the appropriate strategy for the given URL.
   * Follows Chain of Responsibility pattern.
   * 
   * @param url the URL to find strategy for
   * @return Optional containing the strategy if found
   */
  public Optional<LinkConverterStrategy> findStrategy(String url) {
    return strategies.stream()
        .filter(strategy -> strategy.canHandle(url))
        .findFirst();
  }

  /**
   * Gets all registered strategies.
   * 
   * @return list of all strategies
   */
  public List<LinkConverterStrategy> getAllStrategies() {
    return List.copyOf(strategies);
  }
}