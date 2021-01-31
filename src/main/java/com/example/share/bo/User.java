package com.example.share.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;

    private String name;

    private String phone;

    private Integer sex;

    private String ids;


}
