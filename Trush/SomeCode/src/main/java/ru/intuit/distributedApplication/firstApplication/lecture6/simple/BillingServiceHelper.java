package ru.intuit.distributedApplication.firstApplication.lecture6.simple;


/**
* ru/intuit/distributedApplication/firstApplication/lecture6/simple/BillingServiceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BillingService.idl
* 23 ������ 2016 �. 17:11:20 YEKT
*/

abstract public class BillingServiceHelper
{
  private static String  _id = "IDL:simple/BillingService:1.0";

  public static void insert (org.omg.CORBA.Any a, ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingServiceHelper.id (), "BillingService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BillingServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService)
      return (ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ru.intuit.distributedApplication.firstApplication.lecture6.simple._BillingServiceStub stub = new ru.intuit.distributedApplication.firstApplication.lecture6.simple._BillingServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService)
      return (ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingService)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ru.intuit.distributedApplication.firstApplication.lecture6.simple._BillingServiceStub stub = new ru.intuit.distributedApplication.firstApplication.lecture6.simple._BillingServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
