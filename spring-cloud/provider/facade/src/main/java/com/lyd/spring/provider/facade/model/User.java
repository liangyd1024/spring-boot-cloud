package com.lyd.spring.provider.facade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/21 ProjectName:spring-boot-cloud Version: 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;

    private String password;

}
