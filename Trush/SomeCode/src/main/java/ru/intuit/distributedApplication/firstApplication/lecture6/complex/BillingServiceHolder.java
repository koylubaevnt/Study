package ru.intuit.distributedApplication.firstApplication.lecture6.complex;

/**
* ru/intuit/distributedApplication/firstApplication/lecture6/complex/BillingServiceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BillingService.idl
* 23 ������ 2016 �. 21:54:42 YEKT
*/

public final class BillingServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingService value = null;

  public BillingServiceHolder ()
  {
  }

  public BillingServiceHolder (ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingServiceHelper.type ();
  }

}
