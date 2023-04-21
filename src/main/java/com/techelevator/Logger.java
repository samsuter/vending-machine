package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {


    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter ourFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm a");
    private String logTime = now.format(ourFormat);
    private String newFile = "LogFile.txt";
    private File logFile = new File(newFile);

    public String getNewFile() {
        return newFile;
    }

    public void setNewFile(String newFile) {
        this.newFile = newFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

   public void freshLog(VendingMachine vendingMachine) {
        try (PrintWriter printWriter = new PrintWriter(logFile)) {
            printWriter.println("New Log");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
   }

    public void logFeedMoney(VendingMachine vendingMachine) {
        boolean append = true;
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, append))){
            printWriter.println(logTime + " Feed Money:" + " $" + vendingMachine.getUserFeedMoney() + " $" + vendingMachine.getCurMoney());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logPurchase(VendingMachine vendingMachine) {
        boolean append = true;
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, append))){
            printWriter.println(logTime + " " + vendingMachine.getItemMap().get(vendingMachine.getItemSelection()).getItemName() + " " + vendingMachine.getItemSelection() + " $" + vendingMachine.getItemMap().get(vendingMachine.getItemSelection()).getItemPrice() + " $" + vendingMachine.getCurMoney());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logMakeChange1(VendingMachine vendingMachine) {
        boolean append = true;
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, append))){
            printWriter.print(logTime + " Give Change:" + " $" + vendingMachine.getCurMoney());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logMakeChange2(VendingMachine vendingMachine) {
        boolean append = true;
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, append))){
            printWriter.println(" $" + vendingMachine.getCurMoney());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
