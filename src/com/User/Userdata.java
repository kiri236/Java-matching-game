package com.User;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;

public class Userdata {
    final int N = 1000;
    private File Data;
    private user[] users;
    private int idx;
    private int size;
    String pathname = "D:\\IDEASPACE\\Project1\\Data\\Data.txt";
    private int[] l;
    private int[] r;

    public void Init() {

        this.idx = 2;
        this.size = 0;
        users = new user[N];
        l = new int[N];
        r = new int[N];
        r[0] = 1;
        l[1] = 0;
        Data = new File(pathname);
        if (!Data.exists()) {
            try {
                boolean isFileCreated = Data.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + Data.getName());
                } else {
                    System.out.println("File already exists.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(Data))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String name = line.substring(line.indexOf("Username:") + 9, line.indexOf("Password:"));
                    String password = line.substring(line.indexOf("Password:") + 9);
                    add(new user(name, password));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public user get_info(int k) {
        return users[k];
    }

    public boolean empty() {
        return size == 0;
    }

    protected void add(user user1, int k) {
        users[idx] = user1;
        r[idx] = r[k];
        l[idx] = k;
        l[r[k]] = idx;
        r[k] = idx++;
        size++;
    }

    public void add(user user1) {
        add(user1, l[1]);
    }

    public int find(user user1) {
        if (empty()) return -1;
        for (int i = r[0]; i != 1; i = r[i]) {
            if (users[i].equals(user1)) {
                return i;
            }
        }
        return -1;
    }

    public void finish() {
        if (!Data.exists()) {
            try {
                boolean isFileCreated = Data.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + Data.getName());
                } else {
                    System.out.println("File already exists.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathname))) {
                for (int i = r[0]; i != 1; i = r[i]) {
                    String line = "Username:" + users[i].getUsername() + "Password:" + users[i].getPassword();
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException exz) {
                exz.printStackTrace();
            }
        }

    }

    public boolean Remove(user user1) {
        int k = find(user1);
        if (k == -1) return false;
        r[l[k]] = r[k];
        l[r[k]] = l[k];
        size--;
        return true;
    }

}
