package com.work;

import java.util.StringTokenizer;
import util.str.StrUtil;

public class ReplaceDemo {

    public static void main(String[] args) {
        String str = StrUtil.replaceEverything("Hey*$Hey*$Hey*$", "*", " ");
        System.out.println(str);
    }

}
