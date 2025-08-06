package com.zeinzinho.zeinzinho_bot.domain.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zeinzinho.zeinzinho_bot.domain.model.LinkPrefixModel;

class FacebookLinkConverterTest {

  private final FacebookLinkConverter converter = new FacebookLinkConverter();

  @Test
  void testCanHandle_FacebookVideo() {
    String url = "https://www.facebook.com/user/videos/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FacebookReel() {
    String url = "https://www.facebook.com/reel/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FacebookWatch() {
    String url = "https://www.facebook.com/watch/?v=123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FacebookShareReel() {
    String url = "https://www.facebook.com/share/r/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FacebookShareVideo() {
    String url = "https://www.facebook.com/share/v/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FacebookPost() {
    String url = "https://www.facebook.com/user/posts/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_FbCom() {
    String url = "https://fb.com/user/videos/123456789";
    assertTrue(converter.canHandle(url));
  }

  @Test
  void testCanHandle_InvalidUrl() {
    String url = "https://twitter.com/user/status/123";
    assertFalse(converter.canHandle(url));
  }

  @Test
  void testConvert_FacebookVideo() {
    String originalUrl = "https://www.facebook.com/user/videos/123456789";

    LinkPrefixModel result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://facebookez.com/user/videos/123456789", result.getPrefixedUrl());
  }

  @Test
  void testConvert_FacebookReel() {
    String originalUrl = "https://www.facebook.com/reel/123456789";

    LinkPrefixModel result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://facebookez.com/reel/123456789", result.getPrefixedUrl());
  }

  @Test
  void testConvert_FbCom() {
    String originalUrl = "https://fb.com/user/videos/123456789";

    LinkPrefixModel result = converter.convert(originalUrl);

    assertNotNull(result);
    assertEquals(originalUrl, result.getOriginalUrl());
    assertEquals("https://facebookez.com/user/videos/123456789", result.getPrefixedUrl());
  }

  @Test
  void testConvert_InvalidUrl() {
    String invalidUrl = "https://twitter.com/user/status/123";

    assertThrows(IllegalArgumentException.class, () -> converter.convert(invalidUrl));
  }

  @Test
  void testGetPlatformName() {
    assertEquals("Facebook", converter.getPlatformName());
  }
}
