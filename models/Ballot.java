package models;

import java.util.List;

public class Ballot {
    private String id;
    private String voterId;
    private List<String> ranking;

    public Ballot(String id, String voterId, List<String> ranking) {
        this.id = id;
        this.voterId = voterId;
        this.ranking = ranking;
    }

    public String getId() { 
        return id; 
    }

    public String getVoterId() { 
        return voterId; 
    }
    
    public List<String> getRanking() { 
        return ranking; 
    }

    public String getPattern() {
        return String.join(">", ranking);
    }
}
