package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {

    static int lastQuotationId;
    static List<Quotation> quotations;
    Scanner scanner;

    App() {
        lastQuotationId = 0;
        quotations = new ArrayList<>();
        scanner = new Scanner(System.in);

    }

    void run() {
        while (true) {
            System.out.printf("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;

            } else if (cmd.equals("등록")) {
                actionWrite();

            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            }

        }
    }

    void actionWrite() {
        System.out.printf("명언 : ");
        String content = scanner.nextLine();
        System.out.printf("작가 : ");
        String authorName = scanner.nextLine();
        lastQuotationId++;
        quotations.add(new Quotation(lastQuotationId, content, authorName));
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotations.size() - 1; i >= 0; i--) {
            System.out.printf("%d / %s / %s\n"
                    , quotations.get(i).id, quotations.get(i).authorName, quotations.get(i).content);
        }
        System.out.println("총개수 : " + quotations.size());
    }

    void actionRemove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);
        if(id == 0){
            System.out.println("id를 정확히 입력해주세요");
        }
        System.out.printf("%d번 명언을 삭제합니다\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {

        String[] cmdBits = cmd.split("\\?", 2);
        //String action = cmdBits[0]; //ex)삭제
        String queryString = cmdBits[1]; //ex)id=3&archive=true

        String[] queryStringBits = queryString.split("&"); //ex)id=3, archive=true..
        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);
            String _paramName = queryParamStrBits[0]; //ex) id
            String paramValue = queryParamStrBits[1]; //ex)3

            if (_paramName.equals(paramName)) {
                try {
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
        }
        return defaultValue;

    }

}
