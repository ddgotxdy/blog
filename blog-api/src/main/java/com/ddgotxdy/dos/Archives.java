package com.ddgotxdy.dos;

import lombok.Data;


/**
 * @author: ddgo
 * @description: do对象，用于文章归档
 */
@Data
public class Archives {

    private Integer year;

    private Integer month;

    private Integer count;
}