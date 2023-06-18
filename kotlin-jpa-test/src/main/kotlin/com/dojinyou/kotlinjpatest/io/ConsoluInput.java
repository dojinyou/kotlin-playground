package com.dojinyou.kotlinjpatest.io;

import java.util.Scanner;

public class ConsoluInput implements Input {
    // 초기 요구사항
    // 사람 수를 입력 받을 때는 숫자로 입력 받는다.

    private static Scanner sc = new Scanner(System.in);


    @Override
    public String inputCheck() {
        String inputString = sc.read();

        return inputString;
    }

    @Override
    public String input() {
        return sc.read();
    }


    class Application {

        Input input = new ConsoluInput();

        void function() {
            var input = input.input();
            checkInput(input);
//            input.inputCheck();
        }

    }
}
