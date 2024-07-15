package com.caito.rs232demo.services;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SerialService {

    private SerialPort serialPort;

    public String initialize(String portName){
            if (serialPort != null ) {
                if (serialPort.isOpen()) {
                    System.out.println("puerto en uso");
                    return "puerto en uso";
                }
            }
            serialPort = SerialPort.getCommPort(portName);
            serialPort.setBaudRate(9600);
            serialPort.setNumDataBits(8);
            serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
            serialPort.setParity(SerialPort.NO_PARITY);
            if (serialPort.openPort()) {
                System.out.println("open port");
                return "open port";
            } else {
                System.out.println("failed open port");
                return "failed open port";
            }

    }

    public String sendData(String data){
        if (serialPort != null) {
            byte[] dataBytes = data.getBytes();
            serialPort.writeBytes(dataBytes, dataBytes.length);
            return Arrays.toString(dataBytes);
        }else {
            return "failed to send data";
        }

    }

    public String reciveData(){
        byte[] readBuffer = new byte[1024];
        int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
        return new String(readBuffer, 0, numRead);
    }

    public void close(){
        if (serialPort != null){
            serialPort.closePort();
            serialPort = null;
        }
    }
}
