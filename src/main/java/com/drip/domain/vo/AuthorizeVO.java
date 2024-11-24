package com.drip.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    String token;
    Date expire;
}
