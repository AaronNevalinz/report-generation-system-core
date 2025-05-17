package com.school.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationReturnObject {

    private Integer returnCode;
    private String returnMessage;
    private Object returnObject;

    public void setReturnCodeAndMessage(Integer returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
    public void setCodeAndMessageAndReturnObject(Integer returnCode, String returnMessage, Object returnObject  ) {
        this.returnObject = returnObject;
        this.returnMessage = returnMessage;
        this.returnCode = returnCode;
    }
}
