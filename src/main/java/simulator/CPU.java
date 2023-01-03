package simulator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import exceptions.SimulatorRuntimeException;
import view.DisplayProgram;

public class CPU {

    private Queue<Instruction> instructionQueue;

    public void setInstructionQueue(Queue<Instruction> instructionQueue) {
        this.instructionQueue = instructionQueue;
    }

    public Queue<Instruction> getInstructionQueue() {
        return instructionQueue;
    }

    private PriorityQueue<Result> executedInstructions;

    public PriorityQueue<Result> getExecutedInstructions() {
        return executedInstructions;
    }

    public void setExecutedInstructions(PriorityQueue<Result> executedInstructions) {
        this.executedInstructions = executedInstructions;
    }

    private final Register[] registerFile;

    public Register[] getRegisterFile() {
        return registerFile;
    }

    private final ReservationStation[] addSubStations;

    public ReservationStation[] getAddSubStations() {
        return addSubStations;
    }

    private final ReservationStation[] mulDivStations;

    public ReservationStation[] getMulDivStations() {
        return mulDivStations;
    }

    private final LoadStation[] loadStations;

    public LoadStation[] getLoadStations() {
        return loadStations;
    }

    private final StoreStation[] storeStations;

    public StoreStation[] getStoreStations() {
        return storeStations;
    }

    private final SimulatorSettings settings;

    public SimulatorSettings getSettings() {
        return settings;
    }

    private int clock;

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    private final Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public CPU(SimulatorSettings settings, Computer computer) {
        super();
        this.computer = computer;
        this.settings = settings;
        this.clock = 1;
        instructionQueue = new LinkedList<>();
        executedInstructions = new PriorityQueue<>();
        registerFile = new Register[32];
        for (int i = 0; i < registerFile.length; i++) {
            registerFile[i] = new Register();
            registerFile[i].setValue(100 * Math.random() + 1);
            registerFile[i].setIndex(i);

        }
        addSubStations = new ReservationStation[settings.getAddSubStationSize()];
        for (int i = 0; i < addSubStations.length; i++) {
            int number = i + 1;
            addSubStations[i] = new ReservationStation("A" + number);
        }
        mulDivStations = new ReservationStation[settings.getMulDivStationSize()];
        for (int i = 0; i < mulDivStations.length; i++) {
            int number = i + 1;
            mulDivStations[i] = new ReservationStation("M" + number);
        }
        loadStations = new LoadStation[settings.getLoadStationSize()];
        for (int i = 0; i < loadStations.length; i++) {
            int number = i + 1;
            loadStations[i] = new LoadStation("L" + number);
        }
        storeStations = new StoreStation[settings.getStoreCycles()];
        for (int i = 0; i < storeStations.length; i++) {
            int number = i + 1;
            storeStations[i] = new StoreStation("S" + number);
        }
    }

    public boolean isDone() {
        return (instructionQueue.isEmpty() && isEmptyAddSubStation() && isEmptyMulDivStation() && isEmptyLoadStation()
                && isEmptyStoreStation());
    }

