/*

Name: Maha Mohammed   ID:202002565
Name: Musherah Moqbel ID:202002276
Name: Abdullah Aktham ID:202004678

*/

import java.util.*;
public class RR{

        public static void main(String[] args)
        {
           Scanner in = new Scanner(System.in);

           //data
           int Pnum=0, q=0;
           int[][] process;
           
        
           //ask input from the user
           System.out.println("\n\t\tRound-Robin (RR) scheduling algorithm");
           System.out.print("\nHow many processes do you want to scheduling: ");
           Pnum=in.nextInt();
           process = new int[Pnum][3];
           System.out.print("\nPlease enter the quantum do you want in (ms): ");
           q=in.nextInt();

           System.out.println("\nPlease enter information of the " + Pnum + " processes:\n");
           System.out.println("\tProcess ID\t\tarrival time(ms)\tburst time(ms)");
           for (int i=0; i<Pnum ; i++)
           {
              for (int j=0; j<3 ; j++)
              {
                 process[i][j]=in.nextInt();
             
              }
           }

     
          //reordering process Array
          int cur, k; 
          for (int i=1;i<Pnum;i++)
          { 
             cur=process[i][1]; 
             int[] curRow=process[i]; 
             k=i-1; 
             while((k>=0)&&process[k][1]>cur)
             { 
                process[k+1]=process[k]; 
                k--; 
             }           
             process[k+1] = curRow; 
          } 

          

          //output
          System.out.println("\n\n\n\t\t\t-----------------------------------");
          System.out.println("\n\nProcesses information: \n");
          System.out.println("\tProcess ID\t\tarrival time(ms)\tburst time(ms)");
          for (int i=0; i<Pnum ; i++)
          {  
             System.out.print("\tp");          
             for (int j=0; j<3 ; j++)
             {
                System.out.print(process[i][j]+ "\t\t\t");
             }
             System.out.println();
          }
           
          
          //solution
          
          int[] taT = new int[Pnum];
          int[] resT = new int[Pnum];  
          int[] wtT = new int[Pnum];
          

          int currentB=0, time=0, size=0, min=q, ID=0 , index=-1 ;
          int[][] waitingList = new int[100][2]; 

          int[][] finishTime = new int [Pnum][2];
          int[][] StartTime = new int [Pnum][2];         

          System.out.println("\n\nGantt chart: \n");

          time = process[0][1];
          System.out.print(time);

          for(int i=0; i<Pnum; i++)
          {

            min=q;
            ID=process[i][0];
            currentB = process[i][2];
            StartTime[i][0] =ID;
           
            System.out.print(" P"+ID);
              
            if(currentB < q)
            {
              min = currentB;
            }
                         
            StartTime[i][1] =time;

            for(int j=0; j<min; j++)
            {
               time++;
               currentB--;
            }

            System.out.print(" "+ time);

            if(currentB != 0)
            {
              size++;        
              waitingList[size-1][0] = ID;         
              waitingList[size-1][1] = currentB;
              
            }
            else
            {
              index++;
              finishTime[index][0] =ID;
              finishTime[index][1] =time; 
            }
  

          }


          
           for(int i=0; i<size; i++)
           {
            min=q;
            ID=waitingList[i][0];
            currentB = waitingList[i][1]; 
           
            System.out.print(" P"+ID);
              
            if(currentB < q)
            {
              min = currentB;
            }
            
            for(int j=0; j<min; j++)
            {
               time++;
               currentB--;
            }

            System.out.print(" "+ time);

            if(currentB != 0)
            {
              size++;
             
              waitingList[size-1][0] = ID;         
              waitingList[size-1][1] = currentB;
              
            }
            else
            {
              index++;
              finishTime[index][0] =ID;
              finishTime[index][1] =time; 
            }

           }

            
          System.out.print("\n\n"); 


          //reordering process Array
          int cu, L; 
          for (int i=1;i<Pnum;i++)
          { 
             cu=process[i][0]; 
             int[] cuRow=process[i]; 
             L=i-1; 
             while((L>=0)&&process[L][0]>cu)
             { 
                process[L+1]=process[L]; 
                L--; 
             }           
             process[L+1] = cuRow; 
          } 


          //reordering StartTime Array
          int currr, H; 
          for (int i=1;i<Pnum;i++)
          { 
             currr=StartTime[i][0]; 
             int[] currrRow=StartTime[i]; 
             H=i-1; 
             while((H>=0)&&StartTime[H][0]>currr)
             { 
                StartTime[H+1]=StartTime[H]; 
                H--; 
             }           
             StartTime[H+1] = currrRow; 
          } 

          
          //reordering finishTime Array
          int cuurrr, R; 
          for (int i=1;i<Pnum;i++)
          { 
             cuurrr=finishTime[i][0]; 
             int[] cuurrrRow=finishTime[i]; 
             R=i-1; 
             while((R>=0)&&finishTime[R][0]>cuurrr)
             { 
                finishTime[R+1]=finishTime[R]; 
                R--; 
             }           
             finishTime[R+1] = cuurrrRow; 
          }
          

          /*
           *turnaround = finishTime - ArraiveTime
           *response   = StartTime  - ArraiveTime
           *waiting    = finishTime - ArraiveTime - BurstTime

           Average = sum of (*) / pnum;         

           */  

           for(int i=0; i<Pnum; i++)
           {
              taT[i] = finishTime[i][1] - process[i][1];
              resT[i] = StartTime[i][1] - process[i][1];
              wtT[i] = finishTime[i][1] - process[i][1] - process[i][2];
           }
           
           double Tsum=0, Rsum=0, Wsum=0, TAvg=0, RAvg=0, WAvg=0;
           for(int i=0; i<Pnum; i++)
           {
              Tsum += taT[i];
              Rsum += resT[i];
              Wsum += wtT[i];
           }
            
           TAvg = Tsum/Pnum;
           RAvg = Rsum/Pnum;
           WAvg = Wsum/Pnum;

           System.out.print("\nProcess\t      Turnaround Time\t       response Time\t          Waiting Time\n"); 
           for(int i=0; i<Pnum; i++)
           {
               System.out.print("p" + process[i][0] + "\t\t"+taT[i] + "\t\t\t" + resT[i]+ "\t\t\t  " + wtT[i] + "\n");
           }

       	   System.out.println("\nAverage turnaround time = "+TAvg);
       	   System.out.println("\nAverage response time = "+RAvg);   
           System.out.println("\nAverage waiting time = "+WAvg);  
          

     }

 }
