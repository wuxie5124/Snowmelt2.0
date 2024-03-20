package Model;

import ML.MachineLearn;

public class LevelML {
    int level;
    MachineLearn machineLearn;

    public LevelML(int level, MachineLearn machineLearn) {
         this.level = level;
        this.machineLearn = machineLearn;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public MachineLearn getMachineLearn() {
        return machineLearn;
    }

    public void setMachineLearn(MachineLearn machineLearn) {
        this.machineLearn = machineLearn;
    }
}
