import java.lang.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Map.Entry;
import java.time.format.DateTimeFormatter;
public class account{
    public static void main(String[] args) throws Exception{
        Count count=new Count();

        while(true){
            count.menu();
 
            Scanner s = new Scanner(System.in);
            String choice = s.nextLine();
            String No=choice.trim();  // 앞뒤 공백제거
            if(No.equals("1")) count.income();
            else if(No.equals("2")) count.outlay();
            else if(No.equals("3")) count.book();
            else if(No.equals("4")) count.save();
            else if(No.equals("5")) count.mout();
            else if(No.equals("6")) count.modify();
            else if(No.equals("7")) count.set_date();
            else if(No.equals("8")) count.end();
            else {
            	 System.out.println("1~8 사이의 숫자를 입력해주세요.");
            	 	continue;
            	 	}
        }
    }
} 
 
class Count{
    TreeMap<String,Integer> IncomeMap=new TreeMap<String,Integer>();
    //수입 트리
    TreeMap<String,Integer> OutlayMap=new TreeMap<String,Integer>();
    //지출 트리
    TreeMap<String,Integer> bookMap_income=new TreeMap<String,Integer>();
    //수출트리 
    TreeMap<String,Integer> bookMap_outlay=new TreeMap<String,Integer>();

   
    //부채받은년월
    LocalDate curr=LocalDate.now();
    LocalDate currDate=curr.withDayOfMonth(22);
    