    private boolean isEmptyStoreStation() {
        for (int i = 0; i < storeStations.length; i++) {
            if (storeStations[i].isBusy()) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyLoadStation() {
        for (int i = 0; i < loadStations.length; i++) {
            if (loadStations[i].isBusy()) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyMulDivStation() {
        for (int i = 0; i < mulDivStations.length; i++) {
            if (mulDivStations[i].isBusy()) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyAddSubStation() {
        for (int i = 0; i < addSubStations.length; i++) {
            if (addSubStations[i].isBusy()) {
                return false;
            }
        }
        return true;
    }

    public void runProgram() throws SimulatorRuntimeException {
        while (!isDone()) {
            runCycle();
            DisplayProgram.addCycleHtml(this);

            if (clock > 50) {
                System.out.println("Infinite loop");
                return;
            }
            clock++;
        }
    }

    public void runCycle() throws SimulatorRuntimeException {
        Result result = writeBack1();
        execute();
        issue();
        if (result != null) {
            writeBack2(result);
        }
    }

    public boolean issue() {
        if (instructionQueue.isEmpty()) {
            return false;
        }
        Instruction instruction = instructionQueue.peek();
        int opcode = instruction.getOpcode();
        switch (opcode) {
            case OpCodes.ADD:
            case OpCodes.SUB:
                for (int i = 0; i < addSubStations.length; i++) {
                    if (!addSubStations[i].isBusy()) {
                        instructionQueue.remove();
                        addSubStations[i].setOp(opcode);
                        addSubStations[i].setBusy(true);
                        addSubStations[i].setIssueCycle(this.clock);
                        int numberOfCyclesLeft = (opcode == OpCodes.ADD) ? this.settings.getAddCycles()
                                : this.settings.getSubCycles();
                        addSubStations[i].setNumberOfCyclesLeft(numberOfCyclesLeft);
                        String tag = addSubStations[i].getTag();
                        ArithmeticInstruction arithmeticInstruction = (ArithmeticInstruction) instruction;
                        String fs = isDependent(arithmeticInstruction.getFs());
                        if (fs != null) {
                            addSubStations[i].setQj(fs);
                        } else {
                            addSubStations[i].setVj(registerFile[arithmeticInstruction.getFs()].getValue());
                        }
                        String fd = isDependent(arithmeticInstruction.getFd());
                        if (fd != null) {
                            addSubStations[i].setQk(fd);
                        } else {
                            addSubStations[i].setVk(registerFile[arithmeticInstruction.getFd()].getValue());
                        }
                        registerFile[arithmeticInstruction.getFt()].setQi(tag);
                        return true;
                    }
                }
                return false;
            case OpCodes.DIV:
            case OpCodes.MUL:
                for (int i = 0; i < mulDivStations.length; i++) {
                    if (!mulDivStations[i].isBusy()) {
                        instructionQueue.remove();
                        mulDivStations[i].setOp(opcode);
                        mulDivStations[i].setBusy(true);
                        mulDivStations[i].setIssueCycle(clock);
                        int numberOfCyclesLeft = (opcode == OpCodes.MUL) ? this.settings.getMulCycles()
                                : this.settings.getDivCycles();
                        mulDivStations[i].setNumberOfCyclesLeft(numberOfCyclesLeft);
                        String tag = mulDivStations[i].getTag();
                        ArithmeticInstruction arithmeticInstruction = (ArithmeticInstruction) instruction;
                        String fs = isDependent(arithmeticInstruction.getFs());
                        if (fs != null) {
                            mulDivStations[i].setQj(fs);
                        } else {
                            mulDivStations[i].setVj(registerFile[arithmeticInstruction.getFs()].getValue());
                        }
                        String fd = isDependent(arithmeticInstruction.getFd());
                        if (fd != null) {
                            mulDivStations[i].setQk(fd);
                        } else {
                            mulDivStations[i].setVk(registerFile[arithmeticInstruction.getFd()].getValue());
                        }
                        registerFile[arithmeticInstruction.getFt()].setQi(tag);
                        return true;
                    }
                }
                return false;
            case OpCodes.SD:
                for (int i = 0; i < storeStations.length; i++) {
                    if (!storeStations[i].isBusy()) {
                        MemoryInstruction memoryInstruction = (MemoryInstruction) instruction;
                        if (checkAddressInLoadStation(memoryInstruction.getAddress())
                                || checkAddressInStoreStation(memoryInstruction.getAddress())) {
                            return false;
                        }
                        instructionQueue.remove();
                        storeStations[i].setBusy(true);
                        storeStations[i].setIssueCycle(clock);
                        storeStations[i].setNumberOfCyclesLeft(this.settings.getStoreCycles());
                        storeStations[i].setAddress(memoryInstruction.getAddress());
                        String fs = isDependent(memoryInstruction.getFt());
                        if (fs != null) {
                            storeStations[i].setQ(fs);
                        } else {
                            storeStations[i].setV(registerFile[memoryInstruction.getFt()].getValue());
                        }
                        return true;
                    }
                }
                return false;
            case OpCodes.LD:
                for (int i = 0; i < loadStations.length; i++) {
                    MemoryInstruction memoryInstruction = (MemoryInstruction) instruction;
                    if (checkAddressInStoreStation(memoryInstruction.getAddress())) {
                        return false;
                    }
                    if (!loadStations[i].isBusy()) {
                        instructionQueue.remove();
                        loadStations[i].setBusy(true);
                        loadStations[i].setIssueCycle(clock);
                        loadStations[i].setNumberOfCyclesLeft(this.settings.getLoadCycles());
                        loadStations[i].setAddress(memoryInstruction.getAddress());
                        String tag = loadStations[i].getTag();
                        registerFile[memoryInstruction.getFt()].setQi(tag);
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    public boolean checkAddressInStoreStation(Integer address) {
        for (int i = 0; i < storeStations.length; i++) {
            if (storeStations[i].getAddress() == address) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAddressInLoadStation(Integer address) {
        for (int i = 0; i < loadStations.length; i++) {
            if (loadStations[i].getAddress() == address) {
                return true;
            }
        }
        return false;
    }

    public String isDependent(int f) {
        for (int i = 0; i < registerFile.length; i++) {
            if (i == f) {
                if (!registerFile[i].getQi().equals("0")) {
                    return registerFile[i].getQi();
                }
                return null;
            }
        }
        return null;
    }

    public boolean isReadyForExecution(String qj, String qk) {
        return (qj == null && qk == null);
    }

    public boolean isReadyForExecution(String q) {
        return (q == null);
    }

    public void execute() throws SimulatorRuntimeException {
        executeAddSub();
        executeMulDiv();
        executeLoad();
        executeStore();
    }

    private void executeStore() throws SimulatorRuntimeException {
        for (int i = 0; i < storeStations.length; i++) {
            String q = storeStations[i].getQ();
            if (isReadyForExecution(q) && storeStations[i].isBusy()) {
                if (storeStations[i].getNumberOfCyclesLeft() == 1) {
                    int number = i + 1;
                    storeStations[i] = new StoreStation("S" + number);
                } else if (storeStations[i].getNumberOfCyclesLeft() == 2) {
                    computer.getMemory().storeMem(storeStations[i].getV(), storeStations[i].getAddress());
                    storeStations[i].setNumberOfCyclesLeft(storeStations[i].getNumberOfCyclesLeft() - 1);
                } else {
                    storeStations[i].setNumberOfCyclesLeft(storeStations[i].getNumberOfCyclesLeft() - 1);
                }
            }
        }
    }

    private void executeLoad() throws SimulatorRuntimeException {
        for (int i = 0; i < loadStations.length; i++) {
            if (loadStations[i].isBusy()) {
                if (loadStations[i].getNumberOfCyclesLeft() <= 1) {
                    Double value = loadStations[i].getValue();
                    Result result = new Result(value, loadStations[i].getTag(), loadStations[i].getIssueCycle());
                    executedInstructions.add(result);
                } else if (loadStations[i].getNumberOfCyclesLeft() == 2) {
                    Double value = computer.getMemory().loadMem(loadStations[i].getAddress());
                    loadStations[i].setValue(value);
                }
                loadStations[i].setNumberOfCyclesLeft(loadStations[i].getNumberOfCyclesLeft() - 1);
            }
        }
    }

    private void executeMulDiv() throws SimulatorRuntimeException {
        for (int i = 0; i < mulDivStations.length; i++) {
            String qj = mulDivStations[i].getQj();
            String qk = mulDivStations[i].getQk();
            if (isReadyForExecution(qj, qk) && mulDivStations[i].isBusy()) {
                if (mulDivStations[i].getNumberOfCyclesLeft() <= 1) {
                    Double value = mulDivStations[i].getValue();
                    Result result = new Result(value, mulDivStations[i].getTag(), mulDivStations[i].getIssueCycle());
                    executedInstructions.add(result);
                } else if (mulDivStations[i].getNumberOfCyclesLeft() == 2) {
                    Double value;
                    if (mulDivStations[i].getOp() == OpCodes.MUL) {
                        value = mulDivStations[i].getVj() * mulDivStations[i].getVk();
                    } else {
                        try {
                            value = mulDivStations[i].getVj() / mulDivStations[i].getVk();
                        } catch (ArithmeticException e) {
                            throw new SimulatorRuntimeException(e.getMessage());
                        }
                    }
                    mulDivStations[i].setValue(value);
                }
                mulDivStations[i].setNumberOfCyclesLeft(mulDivStations[i].getNumberOfCyclesLeft() - 1);
            }
        }
    }

    private void executeAddSub() {
        for (int i = 0; i < addSubStations.length; i++) {
            String qj = addSubStations[i].getQj();
            String qk = addSubStations[i].getQk();
            if (isReadyForExecution(qj, qk) && addSubStations[i].isBusy()) {
                if (addSubStations[i].getNumberOfCyclesLeft() <= 1) {
                    Double value = addSubStations[i].getValue();
                    Result result = new Result(value, addSubStations[i].getTag(), addSubStations[i].getIssueCycle());
                    executedInstructions.add(result);
                } else if (addSubStations[i].getNumberOfCyclesLeft() == 2) {
                    Double value;
                    if (addSubStations[i].getOp() == OpCodes.ADD) {
                        value = addSubStations[i].getVj() + addSubStations[i].getVk();
                    } else {
                        value = addSubStations[i].getVj() - addSubStations[i].getVk();
                    }
                    addSubStations[i].setValue(value);
                }
                addSubStations[i].setNumberOfCyclesLeft(addSubStations[i].getNumberOfCyclesLeft() - 1);
            }
        }
    }

    public Result writeBack1() {
        Result result = executedInstructions.poll();
        if (result == null) {
            return null;
        }
        while (!executedInstructions.isEmpty()) {
            executedInstructions.poll();
        }
        char tagPrefix = result.getTag().charAt(0);
        switch (tagPrefix) {
            case 'A':
                removeAddSubStation(result.getTag());
                break;
            case 'M':
                removeMulDivStation(result.getTag());
                break;
            case 'L':
                removeLoadStation(result.getTag());
                break;
            default:
                break;
        }
        return result;
    }

    public void writeBack2(Result result) {
        checkAddSubStation(result);
        checkMulDivStation(result);
        checkStoreStation(result);
        checkRegisters(result);
    }

    public void checkRegisters(Result result) {
        for (int i = 0; i < registerFile.length; i++) {
            if (registerFile[i].getQi().equals(result.getTag())) {
                registerFile[i].setValue(result.getValue());
                registerFile[i].setQi("0");
                break;
            }
        }
    }

    public void checkAddSubStation(Result result) {
        for (int i = 0; i < addSubStations.length; i++) {
            if (result.getTag().equals(addSubStations[i].getQj())) {
                addSubStations[i].setQj(null);
                addSubStations[i].setVj(result.getValue());
            }
            if (result.getTag().equals(addSubStations[i].getQk())) {
                addSubStations[i].setQk(null);
                addSubStations[i].setVk(result.getValue());
            }
        }
    }

    public void checkMulDivStation(Result result) {
        for (int i = 0; i < mulDivStations.length; i++) {
            if (result.getTag().equals(mulDivStations[i].getQj())) {
                mulDivStations[i].setQj(null);
                mulDivStations[i].setVj(result.getValue());
            }
            if (result.getTag().equals(mulDivStations[i].getQk())) {
                mulDivStations[i].setQk(null);
                mulDivStations[i].setVk(result.getValue());
            }
        }
    }

    public void checkStoreStation(Result result) {
        for (int i = 0; i < storeStations.length; i++) {
            if (result.getTag().equals(storeStations[i].getQ())) {
                storeStations[i].setQ(null);
                storeStations[i].setV(result.getValue());
            }
        }
    }

    public void removeAddSubStation(String tag) {
        for (int i = 0; i < addSubStations.length; i++) {
            if (tag.equals(addSubStations[i].getTag())) {
                addSubStations[i] = new ReservationStation(tag);
                break;
            }
        }
    }

    public void removeMulDivStation(String tag) {
        for (int i = 0; i < mulDivStations.length; i++) {
            if (tag.equals(mulDivStations[i].getTag())) {
                mulDivStations[i] = new ReservationStation(tag);
                break;
            }
        }
    }

    public void removeLoadStation(String tag) {
        for (int i = 0; i < loadStations.length; i++) {
            if (tag.equals(loadStations[i].getTag())) {
                loadStations[i] = new LoadStation(tag);
                break;
            }
        }
    }
}
