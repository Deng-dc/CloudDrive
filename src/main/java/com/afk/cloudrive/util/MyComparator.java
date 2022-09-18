package com.afk.cloudrive.util;

import java.util.Comparator;

/**
 * @Author: dengcong
 * @Date: 2022/9/18 - 09 - 18 - 15:58
 * @Description: com.afk.cloudrive.util
 */
public class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
