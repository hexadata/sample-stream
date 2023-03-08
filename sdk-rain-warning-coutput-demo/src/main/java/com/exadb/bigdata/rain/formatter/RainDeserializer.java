package com.exadb.bigdata.rain.formatter;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import com.exadb.bigdata.util.JsonUtil;
import com.exadb.bigdata.rain.domain.MessageData;
import com.exadb.bigdata.streaming.Deserializer;

public class RainDeserializer implements Deserializer {
  @Override
  public Serializable deserialize(byte[] data) throws Exception {
    try {
      MessageData messageData = JsonUtil.readValue(new String(data, StandardCharsets.UTF_8), MessageData.class);
      if (messageData != null && messageData.getType() != null ) {
        return messageData;
      }
      return null;
    } catch (IOException e) {
      return null;
    }
  }
}
