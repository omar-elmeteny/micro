package simulator;

public class ReservationStation {

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

    private int op;

    public void setOp(int op) {
        this.op = op;
    }

    public int getOp() {
        return op;
    }

    private double vj;

    public double getVj() {
        return vj;
    }

    public void setVj(double vj) {
        this.vj = vj;
    }

    private double vk;

    public double getVk() {
        return vk;
    }

    public void setVk(double vk) {
        this.vk = vk;
    }

    private String qj;

    public String getQj() {
        return qj;
    }

    public void setQj(String qj) {
        this.qj = qj;
    }

    private String qk;

    public String getQk() {
        return qk;
    }

    public void setQk(String qk) {
        this.qk = qk;
    }

    private int address;

    public void setAddress(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public ReservationStation(String tag) {
        super();
        this.tag = tag;
        this.busy = false;
        this.op = -1;
        this.vj = 0;
        this.vk = 0;
        this.qj = null;
        this.qk = null;
        this.address = 0;
    }
}
