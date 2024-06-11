package com.portfolio.www.forum.board.exception;

public class FileDeleteException extends RuntimeException {
	
    public FileDeleteException() {
        super();
    }

    public FileDeleteException(String message) {
        super(message);
    }

    public FileDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileDeleteException(Throwable cause) {
        super(cause);
    }

    protected FileDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
