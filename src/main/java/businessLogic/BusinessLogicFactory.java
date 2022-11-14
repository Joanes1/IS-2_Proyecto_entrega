package businessLogic;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BusinessLogicFactory {

	public BLFacade createFacadeInterface(boolean lokala, ConfigXML confXml) {
		if (lokala) {
			DataAccess dataAccess = new DataAccess(confXml.getDataBaseOpenMode().equals("initialize"));
			BLFacadeImplementation blFacadeImpl = new BLFacadeImplementation(dataAccess);
			return blFacadeImpl;
		} else {
			try {
				String zerbitzua = "http://" + confXml.getBusinessLogicNode() + ":" + confXml.getBusinessLogicPort()
						+ "/ws/" + confXml.getBusinessLogicName() + "?wsdl";
				URL url = new URL(zerbitzua);
				QName qname = new QName("http://businesslogic/", "BLFacadeImplementationService");
				Service s = Service.create(url, qname);
				BLFacade blFacade = s.getPort(BLFacade.class);
				return blFacade;
				
			} catch (Exception e) {
				return null;
			}
		}
	}
}
