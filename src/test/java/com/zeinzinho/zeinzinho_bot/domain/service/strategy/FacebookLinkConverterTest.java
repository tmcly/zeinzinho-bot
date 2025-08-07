package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zeinzinho.zeinzinho_bot.domain.model.ConvertedLink;
import com.zeinzinho.zeinzinho_bot.infrastructure.strategy.FacebookLinkConverter;

class FacebookLinkConverterTest {

  private FacebookLinkConverter converter;

  @BeforeEach
  void setUp() {
    converter = new FacebookLinkConverter();
  }

  @Test
  void testCanHandle_ValidFacebookVideoUrl() {
    String url = "https://www.facebook.com/user/videos/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_ValidFacebookReelUrl() {
    String url = "https://www.facebook.com/reel/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_ValidFacebookWatchUrl() {
    String url = "https://www.facebook.com/watch/?v=123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_ValidFbComUrl() {
    String url = "https://fb.com/user/videos/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_InvalidUrl() {
    String url = "https://twitter.com/user/status/123";
    assertFalse(converter.canHandle(url));
  }

  @Test
  void testCanHandle_InvalidFacebookUrl() {
    String url = "https://www.facebook.com/user"; // Not a video/reel link
    assertFalse(converter.canHandle(url));
  }

  @Test
  void testGetPlatformName() {
    assertEquals("Facebook", converter.getPlatformName());
  }

  @Test
  void testConvert_ValidFacebookVideoUrl() {
    String originalUrl = "https://www.facebook.com/user/videos/123456789";

    ConvertedLink result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals("https://facebookez.com/user/videos/123456789", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testConvert_ValidFacebookReelUrl() {
    String originalUrl = "https://www.facebook.com/reel/123456789";

    ConvertedLink result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals("https://facebookez.com/reel/123456789", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testConvert_ValidFbComUrl() {
    String originalUrl = "https://fb.com/user/videos/123456789";

    ConvertedLink result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals("https://facebookez.com/user/videos/123456789", result.formatForDiscord());
    assertTrue(result.wasConverted());
  }

  @Test
  void testConvert_InvalidUrl() {
    String invalidUrl = "https://twitter.com/user/status/123";

    assertThrows(IllegalArgumentException.class, () -> converter.convert(invalidUrl));
  }

  @Test
  void testConvert_InvalidFacebookUrl() {
    String invalidUrl = "https://www.facebook.com/user"; // Not a video/reel link

    assertThrows(IllegalArgumentException.class, () -> converter.convert(invalidUrl));
  }
}