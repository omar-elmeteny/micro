package simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import exceptions.SimulatorSyntaxException;

public class CodeParser {

    public static ArrayList<String> readCode(String pathname) throws IOException {
        File file = new File(pathname);
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> code = new ArrayList<>();
        try {
            String st;
            while ((st = br.readLine()) != null) {
                code.add(st);
            }
            return code;
        } finally {
            br.close();
        }

    }

    public static Queue<Instruction> parseCode(String pathname) throws IOException, SimulatorSyntaxException {
        ArrayList<String> code = readCode(pathname);
        Queue<Instruction> instructions = new LinkedList<>();
        for (String c : code) {
            c = c.trim();
            if(c.length() == 0){
                continue;
            }
            try {
                Instruction i = parseLine(c);
                if(i != null) {
                    instructions.add(i);
                }
            } catch (NumberFormatException e) {
                throw new SimulatorSyntaxException("Invalid number: " + e.getMessage());
            }
        }
        return instructions;
    }

    private static int getOpCodeByName(String instruction) throws SimulatorSyntaxException {
        switch (instruction) {
            case "ADD.D":
                return OpCodes.ADD;

            case "SUB.D":
                return OpCodes.SUB;

            case "MUL.D":
                return OpCodes.MUL;

            case "DIV.D":
                return OpCodes.DIV;
            case "L.D":
                return OpCodes.LD;
            case "S.D":
                return OpCodes.SD;
        }
        throw new SimulatorSyntaxException("There is no such instruction (" + instruction + ")");
    }

    private static Instruction parseLine(String line) throws SimulatorSyntaxException {
        line = line.trim();
        String[] args = line.split("\\s+");
        if (args.length == 0) {
            return null;
        }
        int opcode = getOpCodeByName(args[0]);
        switch (opcode) {
            case OpCodes.ADD:
            case OpCodes.SUB:
            case OpCodes.MUL:
            case OpCodes.DIV:
                if (args.length != 4) {
                    throw new SimulatorSyntaxException("Incorrect number of arguments");
                }
                if (!checkRegisterSyntax(args[1]) || !checkRegisterSyntax(args[2]) || !checkRegisterSyntax(args[3])) {
                    throw new SimulatorSyntaxException("Incorrect register used");
                } else if (!checkDestinationRegisterNumber(args[1].substring(1))
                        || !checkSourceRegisterNumber(args[2].substring(1))
                        || !checkSourceRegisterNumber(args[3].substring(1))) {
                    throw new SimulatorSyntaxException("Incorrect register used");
                } else {
                    int ft = Integer.parseInt(args[1].substring(1));
                    int fs = Integer.parseInt(args[2].substring(1));
                    int fd = Integer.parseInt(args[3].substring(1));
                    ArithmeticInstruction i = new ArithmeticInstruction(opcode, ft, fs, fd);
                    return i;
                }
            case OpCodes.LD:
                if(args.length != 3) {
                    throw new SimulatorSyntaxException("Incorrect number of arguments");
                }
                if(!checkRegisterSyntax(args[1])) {
                    throw new SimulatorSyntaxException("Incorrect register used");
                }
                else if (!checkDestinationRegisterNumber(args[1].substring(1))){
                    throw new SimulatorSyntaxException("Incorrect register used");
                }
                else {
                    try {
                        int address = Integer.parseInt(args[2]);
                        int ft = Integer.parseInt(args[1].substring(1));
                        MemoryInstruction i = new MemoryInstruction(opcode, ft, address);
                        return i;
                    }
                    catch (NumberFormatException e) {
                        throw new SimulatorSyntaxException("Second Argument must be a number");
                    }
                }
            case OpCodes.SD:
                if(args.length != 3) {
                    throw new SimulatorSyntaxException("Incorrect number of arguments");
                }
                if(!checkRegisterSyntax(args[1])) {
                    throw new SimulatorSyntaxException("Incorrect register used");
                }
                else if (!checkSourceRegisterNumber(args[1].substring(1))){
                    throw new SimulatorSyntaxException("Incorrect register used");
                }
                else {
                    try {
                        int address = Integer.parseInt(args[2]);
                        int ft = Integer.parseInt(args[1].substring(1));
                        MemoryInstruction i = new MemoryInstruction(opcode, ft, address);
                        return i;
                    }
                    catch (NumberFormatException e) {
                        throw new SimulatorSyntaxException("Second Argument must be a number");
                    }
                }
            default:
                throw new SimulatorSyntaxException("There is no such instruction (" + args[0] + ")");
        }
    }

    public static boolean checkRegisterSyntax(String r) {
        if (r.charAt(0) == 'F') {
            return true;
        }
        return false;
    }

    public static boolean checkSourceRegisterNumber(String number) throws SimulatorSyntaxException {
        try {
            int n = Integer.parseInt(number);
            if (n >= 0 && n <= 31) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            throw new SimulatorSyntaxException(e.getMessage());
        }
    }

    public static boolean checkDestinationRegisterNumber(String number) throws SimulatorSyntaxException {
        try {
            int n = Integer.parseInt(number);
            if (n >= 0 && n <= 31) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            throw new SimulatorSyntaxException("Incorrect register used");
        }
    }
}

