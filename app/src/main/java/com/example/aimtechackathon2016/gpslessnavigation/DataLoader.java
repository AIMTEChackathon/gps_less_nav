package com.example.aimtechackathon2016.gpslessnavigation;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by peta on 19.3.16.
 */
public class DataLoader {

    private static DataLoader loader = new DataLoader();

    private StorageData storageData;

    private DataLoader() {

    }

    public StorageData getStorageData() {
        return storageData;
    }

    public static DataLoader getInstance() {
        return loader;
    }

    public StorageData loadData(String fileName) {
        storageData = new StorageData();

        int[] map = {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0,
                     1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0,
                     1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                     1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                     1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0,
                     1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0,
                     1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0,
                     1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0,
                     1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0,
                     1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0
                     };
        storageData.setMap(map);

        storageData.setStartX(0);
        storageData.setStartY(0);

        storageData.setDestX(4);
        storageData.setDestY(9);

        storageData.setPath("5L4R4");

        /* Loading from config file */
        /*File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, fileName);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int [] map = new int[StorageData.COLUMNS * StorageData.ROWS * 4];
            for(int i = 0; i< StorageData.ROWS; i++)
            {
                String line = br.readLine();
                for(int j = 0; j < StorageData.COLUMNS; j++)
                {
                    map[i * StorageData.COLUMNS + j] = Integer.parseInt(line.charAt(j)+"");
                }
            }
            storageData.setMap(map);

            br.readLine();
            String line = br.readLine();
            String[] tokens = line.split(",");
            storageData.setStartX(Integer.parseInt(tokens[0]));
            storageData.setStartY(Integer.parseInt(tokens[1]));

            line = br.readLine();
            tokens = line.split(",");
            storageData.setDestX(Integer.parseInt(tokens[0]));
            storageData.setDestY(Integer.parseInt(tokens[1]));

            storageData.setPath(br.readLine());





        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return storageData;
    }
}
