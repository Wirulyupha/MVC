package views;

import models.*;

public class CandidateView {
    public void displayCandidates() {
        Election election = Election.getInstance();
        System.out.println("\n---- [ รายชื่อผู้สมัคร ] ----");
        for (Candidate c : election.getCandidates()) {
            System.out.println(c.getId() + " : " + c.getName());
        }
    }
}
