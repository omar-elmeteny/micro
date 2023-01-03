package simulator;

public class ArithmeticInstruction  extends Instruction{

    private final int fs;

    public int getFs() {
        return fs;
    }

    private final int fd;

    public int getFd() {
        return fd;
    }

    public ArithmeticInstruction(int opcode, int ft, int fs, int fd) {
        super(opcode, ft);
        this.fs = fs;
        this.fd = fd;
    }

    @Override
    public String getOperands() {
        return "F" + getFs() + ", F" + getFd();
    }
}
