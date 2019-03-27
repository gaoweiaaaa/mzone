package com.song.entity;

import java.io.Serializable;

public class ResponseEntity<E> implements Serializable {
    private Integer status;
    private E data;

}
