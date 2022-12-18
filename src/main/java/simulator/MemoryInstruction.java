package simulator;

public class MemoryInstruction extends Instruction{
    
    private final int address;

    public int getAddress() {
        return address;
    }

    public MemoryInstruction(int opcode, int ft, int address) {
        super(opcode, ft);
        this.address = address;
    }
}
