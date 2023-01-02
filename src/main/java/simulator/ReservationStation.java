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

    private Double vj;

    public Double getVj() {
        return vj;
    }

    public void setVj(Double vj) {
        this.vj = vj;
    }

    private Double vk;

    public Double getVk() {
        return vk;
    }

    public void setVk(Double vk) {
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

    private Integer address;

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getAddress() {
        return address;
    }

    private Integer issueCycle;
    
    public Integer getIssueCycle() {
        return issueCycle;
    }

    public void setIssueCycle(Integer issueCycle) {
        this.issueCycle = issueCycle;
    }

    private Integer numberOfCyclesLeft;

    public Integer getNumberOfCyclesLeft() {
        return numberOfCyclesLeft;
    }

    public void setNumberOfCyclesLeft(Integer numberOfCyclesLeft) {
        this.numberOfCyclesLeft = numberOfCyclesLeft;
    }

    private Double value;


    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public ReservationStation(String tag) {
        super();
        this.tag = tag;
        this.busy = false;
        this.op = -1;
        this.vj = null;
        this.vk = null;
        this.qj = null;
        this.qk = null;
        this.address = null;
        this.issueCycle = null;
        this.numberOfCyclesLeft = null;
        this.value = null;
    }
}
