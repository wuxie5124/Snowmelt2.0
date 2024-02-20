package UI;

public enum ChooseState {
    Cancel(1),
    OK(0),
    ERROR(2);
    private int state;
    ChooseState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public static ChooseState getEnum(int state) {
        for (ChooseState value : values()) {
             if (value.getState() == state){
                 return value;
             }
        }
        return ERROR;
    }
}
