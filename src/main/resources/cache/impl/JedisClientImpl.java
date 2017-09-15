package com.swust.zj.cache.impl;

import com.swust.zj.cache.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientImpl implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key,value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hash, String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(hash,key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String hash, String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hash,key,value);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String hash, String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hash,key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

}
