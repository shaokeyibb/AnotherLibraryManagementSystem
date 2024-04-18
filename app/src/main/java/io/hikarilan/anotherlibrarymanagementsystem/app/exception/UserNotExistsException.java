package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;

public class UserNotExistsException extends BusinessException {

    public UserNotExistsException() {
        super(BizConstants.USER_NOT_EXISTS);
    }
}
