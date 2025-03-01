package com.mall.xiaomi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class XmException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
