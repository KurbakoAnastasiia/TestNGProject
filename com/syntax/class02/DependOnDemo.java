package com.syntax.class02;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependOnDemo {

    @Test
    public void login() {
        System.out.println("I am login in");
        Assert.assertTrue(false); // add it to show how dependsOnMethod works
    }

    @Test (dependsOnMethods = "login")  // if login fails, this Test method will get ignore
    public  void addEmployee() {
        System.out.println("I am adding employee");
    }

   /* @Test()
    public void test3() {
        System.out.println(" an test 3");
    }*/

}
