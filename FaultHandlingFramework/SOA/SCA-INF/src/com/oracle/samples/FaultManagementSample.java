package com.oracle.samples;

import java.util.Map;
import java.util.Iterator;

import javax.xml.namespace.QName;

import oracle.integration.platform.faultpolicy.IFaultRecoveryContext;
import oracle.integration.platform.faultpolicy.IFaultRecoveryJavaClass;


public class FaultManagementSample
  implements IFaultRecoveryJavaClass
{
  public FaultManagementSample()
  {
    super();
  }

  public void handleRetrySuccess(IFaultRecoveryContext iFaultRecoveryContext)
  {
    System.out.println("**** handleRetrySuccess() method called ...");

    this.printValues(iFaultRecoveryContext);
  }

  public String handleFault(IFaultRecoveryContext iFaultRecoveryContext)
  {
    System.out.println("**** handleFault() method called ...");

    this.printValues(iFaultRecoveryContext);

    return "RETRY";

    /*
     * NOTE: We didn't implement returning "TERMINATE" or "RETHROW" so the policy
     *       is simply an example that you can return multiple values from this
     *       method depending upon your needs.
     */
  }
  
  private void printValues(IFaultRecoveryContext iFaultRecoveryContext)
  {
    String    policyId    = iFaultRecoveryContext.getPolicyId();
    QName     portType    = iFaultRecoveryContext.getPortType();
    Map       properties  = iFaultRecoveryContext.getProperties();
    String    refName     = iFaultRecoveryContext.getReferenceName();
    String    type        = iFaultRecoveryContext.getType();
    Iterator  it          = properties.entrySet().iterator();
    
    System.out.println("**** PolicyId:       " + policyId);
    System.out.println("**** PortType:       " + portType.toString());
    System.out.println("**** Properties:     ");
    while(it.hasNext())
    {
      Map.Entry pairs = (Map.Entry)it.next();
      System.out.println("     " + pairs.getKey() + " = " + pairs.getValue());
    }
    System.out.println("**** ReferenceName:  " + refName);
    System.out.println("**** Type:           " + type);

  }
}
