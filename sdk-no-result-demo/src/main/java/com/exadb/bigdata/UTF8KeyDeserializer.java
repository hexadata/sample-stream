package com.exadb.bigdata;

import com.exadb.bigdata.streaming.Deserializer;

import java.io.Serializable;


/**
 * @author zhengyangyong
 * 目前默认所有的Key类型都为String
 */
public class UTF8KeyDeserializer implements Deserializer {
  @Override
  public Serializable deserialize(byte[] data) throws Exception {
    return new String(data, "utf-8");
  }
}