    //부채가 계산되는 년월
    LocalDate nextDate=LocalDate.now();
   //현재 날짜 및 시간
   LocalDateTime today = LocalDateTime.now();
   //날짜 형식
   String DatePattern ="yyyy-MM-dd-W"; // yyyy:년 mm:월 dd:일 W:주차
   // 날짜 형식 변환
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern);
    //지정한 형식에 맞게 날짜 저장
   String today_date = today.format(dtf);
    //옛날 날짜
    LocalDate oldDate=null;
    //파일불러올때 데이터 상수
    static final int SIZE=1000000;
    //메뉴
    public void menu() throws Exception{  //예외처리?
        System.out.printf("┌──────────────────┐\n");
        System.out.printf("│           1. 수입 입력             │\n");
        System.out.printf("│           2. 지출 입력             │\n");
        System.out.printf("│           3. 장부 보기             │\n");
        System.out.printf("│           4. 저장 하기             │\n");
        System.out.printf("│           5. 메모리해제           │\n");
        System.out.printf("│           6. 수정 하기             │\n");
        System.out.printf("│           7. 날짜 설정             |\n");
        System.out.printf("│           8. 종료                    │\n");
        System.out.printf("└──────────────────┘\n");
        System.out.print("입력:>>");
 
    }
    //수입
    public void income() throws Exception{
       Scanner scanner=new Scanner(System.in);
        System.out.println("==가계부 수입 입력==");
        System.out.print("----------<수입항목>----------\n");
        System.out.print("소득-월급,상여금,이자 및 배당금의 수입액\n");
        System.out.print("저축-예금,적금등의 만기\n");
        System.out.print("차입-빌린돈 \n");
        System.out.print("ex)수입항목입력>> 자유형식(예금)\n");
        System.out.print("수입항목입력>>");
        String instr=scanner.nextLine();
        String rm_blank=instr.trim(); // 입력받은 문자열 앞뒤 공백 제거
        System.out.println("수입항목으로 "+rm_blank+" 이(가)입력되었습니다.");
        System.out.print("수입돈입력:>>");
        Integer inmonstr=scanner.nextInt();
 
        if(IncomeMap.containsKey(rm_blank))  // key 값 중복 확인
        {
        	Integer money=IncomeMap.get(rm_blank);
        	IncomeMap.put(rm_blank, money+inmonstr); // key중복시 value를 더해서 저장	
        }
        else IncomeMap.put(rm_blank,inmonstr); 
    System.out.println("수입되었습니다.");
    }
    //지출
    public void outlay() throws Exception{
         Scanner scanner=new Scanner(System.in);
 
        System.out.println("==가계부 지출 입력==");
        System.out.print("----------<지출항목>----------\n");
        System.out.print("가스비,통신비,교통비,보험료,육아,의료비 등\n");
        System.out.print("ex)지출항목입력>> 자유형식(대출)\n");
        System.out.print("지출항목입력:>>");
        String outstr=scanner.nextLine();
        String rm_blank=outstr.trim(); // 입력받은 문자열 앞뒤 공백 제거
        System.out.println("지출항목으로 "+rm_blank+" 이(가)입력되었습니다.");
        System.out.print("지출돈입력:>>");
        Integer outmonstr=scanner.nextInt();
 
        if(OutlayMap.containsKey(rm_blank))  //key 값 중복확인
        {
        	Integer money=OutlayMap.get(rm_blank);
        	OutlayMap.put(rm_blank, money+outmonstr); // key중복시 value를 더해서 저장	
        }
        else OutlayMap.put(rm_blank,outmonstr);
        System.out.println("지출되었습니다.");
    }
   
    //장부
   public void book() throws Exception{
       
        while(true) {
        System.out.printf("┌────────────────────┐\n");
        System.out.printf("│  1. 일별 수입,지출  │\n");
        System.out.printf("│  2. 주차별 수입,지출│\n");
        System.out.printf("│  3. 월별 수입,지출  │\n");
        System.out.printf("│  4. 종료           │\n");
        System.out.printf("└────────────────────┘\n");
        System.out.print("입력:>>");
        Scanner b = new Scanner(System.in);
        String choice = b.nextLine();
        String No=choice.trim();  // 앞뒤 공백제거
        if(No.equals("1")) dailyusage();
        else if(No.equals("2")) weekusage();
        else if(No.equals("3")) monthlyusage();
        else if(No.equals("4")) break;
        else {
        	 System.out.println("1~4 사이의 숫자를 입력해주세요.");
        	 	continue;
        	 	}
        }
}
   public void dailyusage() throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		Integer Total_income = 0;
		Integer Total_outlay = 0;
		Integer Total_debt = 0;
		
		System.out.println("==조회할 일을 입력 하시오 (ex 2021-05-20)==");
		System.out.println("입력:>>");
		String fileDate = scanner.next();
		
		System.out.println("====수입====");
		book_IncomeFile(fileDate);
		
		for (Entry<String, Integer> entry : bookMap_income.entrySet()) {
            System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
            Total_income += entry.getValue();
        }
		System.out.println("===수입 총합===");
		System.out.println(Total_income);
	   	System.out.println();
		bookMap_income.clear();
		
		System.out.println("====지출====");
		book_OutlayFile(fileDate);
		for (Entry<String, Integer> entry : bookMap_outlay.entrySet()) {
            System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
            Total_outlay += entry.getValue();
        }
		System.out.println("===지출 총합===");
		System.out.println(Total_outlay);
	        System.out.println();
		bookMap_outlay.clear();
		
	
	waiting();
	}
	public void weekusage() throws Exception{
		
    	Scanner scanner = new Scanner(System.in);
    	Integer Total_income = 0;
		Integer Total_outlay = 0;
		Integer Total_debt = 0;
		
    	System.out.println("==조회할 월을 입력 하시오 (ex 2021-05)==");
    	System.out.print("입력:>>");
    	String fileMonth = scanner.nextLine();
    	
    	System.out.println("==조회할 주차를 입력하시오");
    	System.out.println("입력:>>");
    	Integer Week = scanner.nextInt();
    	
    	
    	for(int i = 1; i < 32; i++) {
    		String Date = String.format("%02d", i);
    		String fileDate = fileMonth +"-" +Date;

    		if((checkFile(fileDate))){
    		File fileIn=new File("income"+fileDate+".txt");
            FileReader frIn=new FileReader(fileIn);
            
            int readCharNo;
            char[] cbuf=new char[SIZE];
     
            while((readCharNo=frIn.read(cbuf)) != -1){
                String iData=new String(cbuf,0,readCharNo);
     
                StringTokenizer Datasp=new StringTokenizer(iData,"\r\n");
     
                while(Datasp.hasMoreTokens()){ 
                    String token=Datasp.nextToken();  
                    String[] Datasp_i=token.split(":"); 
                    String Datasp_is= new String(Datasp_i[0]);
                    Integer Datasp_ii=new Integer(Datasp_i[1]);
                    
                   
                    String[] Datasp_i_2=Datasp_i[2].split("-");
                    Integer Datasp_week = new Integer(Datasp_i_2[3]);
                    
                    if(Datasp_week.equals(Week)) {
                    if(bookMap_income.isEmpty()) bookMap_income.put(Datasp_is,Datasp_ii);
                    else {
                    for(String a : bookMap_income.keySet()) {
                    	Integer Value = bookMap_income.get(a);
                    	if(Datasp_is.contentEquals(a)) {
                    		Value += Datasp_ii;
                    		bookMap_income.replace(a, Value);
                    	
                    	}
                    	else bookMap_income.put(Datasp_is,Datasp_ii);
                    	}
                    }
                    }
               }
            }
            frIn.close();
    	}
    	}
    	for (Entry<String, Integer> entry : bookMap_income.entrySet()) {
            System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
            Total_income += entry.getValue();
        }
		System.out.println("===수입 총합===");
		System.out.println(Total_income);
		System.out.println();
    	bookMap_income.clear();
    	
    	for(int i = 1; i < 32; i++) {
    		String Date = String.format("%02d", i);
    		String fileDate = fileMonth +"-" +Date;

    		if((checkFile(fileDate))){
    		File fileOut=new File("outlay"+fileDate+".txt");
            FileReader frOut=new FileReader(fileOut);
            
            int readCharNo;
            char[] cbuf=new char[SIZE];
     
            while((readCharNo=frOut.read(cbuf)) != -1){
                String iData=new String(cbuf,0,readCharNo);
     
                StringTokenizer Datasp=new StringTokenizer(iData,"\r\n");
     
                while(Datasp.hasMoreTokens()){ 
                    String token=Datasp.nextToken();  
                    String[] Datasp_i=token.split(":"); 
                    String Datasp_is= new String(Datasp_i[0]);
                    Integer Datasp_ii=new Integer(Datasp_i[1]);
                    
                   
                    String[] Datasp_i_2=Datasp_i[2].split("-");
                    Integer Datasp_week = new Integer(Datasp_i_2[3]);
                    
                    if(Datasp_week.equals(Week)) {
                    if(bookMap_outlay.isEmpty()) bookMap_outlay.put(Datasp_is,Datasp_ii);
                    else {
                    for(String a : bookMap_outlay.keySet()) {
                    	Integer Value = bookMap_outlay.get(a);
                    	if(Datasp_is.contentEquals(a)) {
                    		Value += Datasp_ii;
                    		bookMap_outlay.replace(a, Value);
                    	
                    	}
                    	else bookMap_outlay.put(Datasp_is,Datasp_ii);
                    	}
                    }
                    }
               }
            }
            frOut.close();
    	}
    	}
    	for (Entry<String, Integer> entry : bookMap_outlay.entrySet()) {
            System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
            Total_outlay += entry.getValue();
        }
		System.out.println("===지출 총합===");
		System.out.println(Total_outlay);
		System.out.println();
    	bookMap_outlay.clear();
    	waiting();
    	}
    
    	
 
    public void monthlyusage() throws Exception {
    	
    Scanner scanner = new Scanner(System.in);
    Integer Total_income = 0;
	Integer Total_outlay = 0;
	Integer Total_debt = 0;
	
    	
	System.out.println("==조회할 월을 입력 하시오 (ex 2021-05)==");
	System.out.print("입력:>>");
	String fileMonth = scanner.next();
	
	System.out.println("====수입====");
	for(int i = 1; i < 32; i++) {
		String Date = String.format("%02d", i);
		String fileDate = fileMonth +"-" +Date;
		book_IncomeFile(fileDate);
	}
	for (Entry<String, Integer> entry : bookMap_income.entrySet()) {
        System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
        Total_income += entry.getValue();
    }
	System.out.println("===수입 총합===");
	System.out.println(Total_income);
	    System.out.println();
	bookMap_income.clear();
	
	System.out.println("====지출====");
	for(int i = 1; i < 32; i++) {
		String Date = String.format("%02d", i);
		String fileDate = fileMonth +"-" +Date;
		book_OutlayFile(fileDate);
	}
	for (Entry<String, Integer> entry : bookMap_outlay.entrySet()) {
        System.out.println("[항목]:" + entry.getKey() + " [금액]:" + entry.getValue());
        Total_outlay += entry.getValue();
    }
	System.out.println("===지출 총합===");
	System.out.println(Total_outlay);
	    System.out.println();
	bookMap_outlay.clear();
	
	
	waiting();
}
    
