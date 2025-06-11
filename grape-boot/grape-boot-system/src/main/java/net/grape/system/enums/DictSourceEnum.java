package net.grape.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典数据来源
 *
 * @author 1161095164@qq.com
 *
 */
@Getter
@AllArgsConstructor
public enum DictSourceEnum {
    /**
     * 字典数据
     */
    DICT(0),
    /**
     * 动态SQL
     */
    SQL(1);

    private final int value;
}
