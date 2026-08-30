package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Election {
    private static Election instance;
    private String id = "E01";
    private String title = "การเลือกตั้งประธานชมรม";
    private String status = "OPEN";
    private int duplicatePatternThreshold = 3; 
    
    private List<Candidate> candidates = new ArrayList<>();
    private List<Voter> voters = new ArrayList<>();
    private List<Ballot> ballots = new ArrayList<>();
    private List<VoteGroup> groups = new ArrayList<>();

    private Election() {
        candidates.add(new Candidate("C01", "Null Pointer"));
        candidates.add(new Candidate("C02", "Merge Conflict"));
        candidates.add(new Candidate("C03", "Works on My Machine"));
        candidates.add(new Candidate("C04", "404 Policy Not Found"));
        candidates.add(new Candidate("C05", "Ctrl+Z Nation"));

        voters.add(new Voter("V01", "โพยอยู่ไหน", true));
        voters.add(new Voter("V02", "บังเอิญเหมือนกัน", true));
        voters.add(new Voter("V03", "เลือกเองจริง ๆ", true));
        voters.add(new Voter("V04", "ใจตรงกันเฉย ๆ", true));
        voters.add(new Voter("V05", "ขอดูอีกที", true));
        voters.add(new Voter("V06", "บัตรสุดท้าย", true));
        voters.add(new Voter("V07", "ไม่ได้อยู่กลุ่มไลน์", true));

        ballots.add(new Ballot("B01", "V01", Arrays.asList("C01", "C02", "C03")));
        voters.get(0).setHasVoted(true);

        ballots.add(new Ballot("B02", "V02", Arrays.asList("C01", "C02", "C03")));
        voters.get(1).setHasVoted(true);

        ballots.add(new Ballot("B03", "V03", Arrays.asList("C02", "C03", "C04")));
        voters.get(2).setHasVoted(true);
    }

    public static Election getInstance() {
        if (instance == null) {
            instance = new Election();
        }
        return instance;
    }

    public String getId() { 
        return id; 
    }

    public String getTitle() { 
        return title; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public int getDuplicatePatternThreshold() { 
        return duplicatePatternThreshold; 
    }
    public List<Candidate> getCandidates() { 
        return candidates; 
    }
    public List<Voter> getVoters() { 
        return voters; 
    }
    public List<Ballot> getBallots() { 
        return ballots; 
    }
    public List<VoteGroup> getGroups() { 
        return groups; 
    }
}
