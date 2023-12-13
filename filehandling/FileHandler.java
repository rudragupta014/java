package filehandling;

import java.io.FileWriter;
import users.Doctor;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class FileHandler {
    public static String path="C:/Users/rudra/OneDrive/Desktop/rudra/doctors.txt";


    public static void writeFile(Doctor docs[],Doctor newDoc,String userid){
        String writeData="";
        try{
            File f = new File(path);
            FileWriter w  = new FileWriter(path);
            for(Doctor d:docs){
                if(d!=null){
                 if(!userid.equals(d.userid)){
                 writeData+=d.userid+"\n"+d.name+"\n"+d.pwd+"\n"+d.type+"\n"+d.age+"\n"+d.specialization+"\n"+d.experience+"\n\n";
                 }
                }
            }
            if(newDoc!=null){
                 writeData+=newDoc.userid+"\n"+newDoc.name+"\n"+newDoc.pwd+"\n"+newDoc.type+"\n"+newDoc.age+"\n"+newDoc.specialization+"\n"+newDoc.experience+"\n\n";     
            }
            w.write(writeData);
            w.close();
        }catch(Exception e){
            System.out.println(e);
        }   
    }

    public static Doctor[] readFile(){
        Doctor[] doctors = new Doctor[10];
        try{
            FileReader r=new FileReader(path);
            BufferedReader reader = new BufferedReader(r);
            String line;
            String userId="",name="",pwd="",type="",sp="";
            int exp=0,age=0;
            int j=0;
            while((line=reader.readLine())!=null){
                userId = line;
                name = reader.readLine();
                pwd = reader.readLine();
                type = reader.readLine();
                age = Integer.parseInt(reader.readLine());
                sp = reader.readLine();
                exp = Integer.parseInt(reader.readLine());
                // System.out.println(userId);
                doctors[j] = new Doctor(userId, pwd, name, age);
                doctors[j].setExperience(exp);
                doctors[j].setSpecialization(sp);
                j++;
                reader.readLine();
            }}
            catch(Exception e){
                System.out.println(e);
            }
            return doctors;
    }
}
