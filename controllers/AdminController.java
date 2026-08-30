package controllers;

import java.util.*;
import models.*;

public class AdminController {
    private Election election = Election.getInstance();

    public String closeVoting() {
        election.setStatus("CLOSED");

        Map<String, Integer> patternMap = new HashMap<>();
        for (Ballot ballot : election.getBallots()) {
            String pattern = ballot.getPattern();
            if (patternMap.containsKey(pattern)) {
                patternMap.put(pattern, patternMap.get(pattern) + 1);
            } else {
                patternMap.put(pattern, 1);
            }
        }

        election.getGroups().clear();
        for (String pattern : patternMap.keySet()) {
            int count = patternMap.get(pattern);
            if (count >= election.getDuplicatePatternThreshold()) {
                election.getGroups().add(new VoteGroup(pattern, count, "PENDING"));
            }
        }

        return "ปิดรับคะแนนแล้ว พบกลุ่มรอตรวจสอบทั้งหมด " + election.getGroups().size() + " กลุ่ม";
    }

    public void reviewGroup(String pattern, String decision) {
        for (VoteGroup group : election.getGroups()) {
            if (group.getPattern().equals(pattern)) {
                group.setStatus(decision);
            }
        }

        boolean hasPending = false;
        for (VoteGroup group : election.getGroups()) {
            if (group.getStatus().equals("PENDING")) {
                hasPending = true;
                break;
            }
        }

        if (!hasPending) {
            election.setStatus("FINISH");
        }
    }

    public Map<String, Integer> calculateResults() {
        Map<String, Integer> scores = new HashMap<>();

        for (Candidate c : election.getCandidates()) {
            scores.put(c.getId(), 0);
        }

        List<String> invalidPatterns = new ArrayList<>();
        for (VoteGroup group : election.getGroups()) {
            if (group.getStatus().equals("REJECTED") || group.getStatus().equals("PENDING")) {
                invalidPatterns.add(group.getPattern());
            }
        }

        for (Ballot ballot : election.getBallots()) {
            if (!invalidPatterns.contains(ballot.getPattern())) {
                List<String> ranks = ballot.getRanking();
                
                String rank1 = ranks.get(0); 
                String rank2 = ranks.get(1); 
                String rank3 = ranks.get(2); 

                scores.put(rank1, scores.get(rank1) + 3);
                scores.put(rank2, scores.get(rank2) + 2);
                scores.put(rank3, scores.get(rank3) + 1);
            }
        }

        return scores;
    }
}
