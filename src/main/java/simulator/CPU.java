package simulator;

import java.util.LinkedList;
import java.util.Queue;

public class CPU {

    private Queue<Instruction> instructionQueue;

    public void setInstructionQueue(Queue<Instruction> instructionQueue) {
        this.instructionQueue = instructionQueue;
    }

    public Queue<Instruction> getInstructionQueue() {
        return instructionQueue;
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

    public CPU(SimulatorSettings settings) {
        super();
        this.settings = settings;
        this.clock = 1;
        instructionQueue = new LinkedList<>();
        registerFile = new Register[32];
        for(int i = 0; i < registerFile.length;i++){
            registerFile[i] = new Register();
        }
        addSubStations = new ReservationStation[settings.getAddSubStationSize()];
        for(int i = 0; i < addSubStations.length;i++) {
            int number = i + 1;
            addSubStations[i] = new ReservationStation("A" + number);
        }
        mulDivStations = new ReservationStation[settings.getMulDivStationSize()];
        for(int i = 0;i < mulDivStations.length;i++) {
            int number = i + 1;
            mulDivStations[i] = new ReservationStation("M" + number);
        }
        loadStations = new LoadStation[settings.getLoadStationSize()];
        for(int i = 0;i < loadStations.length;i++){
            int number = i + 1;
            loadStations[i] = new LoadStation("L" + number);
        }
        storeStations = new StoreStation[settings.getStoreCycles()];
        for(int i = 0;i < storeStations.length;i++){
            int number = i + 1;
            storeStations[i] = new StoreStation("S" + number);
        }
    }
}
