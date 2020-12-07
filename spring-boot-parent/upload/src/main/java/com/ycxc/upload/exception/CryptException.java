package com.ycxc.upload.exception;

/**
 * 密钥异常类
 */
public class CryptException extends Exception{

    private static final long serialVersionUID = 2843021655596315128L;

    public CryptException(String message){
        super(message);
    }

    public CryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptException(Throwable cause) {
        super(cause);
    }

    public static void main(String[] args) {
        System.out.println(3<<2);
        System.out.println(4>>1);
    }
}
