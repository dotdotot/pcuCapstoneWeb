//package talkToServer;
//
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TcpServerDiary {
//
//	private int nClientCnt;	// 현재 클라이언트 수
//	private final int nMaxClient=10;
//	
//	
//	public static void main(String[] args) {
//
//		TcpServerDiary im = new TcpServerDiary();
//		im.nClientCnt=0;
//		
//		ServerSocket sockSvr = null; // 서버 소켓
//		Socket sockChild = null; // 차일드 소켓
//		InetSocketAddress isaSvr = null; // 서버 엔드포인트
//		InetSocketAddress isaCld = null; // 클라이언트 엔드포인트
//
//		String strListenIP = "0.0.0.0"; // 리슨 IP
//		int nListenPort = 8888; // 리슨 포트
//
//		try {
//			
//			// 서버 소켓 생성
//			sockSvr = new ServerSocket();
//			isaSvr = new InetSocketAddress(strListenIP, nListenPort);
//			sockSvr.bind(isaSvr);
//
//			// 무한루프의 시작
//			while(true) {
//			
//				System.out.printf("Server--[MSG] 서버 시작 [%s:%d]\n", strListenIP, nListenPort);
//				System.out.printf("Server--[MSG] 접속자 수 = [%d]\n", im.getClientCnt());
//				
//				sockChild = sockSvr.accept(); // Listen & Accept
//				sockChild.setSoLinger(true, 0);
//
//				// 접속한 클라이언트 정보 처리
//				isaCld = (InetSocketAddress) sockChild.getRemoteSocketAddress();
//				System.out.printf("Server--[MSG] 접속자는  [%s:%d] 연결됨 !! \n", isaCld.getHostName(), isaCld.getPort());
//	
//				// 동시 클라이언트 확인
//				if(im.getClientCnt()==im.nMaxClient)
//				{
//					//System.out.printf("[MSG] Client Count=[%d]\n", im.getClientCnt());
//					sockChild.close();
//					System.out.printf("Server--[MSG] 새로운 접속자 (%s:%d) \n"
//							,isaCld.getHostName(), isaCld.getPort());
//					System.out.println();
//					continue;
//				}
//				
//				// 차일드 소켓에 대한 이관 처리
//				//sockChild.close();
//				String strChildName=String.format("CHILD-%s-%d"
//						,isaCld.getHostName(), isaCld.getPort());
//				TCPSvrChild3 svrChild=new TCPSvrChild3(
//						strChildName, sockChild, isaCld, im);
//				svrChild.start();
//				im.incClientCnt();
//				System.out.printf("Server--[MSG] 접속자 수 =[%d]\n", im.getClientCnt());
//				
//				System.out.println();
//				
//			} // end of while
//			// 무한루프의 범위	
//		
//		} catch (Exception e) {
//
//			//System.out.println("Server--[EXP] " + e.getLocalizedMessage());
//
//		} finally {
//			try {				
//				if (sockSvr != null) {
//					if(!sockSvr.isClosed())
//						sockSvr.close();
//					System.out.printf("Server--서버 소켓(%d) 닫힘!\n", nListenPort);
//				}
//			} catch (Exception e) {}
//		}
//
//	}