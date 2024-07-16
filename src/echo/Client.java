package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	//검은화면에서 실행해볼거임  다른컴퓨터에서 돌아가는거임

	public static void main(String[] args) throws IOException {
		
		//종이컵 전화기
		Socket socket=new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("=======================");
		
		System.out.println("[서버에 연결을 요청합니다]");
		socket.connect(new InetSocketAddress("192.168.0.24", 10001));
		
		System.out.println("[서버에 연결 되었습니다]");
		
		//쓰기 스트림
		//OutputStream out=new FileOutputStream("C:\\javaStudy\\MS949.txt"); 대신 밑에줄
		OutputStream out=socket.getOutputStream();	//socket이 주스트림을 만들어서 달라고하면됨
		OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
		BufferedWriter bw=new BufferedWriter(osw);
		
		//읽기 스트림
		InputStream in= socket.getInputStream();
		InputStreamReader isr=new InputStreamReader(in, "UTF-8");
		BufferedReader br=new BufferedReader(isr);
		
		
		//입력 스캐너 준비 (나중에 추가했음)
		Scanner sc= new Scanner(System.in);
		/*
		InputStream scin=System.in;		//스캐너 말고 다른방법으로 입력하는방법
		InputStreamReader scisr=new InputStreamReader(scin, "UTF-8");
		BufferedReader scbr=new BufferedReader(scisr);
		*/
		
		
		while (true) {
			
			//키보드 사용
			System.out.print("입력:");
			String str= sc.nextLine();
			//String str=scbr.readLine();
			
			if("/q".equals(str)) {
				break;
			}
		
			//메세지 보내기 (실행되면 처음으로 메세지 보냄)
			bw.write(str);		//  <--bw.write("안녕"); (나중에 바꿨음 Scanner 사용하려구)
			bw.newLine();
			bw.flush();		//꽉 안차도 그냥 보내주세요 buffered
			
			//메세지 받기	(메세지 보낸거 다시 받기)
			String remsg = br.readLine();
			System.out.println("server:["+remsg+ "]");
		
		}
		

		
		System.out.println("==================");
		System.out.println("<클라이언트 종료>");
		
		/* (잘안됐음)
		//println 만들기 OutputStream 일거야
		 
		OutputStream pout=System.out;
		OutputStreamWriter posw=new OutputStreamWriter(pout, "UTF-8");
		BufferedWriter pbw=new BufferedWriter(posw);
		
		pbw.write("println테스트");
		pbw.newLine();
		pbw.flush();
		
		*/
		
		//닫기
		//pbw.close();
		sc.close();
		//scbr.close();
		br.close();
		bw.close();
		socket.close();
		
	}

}
