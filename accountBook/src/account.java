import java.lang.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
 
public class account{
    public static void main(String[] args) throws Exception{
        Count count=new Count();
 
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
}
 
class Count{
    TreeMap<String,Integer> IncomeMap=new TreeMap<String,Integer>();
    //���� Ʈ��
    TreeMap<String,Integer> OutlayMap=new TreeMap<String,Integer>();
    //���� Ʈ��
    TreeMap<String,Integer> DebtMap=new TreeMap<String,Integer>();
    //��ä Ʈ��
 
    //��ä ���� ����
    int ija;
    //��ä ���� ����
    int iyul;
    //��ä�������
    LocalDate currDate=LocalDate.now();
    //��ä�� ���Ǵ� ���
    LocalDate nextDate=LocalDate.now();
    //���� ��¥
    LocalDate oldDate=null;
    //���Ϻҷ��ö� ������ ���
    static final int SIZE=1000000;
    //�޴�
    public void menu() throws Exception{  //����ó��?
        System.out.printf("����������������������������������������\n");
        System.out.printf("��           1. ���� �Է�             ��\n");
        System.out.printf("��           2. ���� �Է�             ��\n");
        System.out.printf("��           3. ��ä �Է�             ��\n");
        System.out.printf("��           4. ��� ����             ��\n");
        System.out.printf("��           5. �ҷ� ����             ��\n");
        System.out.printf("��           6. ���� �ϱ�             ��\n");
        System.out.printf("��           7. �޸�����            ��\n");
        System.out.printf("��           8. ��     ��             ��\n");
        System.out.printf("����������������������������������������\n");
        System.out.print("�Է�:>>");
 
    }
    //����
    public void income() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==����� ���� �Է�==");
        System.out.print("�����׸��Է�:>>");
        String instr=scanner.nextLine();
 
        System.out.print("���Ե��Է�:>>");
        Integer inmonstr=scanner.nextInt();
      //wrapper Ŭ����, null�� ó�� ����
 
        IncomeMap.put(instr,inmonstr);
        System.out.println("���ԵǾ����ϴ�.");
    }
    //����
    public void outlay() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==����� ���� �Է�==");
        System.out.print("�����׸��Է�:>>");
        String outstr=scanner.nextLine();
 
        System.out.print("���⵷�Է�:>>");
        Integer outmonstr=scanner.nextInt();
 
        OutlayMap.put(outstr,outmonstr);
        System.out.println("����Ǿ����ϴ�.");
    }
    //��ä
    public void debt() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==����� ��ä �Է�==");
        System.out.print("��ä�׸��Է�:>>");
        String destr=scanner.nextLine();
 
        System.out.print("��ä���Է�:>>");
        Integer demonstr=scanner.nextInt();
 
        while(true){
            System.out.println("0=������ 1=������");
            System.out.print("�Է�:>>");
            this.ija=scanner.nextInt();
 
            if(this.ija<0 || this.ija>1){
                System.out.println("�߸��Է��ϼ̽��ϴ�.");
                continue;
            }
            break;
        }
 
        while(true){
            System.out.print("����(�ۼ�Ʈ)�Է�>>");
            this.iyul=scanner.nextInt();
 
            if(this.iyul<0){
                System.out.println("�߸��Է��ϼ̽��ϴ�.");
                continue;
            }
            break;
        }
 
        System.out.println("==���ڰ��==");
        System.out.println("���糯¥:"+this.currDate);
 
        if(this.ija==0)
            System.out.println("������--"+this.iyul*demonstr/100+"�� �Դϴ�.");
        else if(this.ija==1)
            System.out.println("������--"+this.iyul*demonstr/100+"�� �Դϴ�.");
 
        DebtMap.put(destr,demonstr);
        System.out.println("��ä�Ǿ����ϴ�.");
    }
    //���
    public void book() throws Exception{
        Scanner scanner=new Scanner(System.in);
 
        System.out.println("==����� ��� ����==");
 
        System.out.println("���Գ���:");
 
        Set<String> keySetIn=IncomeMap.keySet();
        Iterator<String> keyIteratorIn=keySetIn.iterator();
        while(keyIteratorIn.hasNext()){
            String keyIn=keyIteratorIn.next();
            Integer valueIn=IncomeMap.get(keyIn);
            System.out.println(keyIn+"\t"+valueIn+"��");
        }
        System.out.println("===================");
 
        System.out.println("���⳻��:");
 
        Set<String> keySetOut=OutlayMap.keySet();
        Iterator<String> keyIteratorOut=keySetOut.iterator();
        while(keyIteratorOut.hasNext()){
            String keyOut=keyIteratorOut.next();
            Integer valueOut=OutlayMap.get(keyOut);
            System.out.println(keyOut+"\t"+valueOut+"��");
        }
        System.out.println("===================");
 
        System.out.println("��ä����:");
 
        Set<String> keySetDebt=DebtMap.keySet();
        Iterator<String> keyIteratorDebt=keySetDebt.iterator();
        while(keyIteratorDebt.hasNext()){
            String keyDebt=keyIteratorDebt.next();
            Integer valueDebt=DebtMap.get(keyDebt);
            System.out.println(keyDebt+"\t"+valueDebt+"��");
        }
        System.out.println("===================");
 
        System.out.println("��ä���ڳ�����:");
 
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
                System.out.println(keyDebt_1+"--������:"+valueDebt_1*this.iyul/100*afterMonth+"��");
            }
            else if(this.ija==1){
                System.out.println(keyDebt_1+"--������:"+valueDebt_1*this.iyul/100*afterYear+"��");
            }
        }
    }
    //�ҷ�����
    public void load() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("==����� �ҷ�����==");
 
        //��¥ �Է��� �Ƿʷ� "2019-12-19"�� �Է��ϼ���.
        System.out.print("��¥(�����)�Է�:>>");
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
                oldDate=LocalDate.parse(Datasp_i_2[2]); //���� ��¥ �ҷ�����
                DebtMap.put(Datasp_is_2,Datasp_ii_2);
           }
        }
        frDebt.close();
        System.out.println("�ҷ�����Ǿ����ϴ�.");
    }
    //�����ϱ�
    public void save() throws Exception{
        File fileIn=new File("income"+this.currDate+".dat");
        FileWriter fwIn=new FileWriter(fileIn,true);
 
        File fileOut=new File("outlay"+this.currDate+".dat");
        FileWriter fwOut=new FileWriter(fileOut,true);
 
        File fileDebt=new File("debt"+this.currDate+".dat");
        FileWriter fwDebt=new FileWriter(fileDebt,true);
 
        System.out.println("==����� ����==");
 
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
 
        System.out.println("����Ǿ����ϴ�.");
    }
    //�޸�����
    public void mout() throws Exception{
        IncomeMap.clear();
        OutlayMap.clear();
        DebtMap.clear();
 
        this.oldDate=null;
 
        System.out.println("�޸� �����Ǿ����ϴ�.");
    }
    //����ħ
    public void end() throws Exception{
        System.out.println("����ġ�ڽ��ϴ�.");
        System.exit(0);
    }
}