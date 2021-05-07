package storage;

import java.io.*;
import java.util.ArrayList;

public class TextFileFactory<E>  {
    private static TextFileFactory INSTANCE;
    private TextFileFactory(){

    }
    public static TextFileFactory getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new TextFileFactory();
        return INSTANCE;
    }
    public  void saveFile(ArrayList<E> data, String path){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<E> readerFile(String path){
        ArrayList<E> data = new ArrayList<>();
        File file = new File(path);
        try {
            if(file.length()>0){
                FileInputStream fileInputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                data = (ArrayList<E>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }
    public void resetFile(String path){
        File file = new File(path);
        file.delete();
    }

}
