package com.exadb.bigdata;

import com.exadb.bigdata.streaming.Serializer;

import java.io.Serializable;

/**
 * @author zhengyangyong
 * 目前默认所有的Key类型都为String
 */
public class UTF8KeySerializer implements Serializer {
  @Override
  public byte[] serialize(Serializable serializable) throws Exception {
    return serializable.toString().getBytes("utf-8");
  }
}
