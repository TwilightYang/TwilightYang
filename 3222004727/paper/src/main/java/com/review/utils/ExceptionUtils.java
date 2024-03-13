package com.review.utils;

public class ExceptionUtils extends RuntimeException {
        // 构造函数：仅带消息参数
        public ExceptionUtils(String message) {
            super(message);
        }
        // 构造函数：带消息和引起此异常的原因
        public ExceptionUtils(String message, Throwable cause) {
            super(message, cause);
        }
}

