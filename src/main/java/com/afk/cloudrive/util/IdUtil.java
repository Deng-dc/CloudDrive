package com.afk.cloudrive.util;

import java.util.UUID;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 15:25
 * @Description: com.afk.util
 */
public class IdUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
