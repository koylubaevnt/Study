package com.brysekkel.typeinfo.generics;

interface Payable<T> {}
class EmployeeM implements Payable<EmployeeM> { }
//Не компилится из-за того что считается 1 интерфейс надо реализовать 2 раза
//class Hourly extends EmployeeM implements Payable<Hourly> { }

public class MultiplyInterfaceVariants {

}
