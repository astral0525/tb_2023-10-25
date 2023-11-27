package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    static int lastQuotationId = 0;
    static List<Quotation> quotations = new ArrayList<>();

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
                lastQuotationId++;
                quotations.add(new Quotation(lastQuotationId, content, authorName));
                System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);

            }else if(cmd.equals("목록")){
                System.out.println("----------------------");
                for(int i=quotations.size()-1; i>=0; i--){
                    System.out.printf("%d / %s / %s\n"
                            , quotations.get(i).id, quotations.get(i).authorName, quotations.get(i).content);
                }
                System.out.println("총개수 : " +  quotations.size());
            }

        }
    }
}
