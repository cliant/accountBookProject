import java.lang.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
 
public class account{
    public static void main(String[] args) throws Exception{
        Count count=new Count();
 //ㅁㅁㅁaaaaa
        while(true){
            count.menu();
 
            InputStream is=System.in;
            Reader reader=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(reader);
            char No=(char)br.read();
 
            if(No=='1') count.income();
            else if(No=='2') count.outlay();
            else if(No=='3') count.debt();
            else if(No=='4') count.book();
            else if(No=='5') count.load();
            else if(No=='6') count.save();
            else if(No=='7') count.mout();
            else if(No=='8') count.end();
            else continue;
        }
    }
} //
 
class Count{
    TreeMap<String,Integer> IncomeMap=new TreeMap<String,Integer>();
    //수입 트리ㅇ
    TreeMap<String,Integer> OutlayMap=new TreeMap<String,Integer>();
    //지출 트리
    TreeMap<String,Integer> DebtMap=new TreeMap<String,Integer>();
    //부채 트리
 
    //부채 이자 변수
    int ija;
    //부채 이율 변수
    int iyul;
    //부채받은년월
    LocalDate currDate=LocalDate.now();
    //부채가 계산되는 년월
    LocalDate nextDate=LocalDate.now();
    //옛날 날짜
    LocalDate oldDate=null;
    //파일불러올때 데이터 상수
    static final int SIZE=1000000;
    //메뉴
    public void menu() throws Exception{  //예외처리?
        System.out.printf("┌──────────────────┐\n");
        System.out.printf("│           1. 수입 입력             │\n");
        System.out.printf("│           2. 지출 입력             │\n");
        System.out.printf("│           3. 부채 입력             │\n");
        System.out.printf("│           4. 장부 보기             │\n");
        System.out.printf("│           5. 불러 오기             │\n");
        System.out.printf("│           6. 저장 하기             │\n");
        System.out.printf("│           7. 메모리해제            │\n");
        System.out.printf("│           8. 종     료             │\n");
        System.out.printf("└──────────────────┘\n");
        System.out.print("입력:>>");
 
    }
    //수입
    public void income() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==가계부 수입 입력==");
        System.out.print("수입항목입력:>>");
        String instr=scanner.nextLine();
 
        System.out.print("수입돈입력:>>");
        Integer inmonstr=scanner.nextInt();
      //wrapper 클래스, null값 처리 용이
 
