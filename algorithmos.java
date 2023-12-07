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
    static int xronos=-1,done=0;
    
    public static boolean check_if_all_done(Object[] qtocheck){
            for(int i=0;i<qtocheck.length;i++){
                diergasia tocheck= (diergasia)qtocheck[i];
                if(tocheck.done==false){
                        return false;
                }
            }
            return true;
    }
    
    public static void run(){
        for(int i=0;i<oures.length;i++){
            if(oures[i].isEmpty()==false){
                if(oures[i].size()==1){
                    diergasia d = (diergasia)oures[i].remove();
                    d.epistrofi=d.apokrisi+d.xronos_kataigismou;
                    int toxronos=xronos+d.xronos_kataigismou;
                    while(xronos<toxronos){
                        xronos=xronos+enotita_xronou;
                    }
                }
                else{
                    //  Third Step: If there are more than one in the same priority queue, 
                    //  they will get time in CPU following the Round-Robin Algorithm.
                    roundrobin(oures[i]);
                }
            }
        }
    }
    
    public static void roundrobin(Queue q){
        int index=0;
        LinkedList<Queue> oures_rr= new LinkedList();
        oures_rr.add(q);
        Object[] qtocheck = q.toArray();
        while(check_if_all_done(qtocheck)==false){
            if(oures_rr.get(index).isEmpty()==true){
                index++;
            }
            diergasia d = (diergasia)oures_rr.get(index).poll();
            d.xronos_kme=d.xronos_kme+enotita_xronou;
            xronos=xronos+enotita_xronou;
            if(d.xronos_kme>=d.xronos_kataigismou){
                    d.done=true;
                    d.epistrofi=xronos-d.xronos_afixis-(d.xronos_kme-d.xronos_kataigismou);
            }
            else{
                if(oures_rr.size()<=index+1){
                    Queue<diergasia> nextq = new LinkedList();
                    oures_rr.add(nextq);
                    nextq.add(d);
                }
                else{
                    oures_rr.get(index+1).add(d);
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
        int count=0;
        while(count!=pinakas.size()){
            xronos++;
            for(i=0;i<pinakas.size();i++){
                if(pinakas.get(i).xronos_afixis<=xronos && pinakas.get(i).entered==false){
                    pinakas.get(i).apokrisi=xronos-pinakas.get(i).xronos_afixis;
                    pinakas.get(i).xronos_kme=pinakas.get(i).xronos_kme+enotita_xronou;
                    count++;
                    xronos=xronos+enotita_xronou;
                    pinakas.get(i).entered=true;
                    if(pinakas.get(i).xronos_kme>=pinakas.get(i).xronos_kataigismou){
                        pinakas.get(i).done=true;
                        pinakas.get(i).epistrofi=pinakas.get(i).apokrisi+pinakas.get(i).xronos_kataigismou;
                    }
                    else{
                        oures[pinakas.get(i).proteraiotita-1].add(pinakas.get(i));
                    }
                }
            }
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
        //  First step: The ones that arrive get time in CPU.If they don't get completed, 
        //  they get added to their selected priority queue(1-7).
        int count=0;
        while(count!=pinakas.size()){
            xronos++;
            for(i=0;i<pinakas.size();i++){
                if(pinakas.get(i).xronos_afixis<=xronos && pinakas.get(i).entered==false){
                    pinakas.get(i).apokrisi=xronos-pinakas.get(i).xronos_afixis;
                    pinakas.get(i).xronos_kme=pinakas.get(i).xronos_kme+enotita_xronou;
                    count++;
                    xronos=xronos+enotita_xronou;
                    pinakas.get(i).entered=true;
                    if(pinakas.get(i).xronos_kme>=pinakas.get(i).xronos_kataigismou){
                        pinakas.get(i).done=true;
                        pinakas.get(i).epistrofi=pinakas.get(i).apokrisi+pinakas.get(i).xronos_kataigismou;
                    }
                    else{
                        oures[pinakas.get(i).proteraiotita-1].add(pinakas.get(i));
                    }
                }
            }
        }
        //  Second Step: The ones that are left, are in their selected queues. 
        //  Now they will get time in CPU according to their priority.
        run();
        
        //  Print Results (Print the Average Responce Time and Average Turn-Around Time for each one)
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
                // After the first run is finished, the user has the option to run the algorithm again
                // using random values, as long as they respond "Nai".
                random();
            }
        }while(a.equals("Nai"));
    }
    
}
