package services;

import com.dropbox.core.DbxException;
import dataaccess.BackupBroker;
import dataaccess.DBException;
import dataaccess.UserBroker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Backup;

/**
 *
 * @author 731866
 */
public class BackupService {

    private UserBroker ab;
    private BackupBroker bb;

    public BackupService() {

        ab = new UserBroker();
        bb = new BackupBroker();
    }

    public void runBackup() {

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date newDate = new Date();
        String date = simpleDateFormat.format(newDate);
        
        String home = System.getProperty("user.home");
        String dump = home + "/backupFile.sql";
        String filename = date + "dump.sql";
        Backup backup = new Backup();
        backup.setBackupName(filename);
        backup.setBackupDate(newDate);

        try {
            bb.insertBackup(backup);
            String[] cmd = {"C:\\Users\\731866\\OneDrive - Southern Alberta Institute of Technology\\Desktop\\FINAL CAPSTONE\\CreativeSyncCapstoneOOF\\src\\main\\java\\database\\sql.bat"};
            Runtime runtime = Runtime.getRuntime();
            Process p;

            p = runtime.exec(cmd);
            int waitFor = p.waitFor();
            p.destroyForcibly();
            String[] append = {"SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;",
                "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;",
                "DROP SCHEMA IF EXISTS `NetflixDB`;",
                "CREATE SCHEMA IF NOT EXISTS `NetflixDB` DEFAULT CHARACTER SET utf8 ;\n"
                + "USE `NetflixDB` ;",};

            File file = new File(home + filename);
            FileWriter fw4 = new FileWriter(file);
            for (int i = 0; i < append.length; i++) {
                fw4.write(append[i] + "\n");
            }
            fw4.flush();

            File toLoop = new File(dump);
            Scanner sc = new Scanner(toLoop);
            while (sc.hasNextLine()) {
                fw4.write(sc.nextLine() + "\n");

            }
            sc.close();
            fw4.close();
            
            InputStream in = new FileInputStream(file);
            
            FileService fs = new FileService();
            fs.uploadBackup(filename, in);
            
            int data = in.read();
            while (data != -1) {
                data = in.read();
            }
            in.close();

        } catch (IOException ex) {
            String s = "Failed";
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());

        } catch (DbxException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DBException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String restoreDatabase(String backupId) {
        Integer newBackupId = Integer.parseInt(backupId);
        Backup backup = bb.getBackupById(newBackupId);
        String home = System.getProperty("user.home");
        FileWriter fw = null;
        try {
            String[] restoreBat = {"@echo off",
                "cd C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin",
                "mysql.exe -u root -ppassword -h localhost netflixdb < " + home + backup.getBackupName()};
            File bat = new File(home + "restoreDB.bat");
            fw = new FileWriter(bat);
            for (int i = 0; i < restoreBat.length; i++) {
                fw.write(restoreBat[i] + "\r\n");
            }
            fw.close();

            String fileBat = home + "restoreDB.bat";
            Runtime runtime = Runtime.getRuntime();
            Process p;
            //removes entry
            p = runtime.exec(fileBat);
            int waitFor = p.waitFor();
            fw.close();

            return "Successful restore";

        } catch (IOException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Backup> getAllBackups() {
        return bb.getAllBackups();
    }
}
