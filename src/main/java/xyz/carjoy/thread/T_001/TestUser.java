package xyz.carjoy.thread.T_001;

import org.apache.commons.lang3.StringUtils;

public class TestUser {
    public static void main(String[] args) {

        User user = new User();
        user.setName("aa");
        if (!StringUtils.isBlank(user.getName())) {
            System.out.println(user.getName()+"==>");
        }


    }
}
