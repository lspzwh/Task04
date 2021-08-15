package t1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",8888);
        OutputStream out=socket.getOutputStream();
        out.write("服务器你好！".getBytes());
        socket.shutdownOutput();
        InputStream in=socket.getInputStream();
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        byte[] data=new byte[1024];
        int len;
        while((len=in.read(data))!=-1){
            os.write(data,0,len);
        }
        System.out.println(os.toString());
        out.close();
        in.close();
        socket.close();
        os.close();
    }
}
