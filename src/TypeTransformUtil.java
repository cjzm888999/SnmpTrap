import java.sql.Date;
import java.text.SimpleDateFormat;



public class TypeTransformUtil {
	/**  
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToIntLittle_Endian配套使用 
	 * @param value  
	 *            要转换的int值 
	 * @return byte数组 
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
	 * 将short数值转换为占2个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToShortLittle_Endian配套使用 
	 * @param value  
	 *            要转换的short值 
	 * @return byte数组 
	 */    
	public static byte[] shortToBytesLittle_Endian( short value )   
	{   
	 byte[] src = new byte[2];  
	  
	 src[1] =  (byte) ((value>>8) & 0xFF);    
	 src[0] =  (byte) (value & 0xFF);                  
	 return src;   
	}  
	
	
	
	
	
	/**  
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToIntBig_Endian配套使用 
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
	 * 将short数值转换为占2个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToShortBig_Endian配套使用 
	 */    
	public static byte[] shortToBytesBig_Endian(short value)   
	{   
	 byte[] src = new byte[2];  
	 
	 src[0] = (byte) ((value>>8)&0xFF);    
	 src[1] = (byte) (value & 0xFF);       
	 return src;  
	}  

	/**  
	    * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytesLittle_Endian配套使用 
	    *   
	    * @param src  
	    *            byte数组  
	    * @param offset  
	    *            从数组的第offset位开始  
	    * @return int数值  
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
	    * byte数组中取short数值，本方法适用于(低位在前，高位在后)的顺序，和和shortToBytesLittle_Endian配套使用 
	    *   
	    * @param src  
	    *            byte数组  
	    * @param offset  
	    *            从数组的第offset位开始  
	    * @return int数值  
	    */    
	public static short bytesToShortLittle_Endian(byte[] src, int offset) {  
	    short value;    
	    value = (short) ((src[offset] & 0xFF)   
	            | ((src[offset+1] & 0xFF)<<8)   
	            );  
	    return value;  
	}  
	  
	 /**  
	    * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytesLittle_Endian配套使用 
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
	    * byte数组中取short数值，本方法适用于(低位在后，高位在前)的顺序。和shortToBytesBig_Endian配套使用 
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