        IncomeMap.put(instr,inmonstr);
        System.out.println("수입되었습니다.");
    }
    //지출
    public void outlay() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==가계부 지출 입력==");
        System.out.print("지출항목입력:>>");
        String outstr=scanner.nextLine();
 
        System.out.print("지출돈입력:>>");
        Integer outmonstr=scanner.nextInt();
 
        OutlayMap.put(outstr,outmonstr);
        System.out.println("지출되었습니다.");
    }
    //부채
    public void debt() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==가계부 부채 입력==");
        System.out.print("부채항목입력:>>");
        String destr=scanner.nextLine();
 
        System.out.print("부채돈입력:>>");
        Integer demonstr=scanner.nextInt();
 
        while(true){
            System.out.println("0=월이자 1=년이자");
            System.out.print("입력:>>");
            this.ija=scanner.nextInt();
 
            if(this.ija<0 || this.ija>1){
                System.out.println("잘못입력하셨습니다.");
                continue;
            }
            break;
        }
 
        while(true){
            System.out.print("이율(퍼센트)입력>>");
            this.iyul=scanner.nextInt();
 
            if(this.iyul<0){
                System.out.println("잘못입력하셨습니다.");
                continue;
            }
            break;
        } 
 
        System.out.println("==이자계산==");
        System.out.println("현재날짜:"+this.currDate);
 
        if(this.ija==0)
            System.out.println("월이자--"+this.iyul*demonstr/100+"원 입니다.");
        else if(this.ija==1)
            System.out.println("년이자--"+this.iyul*demonstr/100+"원 입니다.");
 
        DebtMap.put(destr,demonstr);
        System.out.println("부채되었습니다.");
    }
    //장부
    public void book() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==가계부 장부 보기==");
 
        System.out.println("수입내역:");
 
        Set<String> keySetIn=IncomeMap.keySet();
        Iterator<String> keyIteratorIn=keySetIn.iterator();
        while(keyIteratorIn.hasNext()){
            String keyIn=keyIteratorIn.next();
            Integer valueIn=IncomeMap.get(keyIn);
            System.out.println(keyIn+"\t"+valueIn+"원");
        }
        System.out.println("===================");
 
        System.out.println("지출내역:");
 
        Set<String> keySetOut=OutlayMap.keySet();
        Iterator<String> keyIteratorOut=keySetOut.iterator();
        while(keyIteratorOut.hasNext()){
            String keyOut=keyIteratorOut.next();
            Integer valueOut=OutlayMap.get(keyOut);
            System.out.println(keyOut+"\t"+valueOut+"원");
        }
        System.out.println("===================");
 
        System.out.println("부채내역:");
 
        Set<String> keySetDebt=DebtMap.keySet();
        Iterator<String> keyIteratorDebt=keySetDebt.iterator();
        while(keyIteratorDebt.hasNext()){
            String keyDebt=keyIteratorDebt.next();
            Integer valueDebt=DebtMap.get(keyDebt);
            System.out.println(keyDebt+"\t"+valueDebt+"원");
        }
        System.out.println("===================");
 
        System.out.println("부채이자내역서:");
 
        if(this.oldDate!=null){
            this.currDate=this.oldDate;
        }
 
        long afterYear=ChronoUnit.YEARS.between(this.currDate,this.nextDate);
        long afterMonth=ChronoUnit.MONTHS.between(this.currDate,this.nextDate);
 
        Set<String> keySetDebt_1=DebtMap.keySet();
        Iterator<String> keyIteratorDebt_1=keySetDebt_1.iterator();
        while(keyIteratorDebt_1.hasNext()){
            String keyDebt_1=keyIteratorDebt_1.next();
            Integer valueDebt_1=DebtMap.get(keyDebt_1);
            if(this.ija==0){
                System.out.println(keyDebt_1+"--월이자:"+valueDebt_1*this.iyul/100*afterMonth+"원");
            }
            else if(this.ija==1){
                System.out.println(keyDebt_1+"--년이자:"+valueDebt_1*this.iyul/100*afterYear+"원");
            }
        }
    }
    //불러오기
    public void load() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("==가계부 불러오기==");
 
        //날짜 입력은 실례로 "2019-12-19"로 입력하세요.
        System.out.print("날짜(년월일)입력:>>");
        String fileDate=scanner.next();
 
        File fileIn=new File("income"+fileDate+".dat");
        FileReader frIn=new FileReader(fileIn);
 
        File fileOut=new File("outlay"+fileDate+".dat");
        FileReader frOut=new FileReader(fileOut);
 
        File fileDebt=new File("debt"+fileDate+".dat");
        FileReader frDebt=new FileReader(fileDebt);
 
        int readCharNo;
        char[] cbuf=new char[SIZE];
 
        while((readCharNo=frIn.read(cbuf)) != -1){
            String iData=new String(cbuf,0,readCharNo);
 
            StringTokenizer Datasp=new StringTokenizer(iData,"\r\n");
 
            while(Datasp.hasMoreTokens()){
                String token=Datasp.nextToken();
                String[] Datasp_i=token.split("&");
                String Datasp_is=new String(Datasp_i[0]);
                Integer Datasp_ii=new Integer(Datasp_i[1]);
                IncomeMap.put(Datasp_is,Datasp_ii);
           }
        }
        frIn.close();
 
        int readCharNo_1;
        char[] cbuf_1=new char[SIZE];
 
        while((readCharNo_1=frOut.read(cbuf_1)) != -1){
            String iData_1=new String(cbuf_1,0,readCharNo_1);
 
            StringTokenizer Datasp_1=new StringTokenizer(iData_1,"\r\n");
 
            while(Datasp_1.hasMoreTokens()){
                String token_1=Datasp_1.nextToken();
                String[] Datasp_i_1=token_1.split("&");
                String Datasp_is_1=new String(Datasp_i_1[0]);
                Integer Datasp_ii_1=new Integer(Datasp_i_1[1]);
                OutlayMap.put(Datasp_is_1,Datasp_ii_1);
           }
        }
        frOut.close();
 
        int readCharNo_2;
        char[] cbuf_2=new char[SIZE];
 
        while((readCharNo_2=frDebt.read(cbuf_2)) != -1){
            String iData_2=new String(cbuf_2,0,readCharNo_2);
 
            StringTokenizer Datasp_2=new StringTokenizer(iData_2,"\r\n");
 
            while(Datasp_2.hasMoreTokens()){
                String token_2=Datasp_2.nextToken();
                String[] Datasp_i_2=token_2.split("&");
                String Datasp_is_2=new String(Datasp_i_2[0]);
                Integer Datasp_ii_2=new Integer(Datasp_i_2[1]);
                oldDate=LocalDate.parse(Datasp_i_2[2]); //옛날 날짜 불러오기
                DebtMap.put(Datasp_is_2,Datasp_ii_2);
           }
        }
        frDebt.close();
        System.out.println("불러오기되었습니다.");
    }
    //저장하기
    public void save() throws Exception{
        File fileIn=new File("income"+this.currDate+".dat");
        FileWriter fwIn=new FileWriter(fileIn,true);
 
        File fileOut=new File("outlay"+this.currDate+".dat");
        FileWriter fwOut=new FileWriter(fileOut,true);
 
        File fileDebt=new File("debt"+this.currDate+".dat");
        FileWriter fwDebt=new FileWriter(fileDebt,true);
 
        System.out.println("==가계부 저장==");
 
        Set<String> keySetIn=IncomeMap.keySet();
        Iterator<String> keyIteratorIn=keySetIn.iterator();
        while(keyIteratorIn.hasNext()){
            String keyIn=keyIteratorIn.next();
            Integer valueIn=IncomeMap.get(keyIn);
            fwIn.write(keyIn+"&"+valueIn+"\r\n");
        }
        fwIn.flush();
        fwIn.close();
 
        Set<String> keySetOut=OutlayMap.keySet();
        Iterator<String> keyIteratorOut=keySetOut.iterator();
        while(keyIteratorOut.hasNext()){
            String keyOut=keyIteratorOut.next();
            Integer valueOut=OutlayMap.get(keyOut);
            fwOut.write(keyOut+"&"+valueOut+"\r\n");
        }
        fwOut.flush();
        fwOut.close();
 
        Set<String> keySetDebt=DebtMap.keySet();
        Iterator<String> keyIteratorDebt=keySetDebt.iterator();
        while(keyIteratorDebt.hasNext()){
            String keyDebt=keyIteratorDebt.next();
            Integer valueDebt=DebtMap.get(keyDebt);
            fwDebt.write(keyDebt+"&"+valueDebt+"&"+this.currDate+"\r\n");
        }
        fwDebt.flush();
        fwDebt.close();
 
        System.out.println("저장되었습니다.");
    }
    //메모리해제
    public void mout() throws Exception{
        IncomeMap.clear();
        OutlayMap.clear();
        DebtMap.clear();
 
        this.oldDate=null;
 
        System.out.println("메모리 해제되었습니다.");
    }
    //끝마침
    public void end() throws Exception{
        System.out.println("끝마치겠습니다.");
        System.exit(0);
    }
}
