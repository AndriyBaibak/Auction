package service;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class AuthenticationHandlerFromSoapHeaders implements SOAPHandler<SOAPMessageContext>{
    private static Logger log = Logger.getLogger(AuthenticationHandlerFromSoapHeaders.class);
    UserServiceImpl userService = new UserServiceImpl();
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        log.error("+++++++++++++++++++");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if(!isRequest){

            try{
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPBody soapBody=soapEnv.getBody();
                SOAPHeader soapHeader = soapEnv.getHeader();
               /* Iterator iterator2 = soapBody.getChildElements(new QName("arg1"));
                String desc = ((Node)iterator2.next()).getValue();*/


                if (soapHeader == null){
                    soapHeader = soapEnv.addHeader();
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }
                Iterator iterator = soapHeader.getChildElements(new QName("password"));
                String password = ((Node)iterator.next()).getValue();
                iterator = soapHeader.getChildElements(new QName("login"));
                String login = ((Node)iterator.next()).getValue();
                if(!password.equals((userService.getUserPasswordByLogin(login)))){
                    generateSOAPErrMessage(soapMsg, "User with this userName and password are not registered");
                }
                soapMsg.writeTo(System.out);
            }catch(SOAPException e){
                log.error(e);
            }catch(IOException e){
                log.error(e);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {

        System.out.println("Server : handleFault()......");

        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Server : close()......");
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return null;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        }
        catch(SOAPException e) {log.error(e); }
    }

}
