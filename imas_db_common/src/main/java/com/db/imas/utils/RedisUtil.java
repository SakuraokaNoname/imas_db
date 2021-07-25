package com.db.imas.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 存取缓存工具类
 * @author noname
 * @create 2020/9/21
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 设置一个Object类型的缓存,如果有过期时间,单位为秒
    public void putObj(String key, Object obj, Integer expireSec) {
        if (expireSec != null) {
            stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(obj), expireSec, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(obj));
        }
    }

    // 设置一个自增缓存
    public Long incRaw(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    // 获取一个T类型的缓存,并实现反序列化
    public <T> T getObj(String key, Class<T> clazz) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSONObject.parseObject(json, clazz);
    }

    // 获取一个T类型的缓存列表,并实现反序列化
    public <T> List<T> getObjList(String key, Class<T> clazz) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSONObject.parseArray(json, clazz);
    }

    // 设置一个Map类型的缓存,过期时间的单位为秒
    public void putHashAll(String key, Map<String, String> map, Integer expireSec) {
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
    }

    // 获取一个Map类型的缓存,如果key为空则返空
    public Map<String, String> getHashAll(String key) {
        if (!stringRedisTemplate.hasKey(key)) {
            return null;
        }
        return (Map) stringRedisTemplate.opsForHash().entries(key);
    }

    public <T> T getHashObj(String hashName, String key, Class<T> clazz) {
        String o = (String) stringRedisTemplate.opsForHash().get(hashName, key);
        if (StringUtils.isEmpty(o)) {
            return null;
        }
        return JSONObject.parseObject(o, clazz);
    }

    public String getHashRaw(String hashName, String key) {
        String o = (String) stringRedisTemplate.opsForHash().get(hashName, key);
        if (StringUtils.isEmpty(o)) {
            return null;
        }
        return o;
    }

    public <T> List<T> getHashArray(String hashName, String key, Class<T> clazz) {
        String o = (String) stringRedisTemplate.opsForHash().get(hashName, key);
        if (StringUtils.isEmpty(o)) {
            return null;
        }
        return JSONObject.parseArray(o, clazz);
    }

    public Long incHashRaw(String hashName, String key, long delta) {
        return stringRedisTemplate.opsForHash().increment(hashName, key, delta);
    }

    public void putHashRaw(String hashName, String key, String str, Integer expireSec) {
        boolean hasKey = stringRedisTemplate.hasKey(key);
        stringRedisTemplate.opsForHash().put(hashName, key, str);
        if (!hasKey) {
            stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
        }
    }

    public void putHashRaw(String hashName, String key, String str) {
        stringRedisTemplate.opsForHash().put(hashName, key, str);
    }

    public void putHashObj(String hashName, String key, Object obj, Integer expireSec) {
        boolean hasKey = stringRedisTemplate.hasKey(key);
        stringRedisTemplate.opsForHash().put(hashName, key, JSONObject.toJSONString(obj));
        if (!hasKey) {
            stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
        }
    }

    public void delHashObj(String hashName, String key) {
        stringRedisTemplate.opsForHash().delete(hashName, key);
    }


    public void putRaw(String key, String value) {
        putRaw(key, value, null);
    }

    public void putRaw(String key, String value, Integer expireSec) {
        if (expireSec != null) {
            stringRedisTemplate.opsForValue().set(key, value, expireSec, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }

    public String getRaw(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void putSetRaw(String key, String member, Integer expireSec) {
        stringRedisTemplate.opsForSet().add(key, member);
        stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
    }

    public void putZSetRaw(String key, String member, double score,Integer expireSec) {
        stringRedisTemplate.opsForZSet().add(key,member, score);
        stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
    }

    public Set<String> getZSetRaw(String key) {
        Set<String> set = stringRedisTemplate.opsForZSet().range(key,0,-1);
        if (set.isEmpty()) {
            return null;
        }
        return set;
    }

    public <T> List<T> getZSetRawDesc(String key,Integer pageNo,Integer pageSize,Class<T> clazz) {
        Set<String> set = stringRedisTemplate.opsForZSet().reverseRange(key,(pageNo - 1) * pageSize,pageSize * pageNo - 1);
        if (set.isEmpty()) {
            return null;
        }
        Iterator<String> iterator = set.iterator();
        List<T> list = new ArrayList<>();
        while(iterator.hasNext()){
            String jsonStr = iterator.next();
            list.add(JSONObject.parseObject(jsonStr,clazz));
        }
        return list;
    }

    public <T> T getZSetLastMenber(String key,Class<T> clazz) {
        Set<String> set = stringRedisTemplate.opsForZSet().range(key,-1,-1);
        if (set.isEmpty()) {
            return null;
        }
        Iterator<String> iterator = set.iterator();
        String jsonStr = "";
        while(iterator.hasNext()){
            jsonStr = iterator.next();
            break;
        }
        return JSONObject.parseObject(jsonStr,clazz);
    }

    public void removeZSetRaw(String key,double score){
        stringRedisTemplate.opsForZSet().removeRangeByScore(key,score,score);
    }

    public void putSetRawAll(String key, String[] set, Integer expireSec) {
        stringRedisTemplate.opsForSet().add(key, set);
        stringRedisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
    }

    public void removeSetRaw(String key, String member) {
        stringRedisTemplate.opsForSet().remove(key, member);
    }

    public Long getSetSize(String key){
        return stringRedisTemplate.opsForSet().size(key);
    }

    public Long getZSetSize(String key){
        return stringRedisTemplate.opsForZSet().size(key);
    }

    public boolean isSetMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 获取指定前缀的Key
     *
     * @param prefix
     * @return
     */
    public Set<String> getPrefixKeySet(String prefix) {
        return stringRedisTemplate.keys(prefix + "*");
    }

    public void delPrefixKey(String prefix) {
        Set<String> prefixKeySet = getPrefixKeySet(prefix);
        for (String key : prefixKeySet) {
            stringRedisTemplate.delete(key);
        }
    }

    public Integer getKeysCount(String key){
        return stringRedisTemplate.keys(key).size();
    }

}
