package com.example.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author caofeng
 * @date 2018-3-29
 * @description List、Map、Set互转工具
 */
public class ListMapUtils {

    /**
     * 从集合中获取主键集合
     * @param list
     * @param keyGenerate
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K, A> List<K> getKeyList(List<? extends A> list, KeyGenerate<K, A> keyGenerate) {
        return listConvert2List(list, keyGenerate);
    }

    /**
     * 对list进行分组
     * @param list
     * @param keyGenerate
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K, A> Map<K, List<A>> listConvert2ListMap(List<? extends A> list, KeyGenerate<K, A> keyGenerate) {
        Map<K, List<A>> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            if (map.containsKey(key)) {
                map.get(key).add(a);
            } else {
                ArrayList<A> l = new ArrayList<>(1);
                Collections.addAll(l, a);
                map.put(key, l);
            }
        }
        return map;
    }

    /**
     * 对list进行key转map
     * @param list
     * @param keyGenerate
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K, A> Map<K, A> listConvert2Map(List<? extends A> list, KeyGenerate<K, A> keyGenerate) {
        Map<K, A> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            map.put(key, a);
        }
        return map;
    }

    /**
     * 对list进行key转set
     * @param list
     * @param keyGenerate
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K, A> Set<K> listConvert2Set(List<? extends A> list, KeyGenerate<K, A> keyGenerate) {
        Set<K> set = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {
            return set;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            set.add(key);
        }
        return set;
    }

    /**
     * 从集合中取出key转成list
     * @param list
     * @param keyGenerate
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K, A> List<K> listConvert2List(List<? extends A> list, KeyGenerate<K, A> keyGenerate) {
        List<K> kList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return kList;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            kList.add(key);
        }
        return kList;
    }

    /**
     * 获取大于某个值的对象属性集合
     * @param list
     * @param keyGenerate
     * @param greaterThanThisValue
     * @param <K>
     * @param <A>
     * @return
     */
    public static <K extends Comparable, A> List<K> getGreaterThanOneValueKeyList(List<? extends A> list, KeyGenerate<K, A> keyGenerate, K greaterThanThisValue) {
        List<K> kList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return kList;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            if (key == null || key.compareTo(greaterThanThisValue) <= 0) {
                continue;
            }
            kList.add(key);
        }
        return kList;
    }

    /**
     * 转成key-value map
     * @param list
     * @param keyGenerate
     * @param valueGenerate
     * @param <A>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <A, K, V> Map<K, V> listConvert2KeyValueMap(List<? extends A> list, KeyGenerate<K, A> keyGenerate, ValueGenerate<V, A> valueGenerate) {
        Map<K, V> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        for (A a : list) {
            K key = keyGenerate.getKey(a);
            if (key == null) {
                continue;
            }
            V value = valueGenerate.getValue(a);
            map.put(key, value);
        }
        return map;
    }


    /**
     * 对象中获取key
     * @param <K>
     * @param <A>
     */
    public interface KeyGenerate<K, A> {
        K getKey(A a);
    }

    /**
     * 对象中获取value
     * @param <V>
     * @param <A>
     */
    public interface ValueGenerate<V, A> {
        V getValue(A a);
    }

}
