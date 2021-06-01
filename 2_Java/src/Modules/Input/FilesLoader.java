package Modules.Input;

import Model.Abstract;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesLoader {

    public static Abstract loadAbstract(byte[] file) {

        BufferedReader reader = null;

        InputStream input = new ByteArrayInputStream(file);
        reader = new BufferedReader(new InputStreamReader(input));

        String rawAbs = "";
        String sCurrentLine;
        try {
            while ((sCurrentLine = reader.readLine()) != null) {
                rawAbs += sCurrentLine;

            }

            if (reader != null) {
                reader.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Abstract abs = new Abstract(rawAbs);
        return abs;
    }

    public static ArrayList<Abstract> loadAbstracts(String pathToFiles) {
        ArrayList<Abstract> abs = new ArrayList<>();
        BufferedReader br = null;

        File dir = new File(pathToFiles);

        try {
            for (File fn : dir.listFiles()) {
                fn.setReadable(true);
                fn.setWritable(true);
                FileInputStream fstream = new FileInputStream(fn);
                DataInputStream in = new DataInputStream(fstream);
                br = new BufferedReader(new InputStreamReader(in));
                String addRawAbs = "";
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    addRawAbs += strLine + " ";
                }
                Abstract addAbs = new Abstract(addRawAbs);
                abs.add(addAbs);
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Files loaded!");
        return abs;
    }

    public static ArrayList<Abstract> loadTrainingData(String pathToFiles) {
        ArrayList<Abstract> abs = new ArrayList<>();
        BufferedReader br = null;
        File dir = new File(pathToFiles);

        try {
            for (File fn : dir.listFiles()) {
                fn.setReadable(true);
                fn.setWritable(true);
                FileInputStream fstream = new FileInputStream(fn);
                DataInputStream in = new DataInputStream(fstream);
                br = new BufferedReader(new InputStreamReader(in));
                String addRawAbs = "";
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    addRawAbs += strLine + " ";
                };
                abs.addAll(new Abstract().getTrainingData(addRawAbs));
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Files loaded!");
        return abs;
    }

    public static Abstract loadSingleAbstract(String pathToFiles) {
        File dir = new File(pathToFiles);

        BufferedReader br = null;

        String rawAbs = "";
        String sCurrentLine;
        try {
            br = new BufferedReader(new FileReader(pathToFiles));

            while ((sCurrentLine = br.readLine()) != null) {
                rawAbs += sCurrentLine;

            }

            if (br != null) {
                br.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Abstract abs = new Abstract(rawAbs);
        return abs;
    }
    
 

    public static ArrayList<String> loadSingleFile(String path) {
        ArrayList<String> lista = new ArrayList<>();
        BufferedReader br = null;

        String sCurrentLine;
        try {
            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                lista.add(sCurrentLine);

            }
        } catch (Exception ex) {
            Logger.getLogger(FilesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ArrayList<String> retorno = new ArrayList<>();

        for (String s : lista) {
            String[] v = s.split(" ");
            for (int i = 0; i < v.length; i++) {
                if (!v[i].equals("") || !v[i].equals(" ") || v[i] != null) {
                    retorno.add(v[i]);
                }
            }
        }

        return retorno;
    }

    public static ArrayList<String> loadList(String path) {
        ArrayList<String> lista = new ArrayList<>();
        BufferedReader br = null;

        String sCurrentLine;
        try {
            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                lista.add(sCurrentLine);

            }
        } catch (Exception ex) {
            Logger.getLogger(FilesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

}
