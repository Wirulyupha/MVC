package views;

import controllers.AdminController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import models.*;

public class ResultView {
    private AdminController adminController = new AdminController();
    private Scanner scanner = new Scanner(System.in);

    public void displayPending() {
        Election election = Election.getInstance();
        System.out.println("\n----  รายการกลุ่มบัตรซ้ำที่รอการตรวจสอบ  ----");
        
        if (election.getGroups().isEmpty()) {
            System.out.println("ไม่มีกลุ่มบัตรซ้ำที่ต้องตรวจสอบ");
            return;
        }

        for (VoteGroup group : election.getGroups()) {
            System.out.println("รูปแบบ: " + group.getPattern() + " | จำนวน: " + group.getCount() + " ใบ | สถานะ: " + group.getStatus());
            
            if (group.getStatus().equals("PENDING")) {
                System.out.print("พิจารณากลุ่มนี้ -> (1) อนุมัติ   (2) ปฏิเสธ : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                String review = (choice == 1) ? "APPROVED" : "REJECTED";
                adminController.reviewGroup(group.getPattern(), review);
                System.out.println("-> ปรับสถานะกลุ่ม " + group.getPattern() + " เป็น " + review + " เรียบร้อย");
            }
        }
    }

    public void displayResult() {
        System.out.println("\n=== [ สรุปผลคะแนนการเลือกตั้ง ] ===");
        Map<String, Integer> results = adminController.calculateResults();
        
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(results.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println("ผู้สมัคร " + entry.getKey() + " ได้รับคะแนนรวม: " + entry.getValue() + " คะแนน");
        }
    }
}
