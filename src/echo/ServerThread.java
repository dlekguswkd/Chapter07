package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{	//출장가서 일해야함

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}	
	
	
	//메소드 gs
	
	//메소드 일반
	@Override
	public void run() {
		
		try {

			System.out.println("[클라이언트가 연결 되었습니다.]");
			
			//읽기 스트림 (실행되면 처음으로 메세지 받음)
			InputStream in=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(in, "UTF-8");
			BufferedReader br=new BufferedReader(isr);
			
			//쓰기 스트림
			OutputStream out=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			
			
			while (true) {
				
				//메세지 받기
				String msg=br.readLine();	//msg는 메세지 줄인말
				
				if(msg==null) {
					break;
				}
				
				System.out.println("받은메세지: "+msg);
				
				//메세지 보내기  (메세지 받은걸 돌려주기)
				bw.write(msg);
				bw.newLine();
				bw.flush();
				
			}
			
		}catch(IOException e) {
			System.out.println(e.toString());
		}
		
		
		
	}

}
