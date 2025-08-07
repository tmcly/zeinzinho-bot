package com.zeinzinho.zeinzinho_bot.application.usecase;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.domain.service.strategy.LinkConverterStrategy;
import com.zeinzinho.zeinzinho_bot.infrastructure.registry.LinkConverterRegistry;
import org.springframework.stereotype.Service;

/**
 * Use Case for converting social media links to embed-friendly formats.
 * This is the main entry point for link conversion operations.
 * 
 * Follows Clean Architecture principles:
 * - Orchestrates the conversion process
 * - Delegates to appropriate strategies via registry
 * - Contains the business logic flow
 */
@Service
public class ConvertLinkUseCase {

    private final LinkConverterRegistry registry;

    public ConvertLinkUseCase(LinkConverterRegistry registry) {
        this.registry = registry;
    }

    /**
     * Executes the link conversion use case.
     * 
     * @param originalUrl the original URL to convert
     * @return ConvertedLink with the converted URL and behavior
     * @throws IllegalArgumentException if no converter is available for the URL
     */
    public ConvertedLink execute(String originalUrl) {
        LinkConverterStrategy strategy = registry.findStrategy(originalUrl)
                .orElseThrow(() -> new IllegalArgumentException("No converter available for URL: " + originalUrl));

        return strategy.convert(originalUrl);
    }

    /**
     * Checks if a URL can be converted.
     * 
     * @param url the URL to check
     * @return true if the URL can be converted, false otherwise
     */
    public boolean canConvert(String url) {
        return registry.findStrategy(url).isPresent();
    }
}