package com.swust.zj.cache;

public interface JedisClient {
	public String get(String key);
	public String set(String key, String value);
	public String hget(String hash, String key);
	public Long hset(String hash, String key, String value);
	public Long del(String key);
	public Long hdel(String hash, String key);
	public Long incr(String key);
	public Long decr(String key);
	public Long expire(String key, int seconds);
	public Long ttl(String key);
}
