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

    public String getName() {
        return getNameByOpCode(opcode);
    }

    public abstract String getOperands();

    public Instruction(int opcode, int ft) {
        super();
        this.opcode = opcode;
        this.ft = ft;
    }

    private static String getNameByOpCode(int opCode) {
        switch (opCode) {
            case OpCodes.ADD:
                return "ADD.D";
                case OpCodes.SUB:
                return "SUB.D";
                case OpCodes.MUL:
                return "MUL.D";
                case OpCodes.DIV:
                return "DIV.D";
                case OpCodes.LD:
                return "L.D";
                case OpCodes.SD:
                return "S.D";
        }
        return "";
    }

    @Override
    public String toString() {
        return "";
    }
}
