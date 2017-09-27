import java.sql.Date;
import java.text.SimpleDateFormat;



public class TypeTransformUtil {
	/**  
	 * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳�� ��bytesToIntLittle_Endian����ʹ�� 
	 * @param value  
	 *            Ҫת����intֵ 
	 * @return byte���� 
	 */    
	public static byte[] intToBytesLittle_Endian( int value )   
	{   
	 byte[] src = new byte[4];  
	 src[3] =  (byte) ((value>>24) & 0xFF);  
	 src[2] =  (byte) ((value>>16) & 0xFF);  
	 src[1] =  (byte) ((value>>8) & 0xFF);    
	 src[0] =  (byte) (value & 0xFF);                  
	 return src;   
	}  
	
	/**  
	 * ��short��ֵת��Ϊռ2���ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳�� ��bytesToShortLittle_Endian����ʹ�� 
	 * @param value  
	 *            Ҫת����shortֵ 
	 * @return byte���� 
	 */    
	public static byte[] shortToBytesLittle_Endian( short value )   
	{   
	 byte[] src = new byte[2];  
	  
	 src[1] =  (byte) ((value>>8) & 0xFF);    
	 src[0] =  (byte) (value & 0xFF);                  
	 return src;   
	}  
	
	
	
	
	
	/**  
	 * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳��  ��bytesToIntBig_Endian����ʹ�� 
	 */    
	public static byte[] intToBytesBig_Endian(int value)   
	{   
	 byte[] src = new byte[4];  
	 src[0] = (byte) ((value>>24) & 0xFF);  
	 src[1] = (byte) ((value>>16)& 0xFF);  
	 src[2] = (byte) ((value>>8)&0xFF);    
	 src[3] = (byte) (value & 0xFF);       
	 return src;  
	}  
	
	
	/**  
	 * ��short��ֵת��Ϊռ2���ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳��  ��bytesToShortBig_Endian����ʹ�� 
	 */    
	public static byte[] shortToBytesBig_Endian(short value)   
	{   
	 byte[] src = new byte[2];  
	 
	 src[0] = (byte) ((value>>8)&0xFF);    
	 src[1] = (byte) (value & 0xFF);       
	 return src;  
	}  

	/**  
	    * byte������ȡint��ֵ��������������(��λ��ǰ����λ�ں�)��˳�򣬺ͺ�intToBytesLittle_Endian����ʹ�� 
	    *   
	    * @param src  
	    *            byte����  
	    * @param offset  
	    *            ������ĵ�offsetλ��ʼ  
	    * @return int��ֵ  
	    */    
	public static int bytesToIntLittle_Endian(byte[] src, int offset) {  
	    int value;    
	    value = (int) ((src[offset] & 0xFF)   
	            | ((src[offset+1] & 0xFF)<<8)   
	            | ((src[offset+2] & 0xFF)<<16)   
	            | ((src[offset+3] & 0xFF)<<24));  
	    return value;  
	}  
	
	
	/**  
	    * byte������ȡshort��ֵ��������������(��λ��ǰ����λ�ں�)��˳�򣬺ͺ�shortToBytesLittle_Endian����ʹ�� 
	    *   
	    * @param src  
	    *            byte����  
	    * @param offset  
	    *            ������ĵ�offsetλ��ʼ  
	    * @return int��ֵ  
	    */    
	public static short bytesToShortLittle_Endian(byte[] src, int offset) {  
	    short value;    
	    value = (short) ((src[offset] & 0xFF)   
	            | ((src[offset+1] & 0xFF)<<8)   
	            );  
	    return value;  
	}  
	  
	 /**  
	    * byte������ȡint��ֵ��������������(��λ�ں󣬸�λ��ǰ)��˳�򡣺�intToBytesLittle_Endian����ʹ�� 
	    */  
	public static int bytesToIntBig_Endian(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<24)  
	            |((src[offset+1] & 0xFF)<<16)  
	            |((src[offset+2] & 0xFF)<<8)  
	            |(src[offset+3] & 0xFF));  
	    return value;  
}
	
	
	/**  
	    * byte������ȡshort��ֵ��������������(��λ�ں󣬸�λ��ǰ)��˳�򡣺�shortToBytesBig_Endian����ʹ�� 
	    */  
	public static short bytesToShortBig_Endian(byte[] src, int offset) {  
	    short value;    
	    value = (short) ( ((src[offset] & 0xFF)<<8)  
	            |((src[offset+1] & 0xFF))  
	            );  
	    return value;  
}
	
	
	public static void main(String[] args) {
		byte[] temp={89, -118, -42, -89};
		
		int time=bytesToIntBig_Endian(temp,0);
		
		long ms=time*1000l;
		
	Date date=new Date(1502269661000l);
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateStr=format.format(date);
	System.out.print(dateStr);
		
	}
	
	
	}
