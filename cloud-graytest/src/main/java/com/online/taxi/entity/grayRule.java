package com.online.taxi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author WIN10 .
 * @create 2020-07-09-16:01 .
 * @description .
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class grayRule {

    private Integer id;

    private Integer userId;

    private String version;

}
