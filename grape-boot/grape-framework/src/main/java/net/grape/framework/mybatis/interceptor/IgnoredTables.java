package net.grape.framework.mybatis.interceptor;

import java.util.Arrays;

public enum IgnoredTables {
    SYS_USER,
    SYS_USER_POST,
    SYS_MAIL_CONFIG,
    SYS_MAIL_LOG,
    SYS_MENU,
    SYS_ROLE_DATA_SCOPE,
    SYS_ROLE_MENU,
    SYS_SMS_CONFIG,
    SYS_SMS_LOG,
    SYS_USER_ROLE,
    SYS_ROLE,
    SYS_USER_TOKEN,
    SYS_DICT_TYPE,
    SYS_LOG_LOGIN,
    SYS_PARAMS,
    SYS_DICT_DATA,
    GR_FIELD;

    public static boolean isIgnored(String tableName) {
        return Arrays.stream(IgnoredTables.values())
                .anyMatch(table -> table.name().equalsIgnoreCase(tableName));
    }
}
