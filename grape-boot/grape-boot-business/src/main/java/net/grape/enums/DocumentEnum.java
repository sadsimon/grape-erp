package net.grape.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum DocumentEnum {

    /**
     * 01：采购订单
     */
    DOCUMENT_TYPE_01("01","CGDD"),

    /**
     *02：采购入库单
     */
    DOCUMENT_TYPE_02("02","CGRK"),

    /**
     *03：采购退货单
     */
    DOCUMENT_TYPE_03("03","CGTH"),

    /**
     *11：报价单
     */
    DOCUMENT_TYPE_11("11","BJD"),

    /**
     *12：销售订单
     */
    DOCUMENT_TYPE_12("12","XSDD"),

    /**
     *13：销售出库单
     */
    DOCUMENT_TYPE_13("13","XSCK"),

    /**
     *14：销售退货单
     */
    DOCUMENT_TYPE_14("14","XSTH"),

    /**
     *21：调拨单
     */
    DOCUMENT_TYPE_21("21","DBD"),

    /**
     *22：其他入库单
     */
    DOCUMENT_TYPE_22("22","QTRKD"),

    /**
     *23：其他出库单
     */
    DOCUMENT_TYPE_23("23","QTCKD"),

    /**
     *24：成本调整单
     */
    DOCUMENT_TYPE_24("24","CBTZD"),

    /**
     *25：组装单
     */
    DOCUMENT_TYPE_25("25","ZZD"),

    /**
     *26：拆卸单
     */
    DOCUMENT_TYPE_26("26","CXD"),

    /**
     *32：付款单
     */
    DOCUMENT_TYPE_32("32","FKD"),

    /**
     *34：收款单
     */
    DOCUMENT_TYPE_34("34","SKD"),

    /**
     *36：支出单
     */
    DOCUMENT_TYPE_36("36","ZCD"),

    /**
     *37：收入单
     */
    DOCUMENT_TYPE_37("37","SRD"),

    /**
     *38：预付款单
     */
    DOCUMENT_TYPE_38("38","YFKD"),

    /**
     *39：预收款单
     */
    DOCUMENT_TYPE_39("39","YSKD");

    private final String value;
    private final String prefix;

    public static String getprefixByValue(String name) {
        for (DocumentEnum s : DocumentEnum.values()) {
            if (Objects.equals(s.getValue(), name)) {
                return s.getPrefix();
            }
        }
        return null;
    }
}
