package Prosomiosi_Algorithmou_Dromologisis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


public class algorithmos {
    static int enotita_xronou=3;
    static Queue<diergasia> q1 = new LinkedList<diergasia>();
    static Queue<diergasia> q2 = new LinkedList<diergasia>();
    static Queue<diergasia> q3 = new LinkedList<diergasia>();
    static Queue<diergasia> q4 = new LinkedList<diergasia>();
    static Queue<diergasia> q5 = new LinkedList<diergasia>();
    static Queue<diergasia> q6 = new LinkedList<diergasia>();
    static Queue<diergasia> q7 = new LinkedList<diergasia>();
    static ArrayList<diergasia> pinakas= new ArrayList<diergasia>();
    static Queue[] oures={q1,q2,q3,q4,q5,q6,q7};
    static int xronos=0,count=0;
    
    public static void run(){
        boolean exit_flag=false;
        int index=0;
        while(exit_flag==false){
            if(oures[index].isEmpty()==false){
                diergasia d = (diergasia)oures[index].poll();
                int time_given=0;
                while(d.done==false && time_given<enotita_xronou){
                    if(d.xronos_kme==0){
                        d.apokrisi=xronos-d.xronos_afixis;
                    }
                    d.xronos_kme++;
                    xronos++;
                    time_given++;
                    if(d.xronos_kme==d.xronos_kataigismou){
                        d.done=true;
                        d.epistrofi=xronos-d.xronos_afixis;
                        count++;
                    }
                }
                if(d.done==false){
                    oures[index].add(d);
                }
                exit_flag=true;
            }
            else{
                if(index<6){
                    index++;
                }
                else{
                    exit_flag=true;
                    xronos++;
                }
            }
        }
    }
    
    
    public static void printavgs(ArrayList<diergasia> pinakas){
        System.out.println("\tMESOS OROS APOKRISIS DIERGASION");
        float moa=0;
        for(int i=0;i<pinakas.size();i++){
            System.out.println("Diergasia "+(i+1)+":"+pinakas.get(i).apokrisi);
            moa=moa+pinakas.get(i).apokrisi;
        }
        System.out.println("Genikos Mesos Oros:"+(moa/pinakas.size()));
        System.out.println("\tMESOS OROS EPISTROFIS DIERGASION");
        float moe=0;
        for(int i=0;i<pinakas.size();i++){
            System.out.println("Diergasia "+(i+1)+":"+pinakas.get(i).epistrofi);
            moe=moe+pinakas.get(i).epistrofi;
        }
        System.out.println("Genikos Mesos Oros:"+(moe/pinakas.size()));
    }
    
    public static void random(){
        int i;
        for(i=0;i<oures.length;i++){
            oures[i].clear();
        }
        pinakas.clear();
        xronos=0;
        count=0;
        System.out.println("--------------------------------------------------");
        System.out.println("\tPROSOMIOSI ME TYXAIES TIMES");
        System.out.println("--------------------------------------------------");
        Random r=new Random();
        int size=r.nextInt(10);
        size++;
        System.out.println("Arithmos Diergasion:"+size);
        for(i=0;i<size;i++){
            int xa=r.nextInt(11);
            int xk=r.nextInt(20);
            xk++;
            int p=r.nextInt(7);
            p++;
            diergasia d= new diergasia(xa,xk,p);
            System.out.println("\tSTOIXEIA DIERGASIAS "+(i+1));
            System.out.println("Xronos Afixis:"+xa);
            System.out.println("Xronos Kataigismou:"+xk);
            System.out.println("Arithmos Proteraiotitas:"+p);
            pinakas.add(d);
        }
        ArrayList<diergasia> waiting= new ArrayList<>(pinakas);
        while(count<pinakas.size()){
            for(i=0;i<waiting.size();i++){
                if(waiting.get(i).xronos_afixis<=xronos){
                    oures[waiting.get(i).proteraiotita-1].add(waiting.get(i));
                    waiting.remove(i);
                } 
            }
            run();
        }
        run();
        printavgs(pinakas);
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size,i,xa,xk,p;
        // The algorithm begins by asking the user to give their values of choice.
        System.out.println("--------------------------------------------------");
        System.out.println("\tPROSOMIOSI ALGORITHMOY DROMOLOGISIS");
        System.out.println("--------------------------------------------------");
        System.out.print("Dose arithmo diergasion:");
        size= s.nextInt();
        while(size<=0){
                System.out.println("Mi diathesimos arithmos diergasion...");
                System.out.print("Dose arithmo diergasion:");
                size= s.nextInt();
            }
        for(i=0;i<size;i++){
            System.out.println("\tSTOIXEIA DIERGASIAS "+(i+1));
            System.out.print("Dose xrono afixis:");
            xa=s.nextInt();
            System.out.print("Dose xrono kataigismou:");
            xk=s.nextInt();
            System.out.print("Dose proteraiotita(1-7):");
            p=s.nextInt();
            while(p<1 || p>7){
                System.out.println("Mi diathesimi timi proteraiotitas...");
                System.out.print("Dose proteraiotita(1-7):");
            }
            diergasia d= new diergasia(xa,xk,p);
            pinakas.add(d);
	}
        ArrayList<diergasia> waiting= new ArrayList<>(pinakas);
        while(count<pinakas.size()){
            for(i=0;i<waiting.size();i++){
                if(waiting.get(i).xronos_afixis<=xronos){
                    oures[waiting.get(i).proteraiotita-1].add(waiting.get(i));
                    waiting.remove(i);
                    i--;
                } 
            }
            run();
        }
        printavgs(pinakas);
        String a;
        do{
            System.out.println("H prosomiosi oloklirothike!");
            System.out.print("Thelete na epanalavete me tyxaies times? (Nai/Oxi): ");
            a=s.nextLine();
            if(a.equals("")){
                a=s.nextLine();
            }
            while(a.equals("Nai")==false && a.equals("Oxi")==false){
                System.out.println("Mh diathesimi epilogi...");
                System.out.print("Thelete na epanalavete me tyxaies times? (Nai/Oxi): ");
                a=s.nextLine();
            }
            if(a.equals("Nai")){
                random();
            }
        }while(a.equals("Nai"));
    }  
}
