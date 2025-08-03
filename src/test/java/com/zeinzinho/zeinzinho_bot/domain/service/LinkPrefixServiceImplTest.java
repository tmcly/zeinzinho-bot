package com.zeinzinho.zeinzinho_bot.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;

@SpringBootTest
class LinkPrefixServiceImplTest {

  @Autowired
  private LinkPrefixService service;

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
  void testGeneratePrefixedLink_RedditUrl() {
    String originalUrl = "https://www.reddit.com/r/funny/comments/abc123/some_title";

    LinkPrefixModel result = service.generatePrefixedLink(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://rxddit.com/r/funny/comments/abc123/some_title", result.getPrefixedUrl());
  }

  @Test
  void testGeneratePrefixedLink_TikTokUrl() {
    String originalUrl = "https://www.tiktok.com/@user/video/1234567890";

    LinkPrefixModel result = service.generatePrefixedLink(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://vxtiktok.com/@user/video/1234567890", result.getPrefixedUrl());
  }

  @Test
  void testGeneratePrefixedLink_InstagramUrl() {
    String originalUrl = "https://www.instagram.com/p/ABC123xyz/";

    LinkPrefixModel result = service.generatePrefixedLink(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://ddinstagram.com/p/ABC123xyz/", result.getPrefixedUrl());
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
