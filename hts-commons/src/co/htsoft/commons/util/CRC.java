package co.htsoft.commons.util;

public class CRC {
	
	public static final int _CRC_OK_RESIDUAL = (int)0xf0b8;
    
    public static short crc16(byte[] data, int dataOff, int dataLength){
    //Calculate an ISO13239 CRC checksum of the input buffer.
    
        int m_crc = 0xffff;
        for (int i=dataOff; i<dataOff+dataLength; i++){
            m_crc ^= (data[i] & 0xff);
            for (int k=0; k<8; k++){
                if ((m_crc & 1) != 0)
                    m_crc = (m_crc >>> 1) ^ 0x1021;
                  else
                    m_crc = m_crc >>> 1;
            }
        }
        return (short)(m_crc & 0xffff);
    }
    public static short crc16(byte[] data){
    //Calculate an ISO13239 CRC checksum of the input buffer.
        return crc16(data, 0, data.length);
    }
    public static boolean validate_crc16(byte[] data){
        return crc16(data) == _CRC_OK_RESIDUAL;
    }
    public static boolean validate_crc16(byte[] data, int dataOff, int dataLength){
        return crc16(data, dataOff, dataLength) == _CRC_OK_RESIDUAL;
    }
    
    public static String toHexString(byte[] bytes) {

            final String hexChars = "0123456789ABCDEF";
            StringBuffer sbTmp = new StringBuffer();
            char[] cTmp = new char[2];
            
            if (bytes==null){
                return new String("null");
            }
            
            for (int i = 0; i < bytes.length; i++) {
                    cTmp[0] = hexChars.charAt((bytes[i] & 0xF0) >>> 4);
                    cTmp[1] = hexChars.charAt(bytes[i] & 0x0F);
                    sbTmp.append(cTmp);
            }
            
            return sbTmp.toString();
    }
    public static String toHexString(byte bytes) {

            final String hexChars = "0123456789ABCDEF";
            StringBuffer sbTmp = new StringBuffer();
            char[] cTmp = new char[2];
            
            cTmp[0] = hexChars.charAt((bytes & 0xF0) >>> 4);
            cTmp[1] = hexChars.charAt(bytes & 0x0F);
            sbTmp.append(cTmp);
            
            return sbTmp.toString();
    }

}
