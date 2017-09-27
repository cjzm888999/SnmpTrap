import org.snmp4j.*;  
import org.snmp4j.transport.*;  
import java.io.*;  
import org.snmp4j.smi.*;  
import org.snmp4j.mp.*;  
import org.snmp4j.event.*;  
public class ReadOidNode{  
    public static void main(String[] args) throws Exception{  
    
    try{  
        //�趨CommunityTarget   
        CommunityTarget myTarget = new CommunityTarget();  
        //Address deviceAdd = GenericAddress.parse("udp:2405:200:1413:0:0:0:0:e1f/161"); //����Զ�������ĵ�ַ   
        Address deviceAdd = GenericAddress.parse("udp:172.16.21.119/161");
        myTarget.setAddress(deviceAdd);  //�趨Զ�������ĵ�ַ   
        myTarget.setCommunity(new OctetString("bdcom"));   //����snmp��ͬ��   
        myTarget.setRetries(2);  //���ó�ʱ���Դ���   
        myTarget.setTimeout(5*60);   //���ó�ʱ��ʱ��   
        myTarget.setVersion(SnmpConstants.version2c);//����snmp�汾   
         
        //�趨��ȡ��Э��   
        TransportMapping transport = new DefaultUdpTransportMapping();  //�趨����Э��ΪUDP   
        transport.listen();  
        Snmp protocol = new Snmp(transport);  
         
        //��ȡmib   
        PDU request = new PDU();  
         
        request.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1")));  
        request.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2})));  
        
        request.setType(PDU.GETNEXT);  
        ResponseEvent responseEvent = protocol.send(request, myTarget);  
        PDU response=responseEvent.getResponse();  
        //���   
        if(response != null){  
            System.out.println("request.size()="+request.size());  
            System.out.println("response.size()="+response.size());  
            VariableBinding vb1 = response.get(0);  
            VariableBinding vb2 = response.get(1);  
            System.out.println(vb1);  
            System.out.println(vb2);  
            transport.close();  
        }  
        
      }catch(IOException e){  
          e.printStackTrace();  
          
      }  
    }  
}  