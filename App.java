import controllers.AdminController;
import java.util.Scanner;
import views.*;

public class App {
    public static void main(String[] args) {
        VoteView voteView = new VoteView();
        ResultView resultView = new ResultView();
        AdminController adminController = new AdminController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=================================");
            System.out.println("   ระบบเลือกตั้งประธานชมรม  ");
            System.out.println("=================================");
            System.out.println("1. ลงคะแนนเลือกตั้ง (ผู้ใช้)");
            System.out.println("2. ปิดรับคะแนน (เจ้าหน้าที่)");
            System.out.println("3. ตรวจสอบกลุ่มบัตรซ้ำ (เจ้าหน้าที่)");
            System.out.println("4. แสดงสรุปผลคะแนน");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.print("เลือกเมนู (0-4): ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                voteView.showMenu();
            } else if (choice == 2) {
                String msg = adminController.closeVoting();
                System.out.println("-> " + msg);
            } else if (choice == 3) {
                resultView.displayPending();
            } else if (choice == 4) {
                resultView.displayResult();
            } else if (choice == 0) {
                System.out.println("จบการทำงาน");
                break;
            }
        }
    }
}

