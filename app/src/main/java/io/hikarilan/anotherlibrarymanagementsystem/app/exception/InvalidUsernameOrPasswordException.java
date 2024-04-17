package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;

public class InvalidUsernameOrPasswordException extends BusinessException {

    public InvalidUsernameOrPasswordException() {
        super(BizConstants.INVALID_USERNAME_OR_PASSWORD);
    }

}
