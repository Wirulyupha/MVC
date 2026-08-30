package controllers;

import java.util.Arrays;
import models.*;

public class VoteController {
    private Election election = Election.getInstance();

    public String submitVote(String voterId, String rank1, String rank2, String rank3) {
        
        if (!election.getStatus().equals("OPEN")) {
            return "ปฏิเสธ : การเลือกตั้งปิดอยู่";
        }

        Voter targetVoter = null;
        for (Voter v : election.getVoters()) {
            if (v.getId().equals(voterId)) {
                targetVoter = v;
                break;
            }
        }

        if (targetVoter == null) {
            return "ปฏิเสธ : ไม่พบรหัสผู้มีสิทธิ์เลือกตั้ง";
        }

        if (!targetVoter.isActive()) {
            return "ปฏิเสธ : ผู้มีสิทธิ์ไม่ Active";
        }

        if (targetVoter.hasVoted()) {
            return "ปฏิเสธ : ใช้สิทธิ์ลงคะแนนไปแล้ว";
        }

        if (rank1.equals(rank2) || rank1.equals(rank3) || rank2.equals(rank3)) {
            return "ปฏิเสธ : ห้ามเลือกผู้สมัครซ้ำกันในบัตรใบเดียว";
        }

        String ballotId = "B0" + (election.getBallots().size() + 1);
        Ballot newBallot = new Ballot(ballotId, voterId, Arrays.asList(rank1, rank2, rank3));
        election.getBallots().add(newBallot);
        targetVoter.setHasVoted(true); 
        return "บันทึกการลงคะแนนเรียบร้อยแล้ว";
    }
}
