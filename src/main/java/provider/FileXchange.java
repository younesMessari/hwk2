package provider;

import java.io.*;
import java.util.StringTokenizer;
import javax.jws.WebService;

@WebService
public class FileXchange {

    public String[] browse(String path){
        String[] listOfFiles = new String[0];

        if(path.equals("")){
            File toBrowse = new File("ServerShare/");
            File[] subList = toBrowse.listFiles();
            int nOfFiles = 0;
            for(File f: subList) nOfFiles++;
            listOfFiles = new String[nOfFiles];
            int i = 0;
            for(File temp: subList){
                listOfFiles[i] = "ServerShare/" + temp.getName();
                if(temp.isDirectory()) listOfFiles[i] += "/";
                i++;
            }
        } else{
            assert path.endsWith("/");
            File toBrowse = new File(path);
            if(toBrowse.isDirectory()){
                File[] subList = toBrowse.listFiles();
                int nOfFiles = 0;
                for(File f: subList) nOfFiles++;
                listOfFiles = new String[nOfFiles];
                int i = 0;
                for(File temp: subList){
                    listOfFiles[i] = path + temp.getName();
                    if(temp.isDirectory()) listOfFiles[i] += "/";
                    i++;
                }
            }
            else{
                listOfFiles = new String[2];
                listOfFiles[0] = "Error. The path specified is not a folder.";
                listOfFiles[1] = "Do you mean 'download(" + path + ")' ?";
            }
        }

        return listOfFiles;
    }

    public byte[] download(String path){

        File toDownload = new File(path);
        byte[] fileContent = null;
        FileInputStream fileIn = null;
        int size = 0;

        assert toDownload.exists();

        if(toDownload.isFile()){
            try{
                fileIn = new FileInputStream(path);
                size = fileIn.available();
                fileContent = new byte[size];
                fileIn.read(fileContent);
                fileIn.close();
            } 
            catch (FileNotFoundException ex) {}
            catch(IOException ex) {}
        }   

        return fileContent;
    }

    public boolean upload(String path, String fileName, byte[] fileContent){
        if(path.endsWith("/")){
            try{
                FileOutputStream fileOut = new FileOutputStream(path + fileName);
                fileOut.write(fileContent);
                fileOut.close();
            } 
            catch (FileNotFoundException ex){}
            catch (IOException ex){}
        }
        else{
            System.out.println("Error, path specified is not the right format");
            return false;
        }
        return true;
    }

    public boolean rename(String path, String newFileName){
        File toRename = null;
        if(path.endsWith("/"))
            toRename = new File(path);
        else return false;
        if(toRename.exists()){
            if(toRename.isFile()){
                StringTokenizer strk = new StringTokenizer(path, "/");
                String absPath = "";
                int n = strk.countTokens();
                for(int i = 0; i<n-1; i++)
                    absPath += strk.nextToken() + "/";
                
                File newFile = new File(absPath + newFileName);
                toRename.renameTo(newFile);
            }
            else return false;
        }
        else return false;

        return true;
    }

    public boolean delete(String path){
        File toDelete = null;
        if(path.endsWith("/"))
            toDelete = new File(path);
        else 
            return false;
        
        if(toDelete.exists()){
            if(toDelete.isFile()){
                toDelete.delete();
            } else 
                return false;
        } else 
            return false;
        return true;
    }

}
