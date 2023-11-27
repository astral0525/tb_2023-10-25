package com.ll;

import java.util.Scanner;

class App {
    static int lasQuotationId = 0;
    void run() {
        while (true) {
            System.out.printf("명령) ");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.printf("명언 : ");
                String content = scanner.nextLine();

                System.out.printf("작가 : ");
                String authorName = scanner.nextLine();

                System.out.printf("%d번 명언이 등록되었습니다.\n", lasQuotationId);
            }

        }
    }
}
