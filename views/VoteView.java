package views;

import controllers.VoteController;
import java.util.Scanner;

public class VoteView {
    private VoteController voteController = new VoteController();
    private CandidateView candidateView = new CandidateView();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n=== [ เมนูลงคะแนนเลือกตั้ง ] ===");
        
        candidateView.displayCandidates();

        System.out.print("\nกรอกรหัสผู้มีสิทธิ์ (เช่น V04): ");
        String voterId = scanner.nextLine();

        System.out.print("เลือกอันดับ 1 (เช่น C01): ");
        String r1 = scanner.nextLine();

        System.out.print("เลือกอันดับ 2 (เช่น C02): ");
        String r2 = scanner.nextLine();

        System.out.print("เลือกอันดับ 3 (เช่น C03): ");
        String r3 = scanner.nextLine();

        String result = voteController.submitVote(voterId, r1, r2, r3);
        showMessage(result);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
