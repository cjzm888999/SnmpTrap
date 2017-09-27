import java.io.IOException;
import java.util.Vector;

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;


public class TrapSendUtil {
	
	
	public static void sendTrapV3()
	{
		

		        OctetString user = new OctetString("phyane");
				OctetString passwd = new OctetString("phyane2012");
				Address targetAddress = GenericAddress.parse("udp:172.16.21.121/162");   
				TransportMapping transport;
				try {
					transport = new DefaultUdpTransportMapping();
					Snmp snmp = new Snmp(transport);
					MPv3.setEnterpriseID(3320);
					USM usm = new USM(SecurityProtocols.getInstance(),   
					new OctetString(MPv3.createLocalEngineID()), 0);   
					SecurityModels.getInstance().addSecurityModel(usm);   
					transport.listen();  
				
				
				    
				// add user to the USM   
				snmp.getUSM().addUser(user,new UsmUser(user,AuthMD5.ID,passwd,PrivDES.ID,passwd));
				  
				// create the target   
				UserTarget target = new UserTarget();   
				target.setAddress(targetAddress);   
				target.setRetries(2);   
				target.setTimeout(3000);   
				target.setVersion(SnmpConstants.version3);   
				target.setSecurityLevel(SecurityLevel.AUTH_NOPRIV);   
				target.setSecurityName(user);   
				  
				// create the PDU   
				PDU pdu = new ScopedPDU();   
				pdu.add(new VariableBinding(
		        		new OID(".1.3.6.1.2.1.1.1.0"),
		                new OctetString("This is my description")));
		        pdu.add(new VariableBinding(
		        		new OID(".1.3.6.1.4.1.2021.2.1.2.1"),
		                new OctetString("I dont know what is this")));
				pdu.setType(PDU.TRAP);   
				pdu.add(new VariableBinding(new OID(".1.3.6.1.6.3.1.1.4.1"), new OctetString("EnterPrise")));
				
				Vector<VariableBinding> exitVarBindsVec=pdu.getVariableBindings();
				for (int i = 0; i < exitVarBindsVec.size(); i++) {
					
					
					VariableBinding varbindBid =exitVarBindsVec.get(i);
					OID oid =varbindBid.getOid();
					if (oid.toString().equals("1.3.6.1.6.3.1.1.4.1")) {
						varbindBid.setVariable(new OctetString("new oid"));
						break;
					}
					
				}
				
				
				// send the PDU
				
					snmp.send(pdu, target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	public static void main(String[] args) {
		sendTrapV3();
	}

}
