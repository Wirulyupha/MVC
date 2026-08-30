package models;

public class VoteGroup {
    private String pattern;
    private int count;
    private String status; 

    public VoteGroup(String pattern, int count, String status) {
        this.pattern = pattern;
        this.count = count;
        this.status = status;
    }

    public String getPattern() { 
        return pattern; 
    }

    public int getCount() { 
        return count; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }
}
