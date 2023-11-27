package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private int lastQuotationId;
    private  List<Quotation> quotations;


    public App() {
        lastQuotationId = 0;
        quotations = new ArrayList<>();
        scanner = new Scanner(System.in);

    }

    public void run() {
        while (true) {
            System.out.printf("명령) ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    return; //run함수가 끝나도록
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }

        }
    }

    private void actionWrite() {
        System.out.printf("명언 : ");
        String content = scanner.nextLine();
        System.out.printf("작가 : ");
        String authorName = scanner.nextLine();
        lastQuotationId++;
        quotations.add(new Quotation(lastQuotationId, content, authorName));
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        if(quotations.size() == 0 ){
            System.out.println("등록된 명언이 없습니다.");
        }
        for (int i = quotations.size() - 1; i >= 0; i--) {
            System.out.printf("%d / %s / %s\n"
                    , quotations.get(i).getId(), quotations.get(i).getAuthorName(), quotations.get(i).getContent());
        }
    }

    private void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = findQuotationIndexById(id);
        if(index == -1){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        quotations.remove(index);
        System.out.printf("%d번 명언은 삭제되었습니다.\n", id);

    }

    //해당 Quotation.id의 Quotation index 찾기
    private int findQuotationIndexById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if(quotation.getId() == id){
                return i;
            }
        }

        return -1;

    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        int index = findQuotationIndexById(id);

        if(index == -1){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        Quotation quotation = quotations.get(index);
        System.out.printf("명언(기존) : %s\n", quotation.getContent());
        System.out.printf("명언 : ");
        String content = scanner.nextLine();


        System.out.printf("작가(기존) : %s\n", quotation.getAuthorName());
        System.out.printf("작가 : ");
        String authorName = scanner.nextLine();

        quotation.setContent(content);
        quotation.setAuthorName(authorName);

        //quotations.set(index, quotation); //이미 quotations.get으로 객체의 주소를 받았기 때문에 수정됨
        System.out.printf("%d번 명언을 수정합니다.\n", id);


    }

}
