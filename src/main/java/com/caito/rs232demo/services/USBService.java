package com.caito.rs232demo.services;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

@Service
public class USBService {

    private SerialPort serialPort;

    public void connect(String portName) {
        serialPort = SerialPort.getCommPorts()[0];
        serialPort.setComPortParameters(9600, 8,
                SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 1000);
        if (serialPort.openPort()) {
            System.out.println("Port opened successfully");
        } else {
            System.out.println("failed to open port");
        }
    }

    public void disconnect() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Port closed successfully");
        }
    }

    public void sendData(String data) {
        if (serialPort != null && serialPort.isOpen()) {
            //byte[] dataBytes = new byte[1024];
            serialPort.writeBytes(data.getBytes(), data.length());
        }
    }

    public String reciveData() {
        if (serialPort != null && serialPort.isOpen()) {
            byte[] dataBytes = new byte[1024];
            int numRead = serialPort.readBytes(dataBytes, dataBytes.length);
            return new String(dataBytes, 0, numRead);
        }
        return null;
    }
}