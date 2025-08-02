package com.zeinzinho.zeinzinho_bot.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.zeinzinho.zeinzinho_bot.application.service.LinkPrefixServiceImpl;
import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;

class LinkPrefixServiceImplTest {

  private final LinkPrefixService service = new LinkPrefixServiceImpl();

  @Test
  void testGeneratePrefixedLink_TwitterUrl() {
    String originalUrl = "https://twitter.com/temposalvoof/status/1951633985685115009";

    LinkPrefixModel result = service.generatePrefixedLink(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://fxtwitter.com/temposalvoof/status/1951633985685115009", result.getPrefixedUrl());
  }

  @Test
  void testGeneratePrefixedLink_XUrl() {
    String originalUrl = "https://x.com/temposalvoof/status/1951633985685115009";

    LinkPrefixModel result = service.generatePrefixedLink(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://fxtwitter.com/temposalvoof/status/1951633985685115009", result.getPrefixedUrl());
  }

  @Test
  void testInvalidUrl() {
    String invalidUrl = "https://google.com";

    assertThrows(IllegalArgumentException.class, () -> service.generatePrefixedLink(invalidUrl));
  }

  @Test
  void testInvalidTwitterUrl() {
    String invalidUrl = "https://twitter.com/temposalvoof"; // Sem /status/

    assertThrows(IllegalArgumentException.class, () -> service.generatePrefixedLink(invalidUrl));
  }
}
