package tasklist.entity;

public class Task {
    private String name;
    private long PID;
    private Long usedMemory;

    public Task() {
    }

    public Task(String name, long PID, long usedMemory) {
        this.name = name;
        this.PID = PID;
        this.usedMemory = usedMemory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPID() {
        return PID;
    }

    public void setPID(long PID) {
        this.PID = PID;
    }

    public Long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public void addUserMemory(Long usedMemory) {
        this.usedMemory += usedMemory;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return name.equals(other.name) && PID == other.PID && usedMemory == other.usedMemory;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nPID: " + PID +
                "\nUsed memory: " + usedMemory;
    }
}
