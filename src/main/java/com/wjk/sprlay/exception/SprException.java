package com.wjk.sprlay.exception;

import org.springframework.util.StringUtils;

/**
 * @ClassName  SprException
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author WangJKui
 * @date   2019年4月3日 上午9:58:01
 */
public class SprException extends RuntimeException{

	private static final long serialVersionUID = 2298279121476807497L;
	private static final String EXCEPTION_MESSAGE_DEFAULT = "未明确定义的错误";

	public SprException(String message, Throwable cause) {
		super(validateMessage(message), cause);
	}

	public SprException(String message) {
		super(validateMessage(message));
	}

	public SprException(Throwable cause) {
		super(validateMessage(cause.getLocalizedMessage()), cause);
	}

	private static String validateMessage(String message) {
		if (StringUtils.isEmpty(message)) {
			return EXCEPTION_MESSAGE_DEFAULT;
		} else {
			return message;
		}
	}
}
