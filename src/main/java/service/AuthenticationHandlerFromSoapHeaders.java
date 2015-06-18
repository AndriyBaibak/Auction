package service;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        try {
            log.error("===============" + context.getMessage().toString());
            SOAPMessage s = context.getMessage();
            s.writeTo(System.out);

            Map http_headers = (Map) context.get(MessageContext.HTTP_REQUEST_HEADERS);
            List userList = (List) http_headers.get("Login");
            List passList = (List) http_headers.get("Password");

            String login = "";
            String password = "";

            if (userList != null) {
                login = userList.get(0).toString();
            }

            if (passList != null) {
                password = passList.get(0).toString();
            }
                String pass = userService.getUserPasswordByLogin(login);
           if (!password.equals(pass)) {
               return false;
           }
        }catch (Exception e){
            e.printStackTrace();
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
