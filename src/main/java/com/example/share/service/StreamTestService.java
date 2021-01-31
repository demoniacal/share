package com.example.share.service;

import com.alibaba.fastjson.JSON;
import com.example.share.bo.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTestService {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        intList.add(3);
        intList.add(2);
        intList.add(4);
        intList.add(1);

        User user1 = new User(1L,"aa","18722113241",1,"1,2");
        User user2 = new User(2L,"bb","18722113242",0,"3,4");
        User user3 = new User(3L,"cc","18722113243",1,"5,6");
        User user4 = new User(4L,"dd","18722113244",0,"1,2");
        User user5 = new User(5L,"ee","18722113245",1,"1,2");
        User user6 = new User(6L,"ff","18722113246",0,"1,2");

        userList.add(user3);
        userList.add(user1);
        userList.add(user6);
        userList.add(user5);
        userList.add(user4);
        userList.add(user2);

        List<User> sortList = userList.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        System.out.printf(JSON.toJSONString(userList));
        System.out.printf("\n");
        System.out.printf(JSON.toJSONString(sortList));
        System.out.printf("\n");
        List<Integer> integers = intList.stream().sorted().collect(Collectors.toList());
        System.out.printf(JSON.toJSONString(integers));
        System.out.printf(JSON.toJSONString(intList));
        List<User> userList1 = userList.stream().filter(user -> user.getSex().equals(0)).collect(Collectors.toList());
        System.out.printf(JSON.toJSONString(userList1)+"\n");

        Map<Long,String> idNameMap = userList.stream().collect(Collectors.toMap(User::getId,User::getName));

        System.out.printf(JSON.toJSONString(idNameMap));

    }
}
