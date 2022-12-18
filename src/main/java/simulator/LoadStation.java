package simulator;

public class LoadStation {

    private final String tag;

    public String getTag() {
        return tag;
    }

    private boolean busy;

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    private int address;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public LoadStation(String tag) {
        super();
        this.tag = tag;
        this.busy = false;
        this.address = 0;
    }

}
