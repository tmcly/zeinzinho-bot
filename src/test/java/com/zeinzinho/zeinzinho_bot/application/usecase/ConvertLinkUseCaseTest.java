package com.zeinzinho.zeinzinho_bot.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;

@SpringBootTest
class ConvertLinkUseCaseTest {

  @Autowired
  private ConvertLinkUseCase convertLinkUseCase;

  @Test
  void testExecute_TwitterUrl() {
    String originalUrl = "https://twitter.com/temposalvoof/status/1951633985685115009";

    ConvertedLink result = convertLinkUseCase.execute(originalUrl);

    assertNotNull(result);
    assertEquals("https://fxtwitter.com/temposalvoof/status/1951633985685115009", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testExecute_XUrl() {
    String originalUrl = "https://x.com/temposalvoof/status/1951633985685115009";

    ConvertedLink result = convertLinkUseCase.execute(originalUrl);

    assertNotNull(result);
    assertEquals("https://fxtwitter.com/temposalvoof/status/1951633985685115009", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testExecute_RedditUrl() {
    String originalUrl = "https://www.reddit.com/r/funny/comments/abc123/some_title";

    ConvertedLink result = convertLinkUseCase.execute(originalUrl);

    assertNotNull(result);
    assertEquals("https://rxddit.com/r/funny/comments/abc123/some_title", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testExecute_TikTokUrl() {
    String originalUrl = "https://www.tiktok.com/@user/video/1234567890";

    ConvertedLink result = convertLinkUseCase.execute(originalUrl);

    assertNotNull(result);
    assertEquals("https://vxtiktok.com/@user/video/1234567890", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testExecute_InstagramUrl() {
    String originalUrl = "https://www.instagram.com/p/ABC123xyz/";

    ConvertedLink result = convertLinkUseCase.execute(originalUrl);

    assertNotNull(result);
    assertEquals("https://ddinstagram.com/p/ABC123xyz/", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testCanConvert_ValidUrl() {
    String validUrl = "https://twitter.com/user/status/123";

    boolean result = convertLinkUseCase.canConvert(validUrl);

    assertEquals(true, result);
  }

  @Test
  void testCanConvert_InvalidUrl() {
    String invalidUrl = "https://google.com";

    boolean result = convertLinkUseCase.canConvert(invalidUrl);

    assertEquals(false, result);
  }

  @Test
  void testExecute_InvalidUrl() {
    String invalidUrl = "https://google.com";

    assertThrows(IllegalArgumentException.class, () -> convertLinkUseCase.execute(invalidUrl));
  }

  @Test
  void testExecute_InvalidTwitterUrl() {
    String invalidUrl = "https://twitter.com/temposalvoof"; // Sem /status/

    assertThrows(IllegalArgumentException.class, () -> convertLinkUseCase.execute(invalidUrl));
  }
}