package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super(BizConstants.USER_ALREADY_EXISTS);
    }

}
