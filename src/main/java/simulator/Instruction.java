package simulator;

abstract public class Instruction {
    
    private final int opcode;
    private final int ft;

    public int getFt() {
        return ft;
    }

    public int getOpcode() {
        return opcode;
    }

    public Instruction(int opcode, int ft) {
        super();
        this.opcode = opcode;
        this.ft = ft;
    }
}
