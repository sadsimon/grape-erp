package net.grape.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataStateEnum {

    /**
     * 生效
     */
    STATE_EFFECTIVE(1),

    /**
     * 失效
     */
    STATE_FAILURE(0);

    private final int value;
}