public void waiting() throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String anykey = null;
	System.out.print("종료하려면 엔터를 누르세요");
	anykey = br.readLine();
}

    //저장하기
   public void save() throws Exception{
        File fileIn=new File("income"+this.currDate+".txt");
        FileWriter fwIn=new FileWriter(fileIn,true);
        //추가모드
 
        File fileOut=new File("outlay"+this.currDate+".txt");
        FileWriter fwOut=new FileWriter(fileOut,true);
 
 
        System.out.println("==가계부 저장==");
 
        Set<String> keySetIn=IncomeMap.keySet();
        Iterator<String> keyIteratorIn=keySetIn.iterator();
        while(keyIteratorIn.hasNext()){
            String keyIn=keyIteratorIn.next();
            Integer valueIn=IncomeMap.get(keyIn);
            fwIn.write(keyIn+":"+valueIn+":"+this.today_date+"\r\n");
        }
        fwIn.flush();
        fwIn.close();
 
        Set<String> keySetOut=OutlayMap.keySet();
        Iterator<String> keyIteratorOut=keySetOut.iterator();
        while(keyIteratorOut.hasNext()){
            String keyOut=keyIteratorOut.next();
            Integer valueOut=OutlayMap.get(keyOut);
            fwOut.write(keyOut+":"+valueOut+":"+this.today_date+"\r\n");
        }
        fwOut.flush();
        fwOut.close();
 
       
 
        System.out.println("저장되었습니다.");
    }
    
    //메모리해제
    public void mout() throws Exception{
        IncomeMap.clear();
        OutlayMap.clear();
 
        this.oldDate=null;
 
        System.out.println("메모리 해제되었습니다.");
    }
  // 이터 항목 금액 수정하기
    public void modify() {
    	Scanner s = new Scanner(System.in);
    	System.out.printf("┌──────────────────┐\n");
        System.out.printf("│           1. 수입 수정             │\n");
        System.out.printf("│           2. 지출 수정             │\n");
        System.out.printf("│           3. 메뉴로 가기             │\n");
        System.out.printf("└──────────────────┘\n");
        System.out.print("입력:>>");
    	String choice = s.nextLine();
    	String No = choice.trim(); //공백제거
    	
    	switch(No)
    	{
    	case "1" :
    		System.out.print("수정할 항목 입력>>");
    		String mod= s.nextLine();
    		String mod_in= mod.trim();
    		if(IncomeMap.containsKey(mod_in))
    		{
    			Integer value = IncomeMap.get(mod_in);
    			System.out.print("수정할 금액 입력>>");
    			Integer money= s.nextInt();
    			IncomeMap.put(mod_in, money);
    			System.out.println("----------수정전-----------");
    			System.out.println("[항목]:" + mod_in + " [금액]:" + value+"\n");
    			System.out.println("----------수정후-----------");
    			System.out.println("[항목]:" + mod_in + " [금액]:" + IncomeMap.get(mod_in)+"\n");
    			System.out.println("수입이 성공적으로 수정되었습니다.");
    			break;   			
    		}
    		else {
    			System.out.println("입력한 항목 "+mod_in+"이(가) 존재하지 않습니다.");
    			this.modify();
    		}
    		   break;
    		
    	case "2" :
    		System.out.print("수정할 항목 입력>>");
    		String mod2= s.nextLine();
    		String mod_out= mod2.trim();
    		if(OutlayMap.containsKey(mod_out))
    		{
    			Integer value2 = OutlayMap.get(mod_out);
    			System.out.print("수정할 금액 입력>>");
    			Integer money2= s.nextInt();
    			OutlayMap.put(mod_out, money2);
    			System.out.println("----------수정전-----------");
    			System.out.println("[항목]:" + mod_out + " [금액]:" + value2+"\n");
    			System.out.println("----------수정후-----------");
    			System.out.println("[항목]:" + mod_out + " [금액]:" + OutlayMap.get(mod_out)+"\n");
    			System.out.println("지출이 성공적으로 수정되었습니다.");
    			break;   			
    		}
    		else {
    			System.out.println("입력한 항목 "+mod_out+"이(가) 존재하지 않습니다.");
    			this.modify();
    		}
    		   break;
    
    		
    	case "3" :
    		break;
    	default:
    		 System.out.println("1~3 사이의 숫자를 입력해주세요.");
    		 this.modify();
    	
    	}
    	
    }
    //끝마침
    public void end() throws Exception{
        System.out.println("끝마치겠습니다.");
        System.exit(0);
    }
    
    public boolean checkFile(String fileDate) {
    	File file = new File("income"+fileDate+".txt");
    	return file.exists();
    }
 
     public void book_IncomeFile(String fileDate) throws Exception {
    	
    	if((checkFile(fileDate))){
			
    		File fileIn=new File("income"+fileDate+".txt");
            FileReader frIn=new FileReader(fileIn);
            
            int readCharNo;
            char[] cbuf=new char[SIZE];
     
            while((readCharNo=frIn.read(cbuf)) != -1){
                String iData=new String(cbuf,0,readCharNo);
     
                StringTokenizer Datasp=new StringTokenizer(iData,"\r\n");
     
                while(Datasp.hasMoreTokens()){ 
                    String token=Datasp.nextToken();  
                    String[] Datasp_i=token.split(":"); 
                    String Datasp_is= new String(Datasp_i[0]);
                    Integer Datasp_ii=new Integer(Datasp_i[1]);
                    
                    if(bookMap_income.isEmpty()) bookMap_income.put(Datasp_is,Datasp_ii);
                    else {
                    for(String a : bookMap_income.keySet()) {
                    	Integer Value = bookMap_income.get(a);
                    	if(Datasp_is.contentEquals(a)) {
                    		Value += Datasp_ii;
                    		Replace(bookMap_income, a, Value);
                    	
                    	}
                    	else bookMap_income.put(Datasp_is,Datasp_ii);
                    	}
                    }
                   
               }
            }
            frIn.close();
    	}
    		

    }
    
    public void book_OutlayFile(String fileDate) throws Exception {
    	if((checkFile(fileDate))){
			
    		File fileOut=new File("outlay"+fileDate+".txt");
            FileReader frOut=new FileReader(fileOut);
            
            int readCharNo;
            char[] cbuf=new char[SIZE];
     
            while((readCharNo=frOut.read(cbuf)) != -1){
                String iData=new String(cbuf,0,readCharNo);
     
                StringTokenizer Datasp=new StringTokenizer(iData,"\r\n");
     
                while(Datasp.hasMoreTokens()){ 
                    String token=Datasp.nextToken();  
                    String[] Datasp_i=token.split(":"); 
                    String Datasp_is= new String(Datasp_i[0]);
                    Integer Datasp_ii=new Integer(Datasp_i[1]);
                    
                    if(bookMap_outlay.isEmpty()) bookMap_outlay.put(Datasp_is,Datasp_ii);
                    else {
                    for(String a : bookMap_outlay.keySet()) {
                        	Integer Value = bookMap_outlay.get(a);
                        	if(Datasp_is.contentEquals(a)) {
                        		Value += Datasp_ii;
                        		Replace(bookMap_income, a, Value);
                        	
                        	}
                    	    else bookMap_outlay.put(Datasp_is,Datasp_ii);
                    	}
                    }
                   
               }
            }
            frOut.close();
    	}

    }
 public void Replace(TreeMap a,String Key, Integer Value) {
	 a.replace(Key, Value);
 }
 
 public void set_date() {
	 Scanner scanner = new Scanner(System.in);
	 
	 System.out.println("==날짜를 지정하시오.==");
	 System.out.println("입력:>>");
	 String new_date = scanner.next();
	 
	 
	 this.currDate = this.currDate.parse(new_date);
	 
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 String formattedString = this.currDate.format(formatter);
	 
	 Calendar calendar = Calendar.getInstance();

	 String[] SplitString = formattedString.split("-");
	 int year = Integer.parseInt(SplitString[0]);
	 int month = Integer.parseInt(SplitString[1]);
	 int day = Integer.parseInt(SplitString[2]);
	 calendar.set(year, month, day);
	 int week = calendar.get(Calendar.WEEK_OF_MONTH);
	 
	 today_date = formattedString + "-" + week;

 }
}
