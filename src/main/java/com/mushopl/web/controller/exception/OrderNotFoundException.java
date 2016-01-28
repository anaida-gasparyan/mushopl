package com.mushopl.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by anaida on 1/26/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class OrderNotFoundException extends RuntimeException {

}
