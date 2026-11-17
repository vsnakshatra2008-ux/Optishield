
package model;

public class Machine {
    private int machineId;
    private String machineName;
    private int availableTime;

    public Machine(int machineId, String machineName) {
        this.machineId = machineId;
        this.machineName = machineName;
        this.availableTime = 0;
    }

    public int getMachineId() {
        return machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }
}
