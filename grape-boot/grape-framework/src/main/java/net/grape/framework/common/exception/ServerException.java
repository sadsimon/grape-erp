package net.grape.framework.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public ServerException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}