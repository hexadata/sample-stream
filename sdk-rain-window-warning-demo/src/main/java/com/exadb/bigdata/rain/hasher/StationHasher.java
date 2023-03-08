package com.exadb.bigdata.rain.hasher;

import java.util.Map;

import com.exadb.bigdata.rain.domain.MessageData;
import com.exadb.bigdata.streaming.Hasher;
import com.exadb.bigdata.streaming.InputMessage;

/**
 * @author david
 * @date 2021/4/7 5:06 下午
 */
public class StationHasher implements Hasher {
  @Override
  public String hash(InputMessage input, Map<String, String> args) throws Exception {
    MessageData rainData = (MessageData) input.getBody();
    if (rainData.getData() != null && rainData.getData().size() > 0) {
      // 如果想要分组，需要逐个发送
      return rainData.getData().get(0).getStcd();
    }
    return null;
  }
}
